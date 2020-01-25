package com.bridgelabz.fundooNotes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NoteDto {

	@NotBlank
	@Pattern(regexp = "[a-zA-z]*", message = "only alphabets are allowed")
	private String title;
	
	@NotBlank
	@Pattern(regexp = "[a-zA-z]*", message = "only alphabets are allowed")
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
