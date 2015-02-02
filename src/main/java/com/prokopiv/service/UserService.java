package com.prokopiv.service;

import java.util.List;

import com.prokopiv.bean.User;

public interface UserService {
	
	public User getUserById(String id);
	public List<User> getUserListBySearch(String login);
	public List<User> getUserList();
	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String id);
}