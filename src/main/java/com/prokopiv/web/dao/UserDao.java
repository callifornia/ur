package com.prokopiv.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.prokopiv.web.exception.DataBaseException;
import com.prokopiv.web.model.User;
import com.prokopiv.web.validation.Pagination;

public interface UserDao {
	
	public void recoveryUser(String id) throws DataBaseException;
	public boolean userExist(String login);

	public User getUserById(String id);
	public List<User> getUsersByLogin(String login, Pagination pagination);
	public List<User> getUsersByPhone(String phone, Pagination pagination);
	public List<User> getUsersByLastName(String lastName, Pagination pagination);
	public List<User> getUsers(Pagination pagination);
	
	public void insertUser(User user) throws SQLException;
	public void updateUser(User user);
	public void deleteUser(String id);
	
}
