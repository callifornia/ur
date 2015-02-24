package com.prokopiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prokopiv.bean.Search;
import com.prokopiv.bean.User;
import com.prokopiv.dao.UserDao;
import com.prokopiv.formvalidation.Pagination;
import com.prokopiv.initialization.InitializationDataBaseImpl;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	InitializationDataBaseImpl initialization;

	@Override
	public boolean userExist(String login) {
		return userDao.userExist(login);
	}
	
	@Override
	public boolean initializationDataBase() {
		initialization.createTables();
		initialization.uploadData();
		return true;
	}
	
	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}
	
	@Override
	public boolean recoveryUser(String id) {
		return userDao.recoveryUser(id);
	}

	@Override
	public List<User> getUserBySearch(Search search, Pagination pagination) {
		List<User> usersList = null;
		switch(search.getSearchType()){
			case "login" : usersList = userDao.getUsersByLogin(search.getSearchRow());
			break;
			case "phone" : usersList = userDao.getUsersByPhone(search.getSearchRow());
			break;
			case "lastName" : usersList = userDao.getUsersByLastName(search.getSearchRow());
			break;
			case "all" : usersList = userDao.getUsers(pagination);
			break;
		}
		return usersList;
	}

	@Override
	public List<User> getUserList(Pagination pagination) {
		return userDao.getUsers(pagination);
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