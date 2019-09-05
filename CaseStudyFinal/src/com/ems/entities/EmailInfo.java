package com.ems.entities;

import javax.persistence.*;

@Entity
@Table
public class EmailInfo {
	@Transient
	private static final int DEFAULT_ID = 0;
	@Id
	private int id;
	@Basic
	@Column(nullable = false)
	private String name;
	@Basic
	@Column(nullable = false)
	private String email;

	@Transient
	private static final String EMAIL_REGEX = 
			"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	public EmailInfo() {
		this(DEFAULT_ID, "Default Name", "default@email.com");
	}

	public EmailInfo(int id, String name, String email) {
		super();
		this.setId(id);
		this.name = name;
		this.email = email;
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
		return "EmailInfo [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EmailInfo)
		{
			EmailInfo obj2 = (EmailInfo) obj;
			return this.id == obj2.id &&
					this.name.equals(obj2.name) &&
					this.email.equals(obj2.email);
		}
		
		return false;
	}
}
