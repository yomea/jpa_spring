package com.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.jpa.dao.UserDao;
import com.jpa.entity.User;

public class UserDaoImpl implements UserDao {
	
	private EntityManager entityManager;
	
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Override
	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createQuery("select user from User user", User.class);
		List<User> users = query.getResultList();
		
		return users;
		
	}

	

}
