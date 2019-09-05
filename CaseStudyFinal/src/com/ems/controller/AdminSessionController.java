package com.ems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ems.entities.*;
import com.ems.services.*;

@SessionAttributes(value= {"userLogin", "userType"})
@Controller
public class AdminSessionController {

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

	
	@RequestMapping("/modifyRooms")
	public ModelAndView getModifyRooms(@SessionAttribute("userLogin") EventAdmin userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// create data for form
		RoomServices roomServices = new RoomServices();
		mav = new ModelAndView("modifyRooms");
		mav.addObject("roomList", roomServices.getAllRooms());
		mav.addObject("userKey", new Room());
		
		return mav;
	}
	
	@RequestMapping("/addRoom")
	public ModelAndView addRoom(@Valid @ModelAttribute("userKey") Room room,
			BindingResult errors,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		// validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;

		//prepare data for near immediate usage
		mav = new ModelAndView("modifyRooms");		
		RoomServices roomServices = new RoomServices();				
		
		// redirect errors to form
		if(errors.hasErrors()) {
			//add not-updated roomlist
			mav.addObject("roomList", roomServices.getAllRooms());
			return mav;
		}		

		// attempt to add new room and store updated room list
		boolean success = roomServices.addRoom(room);
		String message = success ? "Room Added Successfully" : "Failed To Add Room, Possibly Duplicate Room Name";
		mav.addObject("roomList", roomServices.getAllRooms());
		mav.addObject("messageResult", message);
		
		return mav;
	}

	@RequestMapping("/removeRoom/{id}")
	public ModelAndView deleteRoom(@PathVariable("id") int id,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// delete the room
		RoomServices roomServices = new RoomServices();
		Room deleteRoom = roomServices.getRoomById(id);
		boolean result = roomServices.deleteRoom(deleteRoom);
		
		// get new room list and add objects to page as needed
		List<Room> roomList = roomServices.getAllRooms();		
		String message = result ? "Room removed id = " + id : "Room not deleted";
		
		mav = new ModelAndView("modifyRooms");
		mav.addObject("messageResult", message);
		mav.addObject("roomList", roomList);
		mav.addObject("userKey", new Room());
		
		return mav;
	}
	
	@RequestMapping("/updateRoom/{id}")
	public ModelAndView getUpdateRoom(@PathVariable("id") int id,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status,
			Model model)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// attempt to find room and send roomlist
		RoomServices roomServices = new RoomServices();
		Room foundRoom = roomServices.getRoomById(id);
		
		mav = new ModelAndView("updateRoom");
		mav.addObject("room", foundRoom);
		
		//add attribute if it doesn't already exist
	    if(!model.containsAttribute("userKey"))
	    	model.addAttribute("userKey", new Room());
		
