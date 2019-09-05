package com.ems.entities;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value="Admin")
@NamedQueries ({
@NamedQuery (query="SELECT e FROM EventAdmin e", name="queryAllEventAdmins"),
@NamedQuery (query="SELECT e FROM EventAdmin e WHERE e.username=:username", name="queryAdminUsername"),
})
public class EventAdmin extends User {
	
	public EventAdmin()
	{
		this(DEFAULT_ID, "Admin Name", "newadmin", "admin@email.com", "adminpass", new ArrayList<Group>());
	}
	
	public EventAdmin(int id, String name, String username, String email, String password, List<Group> groupList)
	{
		super(id, name, username, email, password, groupList);
	}
	
	// print out message about event admin
	@Override
	public String toString()
	{
		return("EVENT ADMIN: \n" + super.toString());
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof EventAdmin)
		{
			EventAdmin obj2 = (EventAdmin) obj;
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
