package com.bridgelabz.fundooNotes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Component
public class LabelDto {

	@NotBlank(message = "field should not be empty")
	@Pattern(regexp = "[a-zA-Z]*")
	private String labelName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
}
