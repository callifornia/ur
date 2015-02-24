package com.prokopiv.service;

import java.util.List;

import com.prokopiv.bean.Search;
import com.prokopiv.bean.User;
import com.prokopiv.formvalidation.Pagination;

public interface UserService {
	
	public boolean initializationDataBase();
	public boolean recoveryUser(String id);
	public boolean userExist(String login);
	public User getUserById(String id);
	public List<User> getUserBySearch(Search search, Pagination pagination);
	public List<User> getUserList(Pagination pagination);
	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String id);
}