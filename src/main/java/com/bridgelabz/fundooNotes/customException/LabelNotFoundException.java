package com.bridgelabz.fundooNotes.customException;

public class LabelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LabelNotFoundException(String message) {
		super(message);
	}
}