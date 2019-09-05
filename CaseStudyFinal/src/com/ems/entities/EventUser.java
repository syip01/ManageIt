package com.ems.entities;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value="User")
@NamedQueries ({
@NamedQuery (query="SELECT e FROM EventUser e", name="queryAllEventUsers"),
@NamedQuery (query="SELECT e FROM EventUser e WHERE e.username=:username", name="queryUserUsername"),
})
public class EventUser extends User {
	
	public EventUser()
	{
		this(DEFAULT_ID, "User Name", "newuser", "user@email.com", "userpass", new ArrayList<Group>());
	}
	
	public EventUser(int id, String name, String username, String email, String password, List<Group> groupList)
	{
		super(id, name, username, email, password, groupList);
	}
	
	// print out message about event user
	@Override
	public String toString()
	{
		return("EVENT USER: \n" + super.toString());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EventAdmin)
		{
			EventUser obj2 = (EventUser) obj;
			return this.id == obj2.id &&
					this.name.equals(obj2.name) &&
					this.username.equals(obj2.username) &&
					this.email.equals(obj2.email) &&
					this.password.equals(obj2.password) &&
					this.groupList.equals(obj2.groupList);
		}
		
		return false;
	}
}
