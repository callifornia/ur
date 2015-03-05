package com.prokopiv.web.dao;

import java.util.List;

import com.prokopiv.web.exception.DataBaseException;
import com.prokopiv.web.model.User;
import com.prokopiv.web.validation.Pagination;

public interface UserDao {
	
	public void recoveryUser(String id) throws DataBaseException;
	public boolean userExist(String login) throws DataBaseException;

	public User getUserById(String id) throws DataBaseException;
	public List<User> getUsersByLogin(String login, Pagination pagination) throws DataBaseException;
	public List<User> getUsersByPhone(String phone, Pagination pagination) throws DataBaseException;
	public List<User> getUsersByLastName(String lastName, Pagination pagination) throws DataBaseException;
	public List<User> getUsers(Pagination pagination) throws DataBaseException;
	
	public void insertUser(User user) throws DataBaseException;
	public void updateUser(User user) throws DataBaseException;
	public void deleteUser(String id) throws DataBaseException;
	
}
