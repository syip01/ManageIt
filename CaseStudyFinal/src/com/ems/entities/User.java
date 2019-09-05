package com.ems.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.ems.validator.*;

import java.util.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role")
public class User {
	@Transient
	protected static final int DEFAULT_ID = 0;
	
	@Id
	@GeneratedValue
	@Min(0)
	protected int id;
	
	@Basic
	@Column(nullable = false)
	@NotNull
	@Size(min = 4, max = 30, message = "Name length must be between {2} to {1}")
	protected String name;
	
	@Basic
	@Column(nullable = false, unique = true)
	@NotNull
	@Size(min = 4, max = 20, message = "Username length must be between {2} to {1}")
	protected String username;
	
	@Basic
	@Column(nullable = false)
	@NotNull
	@Size(min = 8, max = 30)
	//@EmailConstraint
	@Email
	protected String email;
	
	@Basic
	@Column(nullable = false)
	@NotNull
	@Size(min = 4, max = 30)
	@PasswordConstraint
	protected String password;
	
	@ManyToMany
	@JoinTable(
			name="users_groups",
			joinColumns = @JoinColumn(name = "userid"),
			inverseJoinColumns = @JoinColumn(name = "groupid"))
	List<Group> groupList;
	
	// using regex used for emails
	// https://emailregex.com/
	@Transient
	protected static final String EMAIL_REGEX = 
			"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	public User()
	{
		this(DEFAULT_ID, "Default User", "username", "user@gmail.com", "password", new ArrayList<Group>());
	}	
	
	public User(int id, String name, String username, String email, String password, List<Group> groupList) {
		super();
		this.setId(id);
		this.name = name;
		this.username = username;
		this.setEmail(email);
		this.password = password;
		this.setGroupList(groupList);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(!username.equals(""))
			this.username = username;
		else
			throw new IllegalArgumentException("Invalid username: empty string");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList != null ? groupList : new ArrayList<Group>();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password="
				+ password + ", groupList=" + groupList + "]";
	}
}
