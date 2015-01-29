package com.prokopiv.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.prokopiv.bean.User;
import com.prokopiv.bean.UserListTemp;
import com.prokopiv.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public User getUserById(String id) {
		return new UserListTemp().makeUserList().get(Integer.valueOf(id));
	}

	@Override
	public List<User> getUserListBySearch(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserList() {
		return new UserListTemp().makeUserList();
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
