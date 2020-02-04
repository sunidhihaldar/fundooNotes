package com.bridgelabz.fundooNotes.service;

import java.util.List;

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

	static final String USER_STATUS = "User not found";
	static final String NOTE_STATUS = "Note not found";
	static final String LABEL_STATUS = "Label doesn't exist";

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
		throw new UserNotFoundException(USER_STATUS);
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
			throw new NoteException(NOTE_STATUS);
		}
		throw new UserNotFoundException(USER_STATUS);
	}

	@Transactional
	@Override
	public boolean removeLabel(long labelId, long noteId, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if (user != null) {
			NoteInfo note = noteRepository.findById(noteId);
			if (note != null) {
				LabelInfo label = labelRepository.findById(labelId);
				if (label != null) {
					note.getLabelList().remove(label);
					noteRepository.save(note);
					return true;
				}
				throw new LabelException(LABEL_STATUS);
			}
			throw new NoteException(NOTE_STATUS);
		}
		throw new UserNotFoundException(USER_STATUS);
	}

	@Transactional
	@Override
	public boolean addLabel(long labelId, long noteId, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if (user != null) {
			NoteInfo note = noteRepository.findById(noteId);
			if (note != null) {
				LabelInfo label = labelRepository.findById(labelId);
				if (label != null) {
					note.getLabelList().add(label);
					labelRepository.save(label);
					return true;
				}
				throw new LabelException(LABEL_STATUS);
			}
			throw new NoteException(NOTE_STATUS);
		}
		throw new UserNotFoundException(USER_STATUS);
	}

	@Transactional
	@Override
	public boolean editLabel(long labelId, String token, LabelDto labelDto) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if (user != null) {
			LabelInfo label = labelRepository.findById(labelId);
			if (label != null) {
				label.setLabelName(labelDto.getLabelName());
				labelRepository.save(label);
				return true;
			}
			throw new LabelException(LABEL_STATUS);
		}
		throw new UserNotFoundException(USER_STATUS);
	}

	@Transactional
	@Override
	public boolean deletePermanentlyLabel(long labelId, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if (user != null) {
			LabelInfo label = labelRepository.findById(labelId);
			if (label != null) {
				labelRepository.deleteLabel(labelId);
				labelRepository.save(label);
				return true;
			}
			throw new LabelException(LABEL_STATUS);
		}
		throw new UserNotFoundException(USER_STATUS);
	}

	@Transactional
	@Override
	public List<LabelInfo> getLabels(String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if (user != null) {
			List<LabelInfo> fetchedLabels = labelRepository.getAllLabels(userId);
			if (!fetchedLabels.isEmpty()) {
				return fetchedLabels;
			}
			return fetchedLabels;
		}
		throw new UserNotFoundException(USER_STATUS);
	}

	@Override
	public List<NoteInfo> getNotes(long labelId, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if (user != null) {
			LabelInfo label = labelRepository.findById(labelId);
			if (label != null) {
				return label.getNoteList();
			}
			throw new LabelException(LABEL_STATUS);
		}
		throw new UserNotFoundException(USER_STATUS);
	}
}