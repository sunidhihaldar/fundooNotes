package com.bridgelabz.fundooNotes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.model.NoteInfo;

/**
 * This class provides repository functionalities like saving a note, find a
 * note by its id and deleting a note
 * 
 * @author Sunidhi Haldar
 * @created 2020-01-24
 * @version 1.8
 */

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class NoteRepository {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public NoteInfo save(NoteInfo note) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(note);
		return note;
	}
	
	public NoteInfo findById(long noteId) {
		Session session = entityManager.unwrap(Session.class);
		Query idQuery = session.createQuery("FROM NoteInfo where noteId=:noteId");
		idQuery.setParameter("noteId", noteId);
		return (NoteInfo) idQuery.uniqueResult();
	}

	public boolean deleteNote(long noteId) {
		Session session = entityManager.unwrap(Session.class);
		Query deleteQuery = session.createQuery("delete from NoteInfo where noteId=:noteId");
		deleteQuery.setParameter("noteId", noteId);
		int result = deleteQuery.executeUpdate();
		if (result > 0)
			return true;
		return false;
	}

	@Transactional
	public List<NoteInfo> getAllNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query selectQuery = session
				.createQuery("FROM NoteInfo where user_id=:id and is_trashed=false and is_archived=false");
		selectQuery.setParameter("id", userId);
		return selectQuery.getResultList();
	}

	@Transactional
	public List<NoteInfo> getAllPinnedNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query selectQuery = session.createQuery("FROM NoteInfo where user_id=:id and is_pinned=true");
		selectQuery.setParameter("id", userId);
		return selectQuery.getResultList();
	}

	@Transactional
	public List<NoteInfo> getAllTrashedNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query selectQuery = session.createQuery("From NoteInfo where user_id=:userId and is_trashed=true");
		selectQuery.setParameter("userId", userId);
		return selectQuery.getResultList();
	}

	@Transactional
	public List<NoteInfo> getAllArchivedNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("FROM NoteInfo where user_id=:userId and is_archived=true")
				.setParameter("userId", userId).getResultList();
	}

	public List<NoteInfo> getAllNotes(String title) {
		return entityManager.unwrap(Session.class).createQuery("FROM NoteInfo where title=:title and is_trashed=false")
				.setParameter("title", title).getResultList();
	}
}