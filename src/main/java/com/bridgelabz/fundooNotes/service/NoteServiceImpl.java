package com.bridgelabz.fundooNotes.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNotes.customException.NoteNotFoundException;
import com.bridgelabz.fundooNotes.customException.UserNotFoundException;
import com.bridgelabz.fundooNotes.customException.UserNotVerifiedException;
import com.bridgelabz.fundooNotes.dto.NoteDto;
import com.bridgelabz.fundooNotes.dto.NoteUpdation;
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
	@Transactional
	public boolean createNote(NoteDto note, String token) {
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

	@Transactional
	@Override
	public boolean updateNote(NoteUpdation updateNote, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		NoteInfo note = noteRepository.findById(updateNote.getNoteId());
		if(user != null) {
			if(note != null) {
				BeanUtils.copyProperties(updateNote, user);
				note.setNoteId(updateNote.getNoteId());
				note.setTitle(updateNote.getTitle());
				note.setDescription(updateNote.getDescription());
				note.setArchived(updateNote.isArchived());
				note.setPinned(updateNote.isPinned());
				note.setTrashed(updateNote.isTrashed());
				note.setUpdatedAt(LocalDateTime.now());
				noteRepository.save(note);
				return true;
			}
			throw new NoteNotFoundException("Note not found");
		}
		throw new UserNotVerifiedException("Please verify");
	}

	@Transactional
	@Override
	public boolean deleteNote(long noteId, String token) {
		UserEntity userId = userRepository.getUser(generate.parseJWT(token));
		if(userId != null) {
			NoteInfo note = noteRepository.findById(noteId);
			if(note != null) {
				noteRepository.deleteNote(noteId);
				return true;
			}
			throw new NoteNotFoundException("Note not found");
		}
		throw new UserNotFoundException("User not found");
	}

	@Transactional
	@Override
	public boolean archiveNote(long noteId, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if(user != null) {
			NoteInfo note = noteRepository.findById(noteId);
			if(note != null) {
				if(!note.isArchived()) {
					note.setArchived(true);
					note.setUpdatedAt(LocalDateTime.now());
					noteRepository.save(note);
					return true;
				}
				throw new NoteNotFoundException("note already archived");
			}
			throw new NoteNotFoundException("Note not found");
		}
		throw new UserNotFoundException("User not found");
	}

	@Transactional
	@Override
	public boolean pinNote(long noteId, String token) {
		long userId = generate.parseJWT(token);
		UserEntity user = userRepository.getUser(userId);
		if(user != null) {
			NoteInfo note = noteRepository.findById(noteId);
			if(note != null) {
				if(!note.isPinned()) {
					note.setPinned(true);
					note.setUpdatedAt(LocalDateTime.now());
					noteRepository.save(note);
					return true;
				}
				throw new NoteNotFoundException("note already pinned");
			}
			throw new NoteNotFoundException("Note not found");
		}
		throw new UserNotFoundException("User not found");
	}

	
}
