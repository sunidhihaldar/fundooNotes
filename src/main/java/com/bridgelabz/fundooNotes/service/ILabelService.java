package com.bridgelabz.fundooNotes.service;

import com.bridgelabz.fundooNotes.dto.LabelDto;

public interface ILabelService {

	public boolean createLabel(LabelDto labelDto, String token);
	
	public boolean createAndMapLabel(LabelDto labelDto, String token, long noteId);
	
	public boolean removeLabel(long labelId, long noteId, String token);
}
