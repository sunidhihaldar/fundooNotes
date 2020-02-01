package com.bridgelabz.fundooNotes.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.model.LabelInfo;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class LabelRepository implements ILabelRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public LabelInfo save(LabelInfo label) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(label);
		return label;
	}

	@Override
	public LabelInfo fetchLabel(long userId, String labelName) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("FROM LabelInfo where user_id=:userId and labelName=:labelName");
		query.setParameter("userId", userId);
		query.setParameter("labelName", labelName);
		return (LabelInfo) query.uniqueResult();
	}

	@Override
	public LabelInfo getLabelByUserId(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("FROM LabelInfo where user_id=:userId");
		query.setParameter("userId", userId);
		return (LabelInfo) query.uniqueResult();
	}

	@Override
	public boolean deleteLabel(long labelId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from LabelInfo where labelId=:labelId");
		query.setParameter("labelId", labelId);
		int affectedRows = query.executeUpdate();
		if(affectedRows > 0)
			return true;
		return false;
	}

	@Override
	public List<LabelInfo> getAllLabels(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("FROM LabelInfo where user_id=:userId");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public LabelInfo getLabel(long labelId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("FROM LabelInfo where labelId=:labelId");
		query.setParameter("labelId", labelId);
		return (LabelInfo) query.uniqueResult();
	}
}
