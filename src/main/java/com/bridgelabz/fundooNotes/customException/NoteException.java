package com.bridgelabz.fundooNotes.customException;

public class NoteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoteException(String message) {
		super(message);
	}
}
