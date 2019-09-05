package com.ems.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.*;

@Entity
@Table(name="\"Group\"")
@NamedQueries ({
@NamedQuery (query="SELECT g FROM Group g", name="queryAllGroups"),
@NamedQuery (query="SELECT g FROM Group g where g.name=:name", name="queryGroup"),
//@NamedQuery (query="SELECT DISTINCT g FROM Group g JOIN g.userList u WHERE u.id = :id ", name="queryAllGroupsByUserId"),
})
public class Group {
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
	@Email
	@Size(min = 8, max = 30)
	private String email;
	
	@OneToMany(targetEntity=EmailInfo.class)
	List<EmailInfo> mailingList;
	
	@ManyToMany (mappedBy = "groupList")
	@JoinTable(
			name="users_groups",
			joinColumns = @JoinColumn(name = "groupid"),
			inverseJoinColumns = @JoinColumn(name = "userid"))
	List<User> userList;
	
	@Transient
	private static final String EMAIL_REGEX = 
			"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	public Group() {
		this(DEFAULT_ID, "Default Group", "default@gmail.com", new ArrayList<EmailInfo>(), new ArrayList<User>());
	}
	
	public Group(int id, String name, String email, List<EmailInfo> mailingList, List<User> userList) {
		super();
		this.id = id;
		this.name = name;
		this.setEmail(email);
		this.setMailingList(mailingList);
		this.setUserList(userList);
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

	public List<EmailInfo> getMailingList() {
		return mailingList;
	}

	public void setMailingList(List<EmailInfo> mailingList) {
		this.mailingList = mailingList != null ? mailingList : new ArrayList<EmailInfo>();
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList != null ? userList : new ArrayList<User>();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (validateEmail(email))
			this.email = email;
		else
			throw new IllegalArgumentException("Invalid email");
	}
	
	// public helper function for email validation
	public static boolean validateEmail(String email)
	{
		return email.matches(EMAIL_REGEX);
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", email=" + email + ", mailingList=" + mailingList
				+ ", userList=" + userList + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Group)
		{
			Group obj2 = (Group) obj;
			return this.id == obj2.id &&
					this.name.equals(obj2.name) &&
					this.email.equals(obj2.email) &&
					(this.mailingList.contains(obj2.mailingList) &&
					this.mailingList.size() == obj2.mailingList.size() ||
					this.mailingList.isEmpty() && obj2.mailingList.isEmpty());

		}

		return false;
	}	
}
