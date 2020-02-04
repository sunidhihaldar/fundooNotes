package com.bridgelabz.fundooNotes.repository;

import java.util.List;

import com.bridgelabz.fundooNotes.model.LabelInfo;

/**
 * This interface provides unimplemented methods for label like saving label information, fetching label
 * @author Sunidhi Haldar
 * @created 2020-02-01
 * @version 1.8
 */

public interface ILabelRepository {

	public LabelInfo save(LabelInfo label);

	public LabelInfo fetchLabel(long userId, String labelName);

	public LabelInfo findById(long labelId);

	public boolean deleteLabel(long labelId);

	public List<LabelInfo> getAllLabels(long userId);

	public LabelInfo getLabel(long labelId);

	public LabelInfo getLabel(String labelName);
}