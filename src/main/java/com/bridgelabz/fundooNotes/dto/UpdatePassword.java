package com.bridgelabz.fundooNotes.dto;

/**
 * This class provides credentials for updating password like new password and confirm password
 * @author Sunidhi Haldar
 * @created 2020-01-24
 * @version 1.8
 */

public class UpdatePassword {

	private String newPassword;
	private String confirmPassword;
	
	public UpdatePassword(String newPassword, String confirmPassword) {
		super();
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
