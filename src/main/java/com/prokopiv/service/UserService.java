package com.prokopiv.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.prokopiv.bean.User;

public interface UserService {
	
	public User getUserById(String id);
	public List<User> getUserListBySearch(String login);
	public List<User> getUserList();
	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String id);
}