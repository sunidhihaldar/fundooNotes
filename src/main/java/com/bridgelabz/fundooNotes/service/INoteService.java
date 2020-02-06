package com.bridgelabz.fundooNotes.service;

import java.util.List;

import com.bridgelabz.fundooNotes.dto.NoteDto;
import com.bridgelabz.fundooNotes.dto.NoteUpdation;
import com.bridgelabz.fundooNotes.dto.ReminderDto;
import com.bridgelabz.fundooNotes.model.LabelInfo;
import com.bridgelabz.fundooNotes.model.NoteInfo;

/**
 * this interface provides unimplemented methods for functionalities of a note
 * @author Sunidhi Haldar
 * @created 2020-01-24
 * @version 1.8
 */

public interface INoteService {

	public boolean createNote(NoteDto note, String token);
	
	public boolean updateNote(NoteUpdation updateNote, String token);
	
	public boolean deleteNote(long noteId, String token);
	
	public boolean archiveNote(long noteId, String token);
	
	public boolean isPinnedNote(long noteId, String token);
	
	public boolean trashNote(long noteId, String token);
	
	public List<NoteInfo> getAllNotes(String token);
	
	public List<NoteInfo> getAllPinnedNotes(String token);
	
	public List<NoteInfo> getAllTrashedNotes(String token);
	
	public List<NoteInfo> getAllArchivedNotes(String token);
	
	public boolean updateColour(long noteId, String token, String colour);
	
	public boolean setReminderNote(long noteId, String token, ReminderDto reminder);
	
	public boolean removeReminderNote(long noteId, String token);
	
	public List<NoteInfo> searchByTitle(String token, String title);

	public List<LabelInfo> getLabelsOfNote(long noteId, String token);
}
