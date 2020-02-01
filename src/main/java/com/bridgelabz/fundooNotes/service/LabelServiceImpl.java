package com.bridgelabz.fundooNotes.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNotes.dto.LabelDto;
import com.bridgelabz.fundooNotes.model.LabelInfo;
import com.bridgelabz.fundooNotes.model.UserEntity;
import com.bridgelabz.fundooNotes.repository.ILabelRepository;
import com.bridgelabz.fundooNotes.repository.IUserRepository;
import com.bridgelabz.fundooNotes.util.JwtGenerator;

@Service
public class LabelServiceImpl implements ILabelService {

	@Autowired
	private ILabelRepository labelRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private JwtGenerator generate;
	
	@Override
	public boolean createLabel(LabelDto labelDto, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if(user != null && user.isVerified()) {
			LabelInfo label = new LabelInfo();
			BeanUtils.copyProperties(labelDto, label);
			label.setUserId(user.getUserId());
			labelRepository.save(label);
			return true;
		}
		return false;
	}

}
