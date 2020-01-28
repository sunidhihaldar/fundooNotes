package com.bridgelabz.fundooNotes.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNotes.customException.UserNotFoundException;
import com.bridgelabz.fundooNotes.dto.NoteDto;
import com.bridgelabz.fundooNotes.model.NoteInfo;
import com.bridgelabz.fundooNotes.model.UserEntity;
import com.bridgelabz.fundooNotes.repository.IUserRepository;
import com.bridgelabz.fundooNotes.repository.NoteRepository;
import com.bridgelabz.fundooNotes.util.JwtGenerator;

@Service
public class NoteServiceImpl implements INoteService {
	
	@Autowired
    private NoteRepository noteRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private JwtGenerator generate;
	
	NoteInfo noteInfo = new NoteInfo();

	@Override
	public boolean create(NoteDto note, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if(user != null && user.isVerified()) {
			BeanUtils.copyProperties(note, noteInfo);
			noteInfo.setCreatedAt(LocalDateTime.now());
			noteInfo.setPinned(false);
			noteInfo.setArchived(false);
			noteInfo.setTrashed(false);
			noteInfo.setColour("white");
			user.getNote().add(noteInfo);
			noteRepository.save(noteInfo);
			return true;
		}
		throw new UserNotFoundException("User does not exist");
	}
	
	
}
