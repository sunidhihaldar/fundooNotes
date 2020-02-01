package com.bridgelabz.fundooNotes.repository;

import java.util.List;

import com.bridgelabz.fundooNotes.dto.LabelDto;
import com.bridgelabz.fundooNotes.model.LabelInfo;

public interface ILabelRepository {

	public LabelInfo save(LabelDto labelDto);
	
	public LabelInfo fetchLabel(long userId, String labelName);
	
	public LabelInfo getLabelByUserId(long userId);
	
	public boolean deleteLabel(long labelId);
	
	public List<LabelInfo> getAllLabels(long labelId);
	
	public LabelInfo getAllLabel(long labelId);
}
