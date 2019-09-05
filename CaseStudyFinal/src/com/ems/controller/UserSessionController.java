package com.ems.controller;

import java.time.*;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ems.entities.*;
import com.ems.services.*;

@SessionAttributes(value= {"userLogin", "userType"})
@Controller
public class UserSessionController {
	
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
	
	@RequestMapping("/associateGroups")
	public ModelAndView associateGroups(@SessionAttribute("userLogin") User userLogin,
										@SessionAttribute("userType") String userType,
										SessionStatus status)
	{	
		// check is current user is legit
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;

		// create necessary services
		GroupServices groupServices = new GroupServices();
		EventUserServices userServices = new EventUserServices();
		
		// create necessary lists
		List<Group> groupList = groupServices.getAllGroups();
		List<Group> userGroupList = userServices.getAllUserGroups(userLogin.getId());
		
		// removes all groups not in userGroupList
		groupList.removeAll(userGroupList); 
		
		// create mav and add necessary objects/lists for viewing
		mav = new ModelAndView("associateGroups");
		mav.addObject("userGroupList", userGroupList);
		mav.addObject("groupList", groupList);
		
		return mav;
	}
	
	@RequestMapping("/addUserGroup/{id}")
	public ModelAndView addUserGroup(@PathVariable("id") int id,
								@SessionAttribute("userLogin") EventUser userLogin,
								@SessionAttribute("userType") String userType,
								SessionStatus status)
	{
		// check is current user is legit
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// add required services
		EventUserServices userServices = new EventUserServices();
		GroupServices groupServices = new GroupServices();

		// adds/associates group to user
		boolean success = userServices.addGroupById(userLogin, id);
		
		// create lists of updated info
		List<Group> groupList = groupServices.getAllGroups();
		List<Group> userGroupList = userServices.getAllUserGroups(userLogin.getId());

		// removes all groups not in userGroupList
		groupList.removeAll(userGroupList); 

		// update mav and add objects/lists to be displayed
		mav = new ModelAndView("associateGroups");
		mav.addObject("userGroupList", userGroupList);
		mav.addObject("groupList", groupList);
		
		// gets group that is to be associated with
		Group group = groupServices.getGroupById(id);
		
		// construct useful message that mentions the users new association with selected group
		String message = userLogin.getName() +
				(success ?  " was Successfully associated with " :  " was Unsuccessfully associated with ") +
				group.getName();
		mav.addObject("messageResult" , message);
		
		return mav;
	}
	
	@RequestMapping("/removeUserGroup/{id}")
	public ModelAndView removeUserGroup(@PathVariable("id") int id,
									@SessionAttribute("userLogin") EventUser userLogin,
									@SessionAttribute("userType") String userType,
									SessionStatus status)
	{
		// check is current user is legit
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// set up services
		EventUserServices userServices = new EventUserServices();
		GroupServices groupServices = new GroupServices();

		// remove group from the group list of the user
		boolean success = userServices.removeGroupById(userLogin, id);	

		// construct lists of groups
		List<Group> groupList = groupServices.getAllGroups();
		List<Group> userGroupList = userServices.getAllUserGroups(userLogin.getId());

		// removes all groups not in userGroupList
		groupList.removeAll(userGroupList); 

		// update mav and attach lists to it
		mav = new ModelAndView("associateGroups");
		mav.addObject("userGroupList", userGroupList);
		mav.addObject("groupList", groupList);
		
		// get group that was removed
		Group group = groupServices.getGroupById(id);
		
		// construct and attach message about the users removal from group
		String message = userLogin.getName() +
				(success ?  " was Successfully removed from " :  " was Unsuccessfully removed from ") +
				group.getName();
		mav.addObject("messageResult" , message);		
		
		return mav;
	}	

	@RequestMapping("/addEvent")
	public ModelAndView getAddEvent(@SessionAttribute("userLogin") EventUser userLogin,
									@SessionAttribute("userType") String userType,
									SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
			
		// declare all necessary services and lists
		EventUserServices userServices = new EventUserServices();
		RoomServices roomServices = new RoomServices();
		EventAdminServices adminServices = new EventAdminServices();
		EventServices eventServices = new EventServices();
		
		List<Room> roomList = roomServices.getAllRooms();
		List<EventAdmin> adminList = adminServices.getAllEventAdmins();
		List<Group> groupList = userServices.getAllUserGroups(userLogin.getId());
		List<Event> eventList = new ArrayList<Event>(); 
		
		// create a list of events based on all of the groups involved
		for(Group group : groupList)
			eventList.addAll(eventServices.getAllEventsFromGroupId(group.getId()));

		// set the mav for intended destination
		mav = new ModelAndView("addEvent");
		
		// add all relevant objects required for display
		mav.addObject("groupList", groupList);
		mav.addObject("roomList", roomList);
		mav.addObject("adminList", adminList);
		mav.addObject("eventList", eventList);
		
		return mav;
	}
	
