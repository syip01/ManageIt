package com.ems.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table
@NamedQueries ({
@NamedQuery (query="SELECT r FROM Room r", name="queryAllRooms"),
@NamedQuery (query="SELECT r FROM Room r WHERE r.name=:name", name="queryRoom"),
})
public class Room {
	@Transient
	private static final int DEFAULT_ID = 0;
	
	@Id
	@GeneratedValue
	@Min(0)
	private int id;
	
	@Basic
	@Column(nullable = false, unique = true)
	@NotNull
	@Size(min = 4, max = 30, message="Name length must be between {2} and {1}")
	private String name;

	@Basic
	@Column(nullable = false)
	@NotNull
	@Size(min = 4, max = 50, message="Location length must be between {2} and {1}")
	private String location;
	
	public Room() {
		this(DEFAULT_ID, "Default Name", "Default Location");
	}
	
	public Room(int id, String name, String location) {
		super();
		this.setId(id);
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id < DEFAULT_ID)
			throw new IllegalArgumentException("Invalid id: Must be greater than " + DEFAULT_ID);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", location=" + location + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Room)
		{
			Room obj2 = (Room) obj;
			return this.id == obj2.id &&
					this.name.equals(obj2.name) &&
					this.location.equals(obj2.location);
		}
		
		return false;
	}
}
