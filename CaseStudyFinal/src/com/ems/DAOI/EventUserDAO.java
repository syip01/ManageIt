package com.ems.DAOI;

import java.util.List;

import com.ems.entities.EventUser;
import com.ems.entities.Group;

public interface EventUserDAO {

	List<EventUser> getAllEventUsers();

	EventUser getEventUserById(int pId);

	boolean addEventUser(EventUser user);

	boolean updateEventUser(EventUser user);

	boolean deleteEventUser(EventUser user);

	boolean isValidLogin(String username, String password);

	EventUser getEventUserByUsername(String username);

	boolean addGroupById(EventUser user, int pId);

	boolean removeGroupById(EventUser user, int pId);

	List<Group> getAllUserGroups(int pId);

}
