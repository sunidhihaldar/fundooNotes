package com.bridgelabz.fundooNotes.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.model.NoteInfo;

@Repository
@SuppressWarnings("rawtypes")
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
		Query deleteQuery =session.createQuery("delete from NoteInfo where noteId=:noteId");
		deleteQuery.setParameter("noteId", noteId);
		int result = deleteQuery.executeUpdate();
		if(result > 0)
			return true;
		return false;
	}
}
