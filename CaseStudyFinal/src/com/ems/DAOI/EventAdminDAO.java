package com.ems.DAOI;

import java.util.List;

import com.ems.entities.EventAdmin;

public interface EventAdminDAO {

	List<EventAdmin> getAllEventAdmins();

	EventAdmin getEventAdminById(int pId);

	boolean addEventAdmin(EventAdmin admin);

	boolean updateEventAdmin(EventAdmin admin);

	boolean deleteEventAdmin(EventAdmin admin);

	boolean isValidLogin(String username, String password);

	EventAdmin getEventAdminByUsername(String username);

}
