package com.prokopiv.web.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.prokopiv.web.config.InitializationDataBaseImpl;
import com.prokopiv.web.dao.UserDao;
import com.prokopiv.web.exception.DataBaseException;
import com.prokopiv.web.model.Search;
import com.prokopiv.web.model.User;
import com.prokopiv.web.validation.Pagination;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
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
		try{
			userDao.recoveryUser(id);
		} catch(DataBaseException e){
			logger.warn("Can't recovery user with id: " + id, e);
			return false;
		}
		return true;
	}

	@Override
	public List<User> getUserBySearch(Search search, Pagination pagination) {
		List<User> usersList = null;
		if(search.getSearchRow() != null  && search.getSearchRow().isEmpty()  && !"all".equalsIgnoreCase(search.getSearchType())){
			pagination.setTotalRecords(0);
			return usersList;
		}
		switch(search.getSearchType()){
			case "login" : usersList = userDao.getUsersByLogin(search.getSearchRow(), pagination);
			break;
			case "phone" : usersList = userDao.getUsersByPhone(search.getSearchRow(), pagination);
			break;
			case "lastName" : usersList = userDao.getUsersByLastName(search.getSearchRow(), pagination);
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
	public void insertUser(User user) {
		try{
			
			
			
			
			userDao.insertUser(user);
		} catch (SQLException e){
			
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}
	
	@Override
	public void setUserAndSearchAttributes(Model model, User user, Search search) {
		model.addAttribute("user", user);
		model.addAttribute("search", search);
	}
	@Override
	public void setUserAndSearchAttributes(Model model, List<User> user, Search search) {
		model.addAttribute("user", user);
		model.addAttribute("search", search);
	}
	
	@Override
	public void setFormList(Model model) {
			List<String> userGender = new ArrayList<String>(); 
			userGender.add("Male");
			userGender.add("Female");		
			
			Map<String, String> search = new HashMap<String,String>();
			
			search.put("all", "all");
			search.put("phone", "Телефон");
			search.put("lastName", "ФИО");
			search.put("login", "Логин");
			
			Map<String,String> userRole = new HashMap<String,String>();
			userRole.put("ROLE_ADMIN", "Админ");
			userRole.put("ROLE_REGULAR_USER", "Пользователь");
			
			List<String> userEducation = new ArrayList<String>();
			userEducation.add("Degree");
			userEducation.add("Master Degree");
			userEducation.add("Other");
			
			model.addAttribute("searchName", search);
			model.addAttribute("roles", userRole);
			model.addAttribute("genders", userGender);
			model.addAttribute("education", userEducation);
	}
}