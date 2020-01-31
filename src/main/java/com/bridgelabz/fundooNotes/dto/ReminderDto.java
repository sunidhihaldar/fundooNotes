package com.bridgelabz.fundooNotes.dto;

import java.time.LocalDateTime;

/**
 * This class is a DTO for reminder functionality
 * @author Sunidhi Haldar
 * @created 2020-01-30
 * @version 1.8
 */

public class ReminderDto {

	private LocalDateTime time;

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
