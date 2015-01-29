package com.prokopiv.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prokopiv.bean.User;
import com.prokopiv.bean.UserListTemp;
import com.prokopiv.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	UserListTemp userListTemp;

	@Override
	public User getUserById(String id) {
		return userListTemp.getUserById(id);
	}

	@Override
	public List<User> getUserListBySearch(String login) {
		return userListTemp.getUserList();
	}

	@Override
	public List<User> getUserList() {
		return new UserListTemp().getUserList();
	}

	@Override
	public boolean insertUser(User user) {
		userListTemp.inserUser(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		return userListTemp.updateUser(user);
	}

	@Override
	public boolean deleteUser(String id) {
		return userListTemp.deleteUser(id);
	}
}