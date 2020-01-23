package com.bridgelabz.fundooNotes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Component
//Data transfer object class, contains details to be asked to the user
public class UserDto {

	@NotBlank
	@Pattern(regexp = "[a-zA-Z]*", message = "Enter valid first name")
	private String firstName;
	
	@NotBlank
	@Pattern(regexp = "[a-zA-Z]*", message = "Enter valid second name")
	private  String lastName;
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull(message = "Enter valid mobile number")
	private long mobileNumber;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Enter a valid password")
	private String password;
	
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
}