		return mav;
	}
	
	@RequestMapping("/confirmUpdateRoom")
	public ModelAndView updateRoom(@Valid @ModelAttribute("userKey") Room room,
			BindingResult errors,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status,
			RedirectAttributes attr)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// redirect errors to form
		if(errors.hasErrors()) {
		    attr.addFlashAttribute("org.springframework.validation.BindingResult.userKey", errors);
		    attr.addFlashAttribute("userKey", room);
			return new ModelAndView("redirect:/updateRoom/" + room.getId());
		}
		
		// attempt to update room and send updated roomlist
		RoomServices roomServices = new RoomServices();
		boolean success = roomServices.updateRoom(room);
		
		List<Room> roomList = roomServices.getAllRooms();		
		String message = success ? "Room updated id = " + room.getId() : "Room not updated";
		
		mav = new ModelAndView("modifyRooms");
		mav.addObject("messageResult", message);
		mav.addObject("roomList", roomList);
		
		return mav;
	}
	
	@RequestMapping("/modifyGroups")
	public ModelAndView getModifyGroups(@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{	
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// create data for form
		GroupServices groupServices = new GroupServices();
		mav = new ModelAndView("modifyGroups");
		mav.addObject("groupList", groupServices.getAllGroups());
		mav.addObject("userKey", new Group());
		
		return mav;
	}
	
	@RequestMapping("/addGroup")
	public ModelAndView addGroup(@Valid @ModelAttribute("userKey") Group group,
			BindingResult errors,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		// validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;		
		
		// prepare for near immediate usage
		mav = new ModelAndView("modifyGroups");
		GroupServices groupServices = new GroupServices();
		
		// redirect errors to form
		if(errors.hasErrors()) {
			//add not-updated roomlist
			mav.addObject("groupList", groupServices.getAllGroups());
			return mav;
		}		
		
		// attempt to add group and post new list
		boolean success = groupServices.addGroup(group);
		String message = success ? "Group Added Successfully" : "Failed To Add Group, Possibly Duplicate";
		mav.addObject("messageResult", message);
		mav.addObject("groupList", groupServices.getAllGroups());
		
		return mav;
	}
	
	@RequestMapping("/removeGroup/{id}")
	public ModelAndView deleteGroup(@PathVariable("id") int id,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		//delete the group
		GroupServices groupServices = new GroupServices();
		Group deleteGroup = groupServices.getGroupById(id);
		boolean result = groupServices.deleteGroup(deleteGroup);
		
		// get new group list and add objects to page as needed		
		List<Group> groupList = groupServices.getAllGroups();		
		String message = result ? "Group removed id = " + id : "Group not deleted";
		
		mav = new ModelAndView("modifyGroups");
		mav.addObject("messageResult", message);
		mav.addObject("groupList", groupList);
		mav.addObject("userKey", new Group());
		
		return mav;
	}
	
	@RequestMapping("/updateGroup/{id}")
	public ModelAndView getUpdateGroup(@PathVariable("id") int id,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status,
			Model model)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;

		// attempt to find group and send grouplist
		GroupServices groupServices = new GroupServices();
		Group foundGroup = groupServices.getGroupById(id);
		
		mav = new ModelAndView("updateGroup");
		mav.addObject("group", foundGroup);

		//add attribute if it doesn't already exist
	    if(!model.containsAttribute("userKey"))
	    	model.addAttribute("userKey", new Group());
		
		return mav;
	}
	
	@RequestMapping("/confirmUpdateGroup")
	public ModelAndView updateGroup(@Valid @ModelAttribute("userKey") Group group,
			BindingResult errors,
			@SessionAttribute("userLogin") User userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status,
			RedirectAttributes attr)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// redirect errors to form
		if(errors.hasErrors()) {
		    attr.addFlashAttribute("org.springframework.validation.BindingResult.userKey", errors);
		    attr.addFlashAttribute("userKey", group);
			return new ModelAndView("redirect:/updateGroup/" + group.getId());
		}

		// attempt to update group and send updated grouplist
		GroupServices groupServices = new GroupServices();
		boolean success = groupServices.updateGroup(group);
		
		List<Group> groupList = groupServices.getAllGroups();
		String message = success ? "Room updated" : "Room not updated";
		
		mav = new ModelAndView("modifyGroups");
		mav.addObject("messageResult", message);
		mav.addObject("groupList", groupList);
		
		return mav;
	}
	
	@RequestMapping("/modifyEvents")
	public ModelAndView getModifyEvent(@SessionAttribute("userLogin") EventAdmin userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;

		// set up the redirection
		mav = new ModelAndView("modifyEvents");
		
		// get list of events
		EventServices eventServices = new EventServices();		
		List<Event> eventList = eventServices.getAllEvents(); 
		mav.addObject("eventList", eventList);
		
		return mav;
	}

	
	@RequestMapping("/approveEvent/{id}")
	public ModelAndView approveEvent(@PathVariable("id") int id,
			@SessionAttribute("userLogin") EventAdmin userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;

		// set up services
		EventServices eventServices = new EventServices();
		EventAdminServices adminServices = new EventAdminServices();
		
		// get complete objects from database
		Event event = eventServices.getEventById(id);
		EventAdmin admin = adminServices.getEventAdminById(userLogin.getId());
		
		// set the admin
		event.setAdmin(admin);
		// 3 = approved
		event.setStatus(3);
		
		// actually execute the changes
		boolean success = eventServices.updateEvent(event);
		
		// store result into a string
		String message = success ? "Event " + id + " confirmed approval" : "Event " + id + " not confirmed to be approved"; 
		
		// get list of possibly updated events
		List<Event> eventList = eventServices.getAllEvents();		
				
		// attach necessary data to mav
		mav = new ModelAndView("modifyEvents");
		mav.addObject("eventList", eventList);
		mav.addObject("messageResult", message);
		
		return mav;
	}
	
	@RequestMapping("/disapproveEvent/{id}")
	public ModelAndView disapproveEvent(@PathVariable("id") int id,
			@SessionAttribute("userLogin") EventAdmin userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;		
		
		// set up services
		EventServices eventServices = new EventServices();
		EventAdminServices adminServices = new EventAdminServices();

		// get complete objects from database
		Event event = eventServices.getEventById(id);
		EventAdmin admin = adminServices.getEventAdminById(userLogin.getId());
		
		// set the admin
		event.setAdmin(admin);
		// 1 = not approved
		event.setStatus(1);

		// actually execute the changes
		boolean success = eventServices.updateEvent(event);
		
		// store result into the string
		String message = success ? "Event " + id + " confirmed disapproval" : "Event " + id + " not confirmed to be disapproved"; 
		
		// get list of possibly updated events
		List<Event> eventList = eventServices.getAllEvents();		

		// attach necessary stuff to mav
		mav = new ModelAndView("modifyEvents");
		mav.addObject("eventList", eventList);
		mav.addObject("messageResult", message);
		
		return mav;
	}

	@RequestMapping("/deleteEvent/{id}")
	public ModelAndView deleteEvent(@PathVariable("id") int id,
			@SessionAttribute("userLogin") EventAdmin userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;				
		
		// create service to grab event data
		EventServices eventServices = new EventServices();
		Event event = eventServices.getEventById(id);
		
		// run the query
		boolean success = eventServices.deleteEvent(event);
		
		// store result
		String message = success ? "Event " + id + " deleted" : "Event " + id + " not deleted";
		
		// get list of possibly updated/deleted events
		List<Event> eventList = eventServices.getAllEvents();		
				
		// attach necessary stuff to mav
		mav = new ModelAndView("modifyEvents");
		mav.addObject("eventList", eventList);
		mav.addObject("messageResult", message);
		
		return mav;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		// not used for reasons
		//binder.setDisallowedFields(new String[] {"lastName"});
		//binder.setAllowedFields(new String[] {"firstName"});
	}
}
