package com.prokopiv.service;

import java.util.List;

import org.springframework.ui.Model;

import com.prokopiv.bean.Search;
import com.prokopiv.bean.User;
import com.prokopiv.formvalidation.Pagination;

public interface UserService {
	
	public boolean initializationDataBase();
	
	public boolean userExist(String login);
	public User getUserById(String id);
	public List<User> getUserBySearch(Search search, Pagination pagination);
	public List<User> getUserList(Pagination pagination);

	public void recoveryUser(String id);
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(String id);
	
	public void setFormList(Model model);
	public void setUserAndSearchAttributes(Model model, User user, Search search);
	public void setUserAndSearchAttributes(Model model, List<User> user, Search search);
}