package com.bridgelabz.fundooNotes.service;

import java.util.List;

import com.bridgelabz.fundooNotes.dto.NoteDto;
import com.bridgelabz.fundooNotes.dto.NoteUpdation;
import com.bridgelabz.fundooNotes.model.NoteInfo;

public interface INoteService {

	public boolean createNote(NoteDto note, String token);
	
	public boolean updateNote(NoteUpdation updateNote, String token);
	
	public boolean deleteNote(long noteId, String token);
	
	public boolean archiveNote(long noteId, String token);
	
	public boolean pinNote(long noteId, String token);
	
	public boolean trashNote(long noteId, String token);
	
	public List<NoteInfo> getAllNotes(String token);
}