	@RequestMapping("/confirmAddEvent")
	public ModelAndView confirmAddEvent(@SessionAttribute("userLogin") EventUser userLogin,
			@SessionAttribute("userType") String userType,
			@RequestParam("groupid") int groupId,
			@RequestParam("roomid") int roomId,
			@RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
			@RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// create services for use
		EventUserServices userServices = new EventUserServices();
		GroupServices groupServices = new GroupServices();
		RoomServices roomServices = new RoomServices();
		EventServices eventServices = new EventServices();
		
		// create an event and set all its values (minus id)
		Event event = new Event();
		event.setGroup(groupServices.getGroupById(groupId));
		event.setRoom(roomServices.getRoomById(roomId));
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		// since it is not set by an admin, this value must be null
		event.setAdmin(null);
		// 0 = submitting status
		event.setStatus(0);

		// attempt to add event
		boolean success = eventServices.addEvent(event);
		
		// create appropriate lists required
		List<Group> groupList = userServices.getAllUserGroups(userLogin.getId());
		List<Room> roomList = roomServices.getAllRooms();
		List<Event> eventList = new ArrayList<Event>(); 
		
		// construct lists of events for groups this users is participating in
		for(Group group : groupList)
			eventList.addAll(eventServices.getAllEventsFromGroupId(group.getId()));
		
		//set up where to go and adds list for visual
		mav = new ModelAndView("addEvent");
		mav.addObject("groupList", groupList);
		mav.addObject("roomList", roomList);
		mav.addObject("eventList", eventList);
		
		// create and attach message
		String message = success ? "Event added" : "Event not added";
		mav.addObject("messageResult", message);
		
		return mav;
	}
	
	@RequestMapping("/updateEvent/{id}")
	public ModelAndView getUpdateEvent(@PathVariable("id") int id,
			@SessionAttribute("userLogin") EventUser userLogin,
			@SessionAttribute("userType") String userType,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;

		//creates the necessary services
		EventServices eventServices = new EventServices();
		EventUserServices userServices = new EventUserServices();
		RoomServices roomServices = new RoomServices();

		//finds event in database
		Event foundEvent = eventServices.getEventById(id);		
		
		// create lists necessary to populate dropdowns
		List<Group> groupList = userServices.getAllUserGroups(userLogin.getId());
		List<Room> roomList = roomServices.getAllRooms();
		
		//set up where to go and attach objects/lists needed for viewing info
		mav = new ModelAndView("updateEvent");
		mav.addObject("event", foundEvent);
		mav.addObject("groupList", groupList);
		mav.addObject("roomList", roomList);
		
		return mav;
	}

	@RequestMapping("/confirmUpdateEvent")
	public ModelAndView confirmUpdateEvent(@SessionAttribute("userLogin") EventUser userLogin,
			@SessionAttribute("userType") String userType,
			@RequestParam("id") int id,			
			@RequestParam("groupid") int groupId,
			@RequestParam("roomid") int roomId,
			@RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
			@RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
			SessionStatus status)
	{
		//validate user before continuing
		ModelAndView mav = validateUser(userLogin, userType, status);
		if(mav != null)
			return mav;
		
		// set up all the required services
		EventUserServices userServices = new EventUserServices();
		GroupServices groupServices = new GroupServices();
		RoomServices roomServices = new RoomServices();
		EventServices eventServices = new EventServices();
		
		// get event to be updated
		Event event = eventServices.getEventById(id);
		
		//sets all necessary values
		event.setGroup(groupServices.getGroupById(groupId));
		event.setRoom(roomServices.getRoomById(roomId));
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		// do not set admin - that's done on the admin side anyways
		//event.getAdmin(adminServices.getEventAdminById(id))
		// 0 = submitting status
		// it should be 2 if an admin exists, but there's no real distinction
		event.setStatus(0);

		//attempt to update event data in db
		boolean success = eventServices.updateEvent(event);
		
		// creates lists necessary for visual
		List<Group> groupList = userServices.getAllUserGroups(userLogin.getId());
		List<Room> roomList = roomServices.getAllRooms();
		List<Event> eventList = new ArrayList<Event>(); 
		
		// constructs lists of events based on the groups the user is in
		for(Group group : groupList)
			eventList.addAll(eventServices.getAllEventsFromGroupId(group.getId()));
		
		// sets up mav and adds objects/lists for viewing
		mav = new ModelAndView("addEvent");
		mav.addObject("groupList", groupList);
		mav.addObject("roomList", roomList);
		mav.addObject("eventList", eventList);
		
		// sets up message to be added and attach it
		String message = success ? "Event updated" : "Event not updated";
		mav.addObject("messageResult", message);
		
		return mav;
	}	
}
