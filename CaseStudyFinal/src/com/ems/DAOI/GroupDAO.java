package com.ems.DAOI;

import java.util.List;

import com.ems.entities.Group;

public interface GroupDAO {

	List<Group> getAllGroups();

	Group getGroupById(int pId);

	boolean addGroup(Group group);

	boolean updateGroup(Group group);

	boolean deleteGroup(Group group);

	Group getGroupByName(String name);

}
