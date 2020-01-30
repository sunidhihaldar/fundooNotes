package com.bridgelabz.fundooNotes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.model.NoteInfo;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class NoteRepository {

	@Autowired
	private EntityManager entityManager;

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
				.createQuery("FROM NoteInfo where user_id=:userId and is_trashed=false and is_archived=false");
		selectQuery.setParameter("userId", userId);
		return selectQuery.getResultList();
	}

	@Transactional
	public List<NoteInfo> getAllPinnedNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query selectQuery = session.createQuery("FROM NoteInfo where user_id=:userId and is_pinned=true");
		selectQuery.setParameter("userId", userId);
		return selectQuery.getResultList();
	}

	@Transactional
	public List<NoteInfo> getAllTrashedNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("From NoteInfo where user_id=:userId and is_trashed=true")
				.setParameter("userId", userId).getResultList();
	}

	@Transactional
	public List<NoteInfo> getAllArchivedNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("FROM NoteInfo where user_id=:userId and is_archived=true")
				.setParameter("userId", userId).getResultList();
	}
}
