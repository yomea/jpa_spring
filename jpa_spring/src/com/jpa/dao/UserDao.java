package com.jpa.dao;

import java.util.List;

import com.jpa.entity.User;

public interface UserDao {
	
	List<User> findAll();

}
