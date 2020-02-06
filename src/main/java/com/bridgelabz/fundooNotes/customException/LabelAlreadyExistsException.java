package com.bridgelabz.fundooNotes.customException;

public class LabelAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LabelAlreadyExistsException(String message) {
		super(message);
	}
}