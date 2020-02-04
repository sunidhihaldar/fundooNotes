package com.bridgelabz.fundooNotes.service;

import java.util.List;

import com.bridgelabz.fundooNotes.dto.LabelDto;
import com.bridgelabz.fundooNotes.model.LabelInfo;
import com.bridgelabz.fundooNotes.model.NoteInfo;

public interface ILabelService {

	public boolean createLabel(LabelDto labelDto, String token);
	
	public boolean createAndMapLabel(LabelDto labelDto, String token, long noteId);
	
	public boolean removeLabel(long labelId, long noteId, String token);
	
	public boolean addLabel(long labelId, long noteId, String token);
	
	public boolean editLabel(long labelId, String token, LabelDto labelDto);
	
	public boolean deletePermanentlyLabel(long labelId, String token);
	
	public List<LabelInfo> getLabels(String token);
	
	public List<NoteInfo> getNotes(long labelId, String token);
}