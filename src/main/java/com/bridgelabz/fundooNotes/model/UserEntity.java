package com.bridgelabz.fundooNotes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This model class acts like an entity and has parameters that gets stored in the database
 * @author Sunidhi Haldar
 * @created 2020-01-17
 * @version 1.8
 */

@Entity
@Table(name="user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false, length = 50)
	private String lastName;
	
	@Column(nullable = false, length = 150, unique = true)
	private String email;
	
	@Column(nullable = false, length = 50)
	private long mobileNumber;

	@Column(nullable = false)
	private String password;
	
	@Column(columnDefinition = "boolean default false", nullable = false)
	private boolean isVerified;
	
	private String createdAt;
	
	private List<NoteInfo> note;

	public UserEntity(long userId, String firstName, String lastName, String email, long mobileNumber,
			String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
	
	public UserEntity() { }
		
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isVerified() {
		return isVerified;
	}
	
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public List<NoteInfo> getNote() {
		return note;
	}

	public void setNote(List<NoteInfo> note) {
		this.note = note;
	}
}
