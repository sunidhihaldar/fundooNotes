package com.bridgelabz.fundooNotes.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNotes.customException.LabelException;
import com.bridgelabz.fundooNotes.customException.NoteException;
import com.bridgelabz.fundooNotes.customException.UserNotFoundException;
import com.bridgelabz.fundooNotes.dto.LabelDto;
import com.bridgelabz.fundooNotes.model.LabelInfo;
import com.bridgelabz.fundooNotes.model.NoteInfo;
import com.bridgelabz.fundooNotes.model.UserEntity;
import com.bridgelabz.fundooNotes.repository.ILabelRepository;
import com.bridgelabz.fundooNotes.repository.IUserRepository;
import com.bridgelabz.fundooNotes.repository.NoteRepository;
import com.bridgelabz.fundooNotes.util.JwtGenerator;

@Service
public class LabelServiceImpl implements ILabelService {

	@Autowired
	private ILabelRepository labelRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private JwtGenerator generate;

	@Transactional
	@Override
	public boolean createLabel(LabelDto labelDto, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		LabelInfo labelInfo = new LabelInfo();
		if (user != null) {
			LabelInfo label = labelRepository.fetchLabel(user.getUserId(), labelDto.getLabelName());
			if (label == null) {
				BeanUtils.copyProperties(labelDto, labelInfo);
				user.getLabels().add(labelInfo);
				labelRepository.save(labelInfo);
				return true;
			}
			throw new LabelException("Label already exists");
		}
		throw new UserNotFoundException("User not found");
	}

	@Transactional
	@Override
	public boolean createAndMapLabel(LabelDto labelDto, String token, long noteId) {
		UserEntity fetchedUser = userRepository.getUser(generate.parseJWT(token));
		if (fetchedUser != null) {
			NoteInfo fetchedNote = noteRepository.findById(noteId);
			if (fetchedNote != null) {
				LabelInfo fetchedLabel = labelRepository.fetchLabel(fetchedUser.getUserId(), labelDto.getLabelName());
				if (fetchedLabel == null) {
					LabelInfo newLabel = new LabelInfo();
					BeanUtils.copyProperties(labelDto, newLabel);
					fetchedUser.getLabels().add(newLabel);
					fetchedNote.getLabelList().add(newLabel);
					labelRepository.save(newLabel);
					return true;
				}
				throw new LabelException("Label already exists");
			}
			throw new NoteException("Note not found");
		}
		throw new UserNotFoundException("User not found");
	}

	@Transactional
	@Override
	public boolean removeLabel(long labelId, long noteId, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if (user != null) {
			NoteInfo note = noteRepository.findById(noteId);
			if(note != null) {
				LabelInfo label = labelRepository.getLabelById(labelId);
				if (label != null) {
					note.getLabelList().remove(label);
					noteRepository.save(note);
					return true;
				}
				throw new LabelException("Label doesn't exist");
			}
			throw new NoteException("Note not found");
		}
		throw new UserNotFoundException("User not found");
	}
}
