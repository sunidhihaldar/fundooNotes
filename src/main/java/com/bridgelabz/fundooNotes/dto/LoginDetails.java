package com.bridgelabz.fundooNotes.dto;

/**
 * This class provides login details like email and password
 * @author Sunidhi Haldar
 * @created 2020-01-23
 * @version 1.8
 */

public class LoginDetails {

	private String email;
	
	private String password;

	public LoginDetails(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
