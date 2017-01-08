package com.jpa.service.impl;

import java.util.List;

import com.jpa.dao.UserDao;
import com.jpa.entity.User;
import com.jpa.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

}
