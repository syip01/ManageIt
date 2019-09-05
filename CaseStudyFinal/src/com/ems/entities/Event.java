package com.ems.entities;

//import java.time.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.*;

@Entity
@Table
@NamedQueries ({
@NamedQuery (query="SELECT e FROM Event e ORDER BY e.id", name="queryAllEvents"),
@NamedQuery (query="SELECT e FROM Event e WHERE e.group.id=:id ORDER BY e.id", name="queryAllEventsByGroupId"),
//@NamedQuery (query="SELECT DISTINCT g FROM Group g JOIN g.userList u WHERE u.id = :id ", name="queryAllGroupsByUserId"),
})
public class Event {
	@Transient
	protected static final int DEFAULT_ID = 0;
	
	@Id
	@GeneratedValue
	@Min(0)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="groupid", referencedColumnName="id", nullable = false)
	private Group group;
	
	@ManyToOne
	@JoinColumn(name="roomid", referencedColumnName="id", nullable = false)
	private Room room;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="adminid", referencedColumnName="id", nullable = true)
	private EventAdmin admin;

	@Column(nullable = false)
	private LocalDateTime startTime;
	
	@Column(nullable = false)
	private LocalDateTime endTime;
	
	@Basic
	@Column(nullable = false)
	@Min(0)
	@Max(3)
	/*	status codes
	 * 0 = pending/submitting/resubmitted
	 * 1 = not approved by admin
	 * 2 = resubmitted (not used at the moment)
	 * 3 = approved
	 */
	private int status;
	
	public Event()
	{
		this(DEFAULT_ID, new Group(), new Room(), new EventAdmin(), LocalDateTime.now(), LocalDateTime.now(), 0);
		//this(DEFAULT_ID, new Group(), new Room(), new EventAdmin(), new LocalDateTime(System.currentTimeMillis()), new LocalDateTime(System.currentTimeMillis()), 0);
		
	}
	
	public Event(int id, Group group, Room room, EventAdmin admin, LocalDateTime startTime, LocalDateTime endTime, int status) {
		super();
		this.setId(id);
		this.group = group;
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id < DEFAULT_ID)
			throw new IllegalArgumentException("Invalid id");
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	public void setStartTime(int year, int month, int day, int hour, int minute) {
		try
		{
			this.startTime = LocalDateTime.of(year, month, day, hour, minute);
		}
		catch (DateTimeException e)
		{
			System.out.println("Unable to set start time: Invalid date/time");
		}
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	public void setEndTime(int year, int month, int day, int hour, int minute) {
		try
		{
			this.endTime = LocalDateTime.of(year, month, day, hour, minute);
		}
		catch (DateTimeException e)
		{
			System.out.println("Unable to set end time: Invalid date/time");
		}
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public EventAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(EventAdmin admin) {
		this.admin = admin;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", group=" + group + ", room=" + room + ", admin=" + admin + ", startTime="
				+ startTime + ", endTime=" + endTime + ", status=" + status + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Event)
		{
			Event obj2 = (Event) obj;
			return this.id == obj2.id &&
					this.group.equals(obj2.group) &&
					this.room.equals(obj2.room) &&
					this.admin.equals(obj2.admin) &&
					this.startTime.equals(obj2.startTime) &&
					this.endTime.equals(obj2.endTime) &&
					this.status == obj2.status;
		}		
		return false;
	}

	// print out message about event
	/*
	@Override
	public String toString()
	{
		return("ID: " + id + "\tGroup Name: " + group.getName() + "\tRoom Name: " + room.getName() + "\tLocation: " + room.getLocation()
				+ "\nStart Time: " + (startTime != null ? startTime : "UNKNOWN")
				+ "\t End Time: " + (endTime != null ? endTime : "UNKNOWN"));
	}
	*/
}
