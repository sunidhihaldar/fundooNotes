package com.bridgelabz.fundooNotes.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.dto.NoteDto;
import com.bridgelabz.fundooNotes.model.NoteInfo;

@Repository
@SuppressWarnings("rawtypes")
public class NoteRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public NoteInfo save(NoteInfo note) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(note);
		return note;
	}
	
	public NoteDto findById(long noteId) {
		Session session = entityManager.unwrap(Session.class);
		Query idQuery = session.createQuery("FROM NoteInfo where note_id=:noteId");
		idQuery.setParameter("note_id", noteId);
		return (NoteDto) idQuery.uniqueResult();
	}
	
	public boolean deleteNote(long noteId) {
		Session session = entityManager.unwrap(Session.class);
		Query deleteQuery =session.createQuery("delete from NoteInfo where note_id=:noteId");
		deleteQuery.setParameter("note_id", noteId);
		int result = deleteQuery.executeUpdate();
		if(result > 0)
			return true;
		return false;
//		return (result > 0) ? true : false;
	}
}
