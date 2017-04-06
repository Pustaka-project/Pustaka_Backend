package com.pustaka.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pustaka.dao.UserDao;
import com.pustaka.dto.LoginDTO;
import com.pustaka.dto.UserDTO;
import com.pustaka.entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public boolean findUserByEmailAndMobile(UserDTO user) {

		boolean userExists = false;
		Session session = sessionfactory.getCurrentSession();
		Criterion mobileCriteria = Restrictions.eq("mobile", user.getMobile());
		Criterion emailCriteria = Restrictions.eq("email", user.getEmail());

		Criteria cr = session.createCriteria(UserEntity.class).add(emailCriteria).add(mobileCriteria);
		if (!cr.list().isEmpty()) {
			userExists = true;
		}

		return userExists;
	}

	@Override
	public long addUser(UserEntity user) {
      
		Session session=sessionfactory.getCurrentSession();
		return (long) session.save(user);
	}
	
	@Override
	public UserEntity authenticateUser(LoginDTO loginDTO) {
		Session session = sessionfactory.getCurrentSession();
		Criterion passwordCriteria = Restrictions.eq("password", loginDTO.getPassword());
		Criterion emailCriteria = Restrictions.eq("email", loginDTO.getEmail());

		Criteria cr = session.createCriteria(UserEntity.class).add(emailCriteria).add(passwordCriteria);
		return (UserEntity) cr.uniqueResult();

	}
}