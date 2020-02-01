package com.bridgelabz.fundooNotes.service;

import com.bridgelabz.fundooNotes.dto.LabelDto;

public interface ILabelService {

	public boolean createLabel(LabelDto labelDto, String token);
}
