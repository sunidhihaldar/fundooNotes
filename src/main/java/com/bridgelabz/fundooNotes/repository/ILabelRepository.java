package com.bridgelabz.fundooNotes.repository;

import java.util.List;

import com.bridgelabz.fundooNotes.model.LabelInfo;

public interface ILabelRepository {

	public LabelInfo save(LabelInfo label);

	public LabelInfo fetchLabel(long userId, String labelName);

	public LabelInfo getLabelById(long labelId);

	public boolean deleteLabel(long labelId);

	public List<LabelInfo> getAllLabels(long userId);

	public LabelInfo getLabel(long labelId);
}
