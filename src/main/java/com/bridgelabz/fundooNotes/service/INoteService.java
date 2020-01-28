package com.bridgelabz.fundooNotes.service;

import com.bridgelabz.fundooNotes.dto.NoteDto;

public interface INoteService {

	public boolean create(NoteDto note, String token);
}
