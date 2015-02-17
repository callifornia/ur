package com.prokopiv.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokopiv.bean.Search;
import com.prokopiv.bean.User;
import com.prokopiv.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;

	@Override
	public boolean getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}
	
	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<User> getUserListBySearch(Search search) {
		return userDao.getUserListBySearch(search);
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
		logger.info("UserServiceImpl: delete method. userId: " + id);
		return userDao.deleteUser(id);
	}
}