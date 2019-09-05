package com.ems.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ems.entities.*;
import com.ems.services.*;

@SessionAttributes(value= {"userLogin", "userType"})
@Controller
public class SharedSessionController {

	@RequestMapping("/login")
	public ModelAndView loginToSystem(@RequestParam("username") String username,
			@RequestParam("password") String password,
			SessionStatus status)
	{
		ModelAndView mav = new ModelAndView();
		EventAdminServices adminServices = new EventAdminServices();
		EventUserServices userServices = new EventUserServices();
		EventServices eventServices = new EventServices();
		List<Group> groupList;
		List<Event> eventList; 

		//go check for each possible user type and set the appropriate values
		if(adminServices.isValidLogin(username, password))
		{
			mav.setViewName("dashboardAdmin");
			mav.addObject("userLogin", adminServices.getEventAdminByUsername(username));
			mav.addObject("userType", "admin");
			eventList = eventServices.getAllEvents();
			mav.addObject("eventList", eventList);
		}

		else if(userServices.isValidLogin(username, password))
		{
			mav.setViewName("dashboard");
			EventUser user = userServices.getEventUserByUsername(username);
			mav.addObject("userLogin", user);
			mav.addObject("userType", "user");
			groupList = userServices.getAllUserGroups(user.getId());
			eventList = new ArrayList<Event>(); 
			
			// create a list of events based on all of the groups involved
			for(Group group : groupList)
				eventList.addAll(eventServices.getAllEventsFromGroupId(group.getId()));

			mav.addObject("userGroupList", groupList);
			mav.addObject("eventList", eventList);

		}
		//invalid login, clear everything
		else
		{
			status.setComplete();
			mav.clear();
			mav.setViewName("index");
			mav.addObject("messageResult", "Invalid username/password combo.");
		}
		
		return mav;
	}
	
	public ModelAndView validateUser(@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
{
		ModelAndView mav = null;
		EventAdminServices adminServices = new EventAdminServices();
		EventUserServices userServices = new EventUserServices();

		//store whether user is a valid entity
		boolean valid = false;
		switch(userType)
		{
		case "admin":
			valid = adminServices.isValidLogin(userLogin.getUsername(), userLogin.getPassword());
			break;
		case "user":
			valid = userServices.isValidLogin(userLogin.getUsername(), userLogin.getPassword());
			break;
		}

		if(!valid)
		{
			status.setComplete();
			mav = new ModelAndView();
			mav.clear();
			mav.setViewName("index");
			mav.addObject("messageResult", "You are not authorized to access this page.");			
		}

		return mav;
	}

	
	@RequestMapping("/logout")
	public ModelAndView logout(@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status,
			HttpServletRequest request)
	{
        HttpSession session= request.getSession(false);
        //SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
		
		status.setComplete();
		
		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("index");
		mav.addObject("messageResult", "Successful Logout");
		
		return mav;
	}
	
	@RequestMapping("/updateProfile")
	public ModelAndView getUpdateProfile(@SessionAttribute("userLogin") User userLogin,
											@SessionAttribute("userType") String userType,
											SessionStatus status)
	{
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		mav = new ModelAndView("updateProfile");
		switch(userType)
		{
		case "user":
			mav.addObject("userKey", new EventUser(userLogin.getId(), userLogin.getName(), userLogin.getUsername(), userLogin.getEmail(), userLogin.getPassword(), userLogin.getGroupList()));
			break;
		case "admin":
			mav.addObject("userKey", new EventAdmin(userLogin.getId(), userLogin.getName(), userLogin.getUsername(), userLogin.getEmail(), userLogin.getPassword(), userLogin.getGroupList()));
			break;
		}
				
		return mav;
	}
	
	@RequestMapping("/confirmUpdateProfile")
	public ModelAndView updateProfile(@RequestParam("oldpassword") String oldpassword,
			@RequestParam("password2") String password2,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			@Valid @ModelAttribute("userKey") User user, 
			BindingResult errors,
			SessionStatus status)
	{
		// check is current user is legit
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// redirect errors to form
		if(errors.hasErrors()) {
			return new ModelAndView("updateProfile");
		}
		
		mav = new ModelAndView();

		// validate password repeating requirement
		boolean hasSamePassword = password2.equals(user.getPassword());
		String message = !hasSamePassword ? "Must enter same password twice!<br>" : "";

		// create services
		EventAdminServices adminServices = new EventAdminServices();
		EventUserServices userServices = new EventUserServices();

		User copy = null;
		
		// store whether user is admin
		boolean isAdmin = false;
		
		if(hasSamePassword)
		{			
			// use appropriate service
			switch(userType)
			{
			case "admin":
				copy = adminServices.getEventAdminByUsername(user.getUsername());
				isAdmin = true;
				break;
			case "user":
				copy = userServices.getEventUserByUsername(user.getUsername());
				break;
			}

			// check old password entered is correct
			if(copy.getPassword().equals(oldpassword))
			{
				// copy data then use update service
				copy.setPassword(user.getPassword());
				copy.setName(user.getName());
				copy.setEmail(user.getEmail());
				
				boolean updateSuccess = isAdmin ? adminServices.updateEventAdmin((EventAdmin)copy) :
													userServices.updateEventUser((EventUser) copy);
				
				// update object in session and message
				mav.addObject("userLogin", copy);				
				message = updateSuccess ? "Profile Update Successful" : "Profile Update Unsuccessful";
				mav.setViewName("updateProfile");
			}
			
			else
			{
				message += "Entered incorrect old password!<br>";
				mav.setViewName("updateProfile");
			}
		}
		
		else
			mav.setViewName("updateProfile");
		mav.addObject("messageResult", message);

		
		return mav;
	}

	@RequestMapping("/dashboard")
	public ModelAndView getDashboard(@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		// check is current user is legit
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// declare lists to be added
		EventServices eventServices = new EventServices();
		List<Group> groupList;
		List<Event> eventList = null; 
		
		switch(userType)
		{
		case "admin":
			mav = new ModelAndView("dashboardAdmin");
			eventList = eventServices.getAllEvents();
			break;
		case "user":
			EventUserServices userServices = new EventUserServices();
			mav = new ModelAndView("dashboard");
			groupList = userServices.getAllUserGroups(userLogin.getId());
			eventList = new ArrayList<Event>(); 
			
			// create a list of events based on all of the groups involved
			for(Group group : groupList)
				eventList.addAll(eventServices.getAllEventsFromGroupId(group.getId()));

			mav.addObject("userGroupList", groupList);
			break;
		}
		
		mav.addObject("eventList", eventList);
		
		return mav ;
	}
	
}
