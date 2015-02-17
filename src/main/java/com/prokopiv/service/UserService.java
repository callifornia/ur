package com.prokopiv.service;

import java.util.List;

import com.prokopiv.bean.Search;
import com.prokopiv.bean.User;

public interface UserService {
	
	public boolean getUserByLogin(String login);
	public User getUserById(String id);
	public List<User> getUserListBySearch(Search search);
	public List<User> getUserList();
	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String id);
}