package com.ems.DAOI;

import java.util.List;

import com.ems.entities.Event;

public interface EventDAO {

	List<Event> getAllEvents();

	Event getEventById(int pId);

	boolean addEvent(Event event);

	boolean updateEvent(Event event);

	boolean deleteEvent(Event event);

	List<Event> getAllEventsFromGroupId(int pId);

}
