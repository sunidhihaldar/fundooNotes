package com.bridgelabz.fundooNotes.dto;

import org.springframework.stereotype.Component;

/**
 * This class note DTO takes input from the user
 * @author Sunidhi Haldar
 * @created 2020-01-24
 * @version 1.8
 */

@Component
public class NoteDto {

	private String title;
	
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
