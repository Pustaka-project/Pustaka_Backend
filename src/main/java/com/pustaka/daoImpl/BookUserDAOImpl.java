package com.pustaka.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pustaka.dao.BookUserDAO;
import com.pustaka.entity.BookUserEntity;

@Repository
public class BookUserDAOImpl implements BookUserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public long addMappingforBookUser(BookUserEntity bookUser) {
		Session session = sessionFactory.getCurrentSession();
		return (long) session.save(bookUser);
	}

	@Override
	public List<Long> getUserBookMappingByUserId(long userId) {
		Session session = sessionFactory.getCurrentSession();
		Criterion userCriteria = Restrictions.eq("userId", userId);
		Criterion statusCriteria = Restrictions.eq("status", 1);
		
		Criteria cr = session.createCriteria(BookUserEntity.class).add(userCriteria).add(statusCriteria);
		
		cr.setProjection(Projections.property("bookId"));
      
		return cr.list();
	}

}
