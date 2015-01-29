package com.prokopiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokopiv.bean.User;
import com.prokopiv.dao.UserDao;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<User> getUserListBySearch(String login) {
		return userDao.getUserListBySearch(login);
	}

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public boolean insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(String id) {
		return userDao.deleteUser(id);
	}
}