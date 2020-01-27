package com.bridgelabz.fundooNotes.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.dto.NoteDto;

@Repository
public class NoteRepository {

	@Autowired
	private EntityManager entityManager;
	
	public NoteDto save(NoteDto note) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(note);
		return note;
	}
	
//	public NoteDto findById(long userId) {
//		Session session = entityManager.unwrap(Session.class);
//		Query query = session.createQuery("");
//		return userId;
//	}
}
