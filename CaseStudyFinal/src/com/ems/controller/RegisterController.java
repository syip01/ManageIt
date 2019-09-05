package com.ems.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ems.entities.*;
import com.ems.services.*;

@Controller
public class RegisterController {

	@RequestMapping(value= {"/", "/index"})
	public ModelAndView getIndex()
	{
		return new ModelAndView("index");
	}

	@RequestMapping("/registerAdmin")
	public ModelAndView getAdminRegisterPage()
	{
		ModelAndView mav = new ModelAndView("registerAdmin");
		mav.addObject("userKey", new EventAdmin());
		return mav;
	}
	
	@RequestMapping("/confirmAdminRegistration")
	public ModelAndView confirmAdminRegistration(@RequestParam("password2") String password2,
			@Valid @ModelAttribute("userKey") EventAdmin admin, 
			BindingResult errors)
	{
		// redirect errors to form
		if(errors.hasErrors()) {
			return new ModelAndView("registerAdmin");
		}
		
		// upon completion, go to index
		ModelAndView mav = new ModelAndView("index");

		// check if password was entered twice correctly
		boolean hasSamePassword = password2.equals(admin.getPassword());
		String message = !hasSamePassword ? "Must enter same password twice!" : "";		
		
		// add user when password is confirmed
		if(hasSamePassword)
		{
			EventAdminServices adminServices = new EventAdminServices();

			boolean result = adminServices.addEventAdmin(admin);
			message = result ? "EventAdmin saved" :  "EventAdmin not saved: username exists";
		}
		// otherwise redirect message to form page
		else
			mav.setViewName("registerAdmin");
		
		// add message to mav
		mav.addObject("messageResult", message);
		
		return mav;
	}
	
	@RequestMapping("/register")
	public ModelAndView getRegisterPage()
	{
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("userKey", new EventUser());
		return mav;
	}
	
	@RequestMapping("/confirmRegistration")
	public ModelAndView confirmRegistration(@RequestParam("password2") String password2,
			@Valid @ModelAttribute("userKey") EventUser user, 
			BindingResult errors)
	{
		// redirect to form page on error
		if(errors.hasErrors()) {
			return new ModelAndView("register");
		}
		
		// redirect to index upon completion
		ModelAndView mav = new ModelAndView("index");

		// check with password when typed correctly
		boolean hasSamePassword = password2.equals(user.getPassword());
		String message = !hasSamePassword ? "Must enter same password twice!" : "";		
		
		// add user to database when password is correct
		if(hasSamePassword)
		{
			EventUserServices userServices = new EventUserServices();

			boolean result = userServices.addEventUser(user);
			message = result ? "EventUser saved" :  "EventUser not saved: username exists";
		}
		// otherwise redirect message to error page
		else
			mav.setViewName("register");
		
		// add message to mav
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
