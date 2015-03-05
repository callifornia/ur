package com.prokopiv.web.service;

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
	
	@Autowired UserDao userDao;
	
	@Autowired InitializationDataBaseImpl initialization;

	@Override
	public boolean userExist(String login) {
		boolean result = true;
		try{
			result = userDao.userExist(login);
		} catch(DataBaseException ex){
			logger.warn("Can't execute method userExist()", ex);
		}
		return result; 
	}
	
	@Override
	public boolean initializationDataBase() {
		boolean result = true;
		try {
			initialization.createTables();
			initialization.uploadData();
		} catch (DataBaseException ex){
			logger.warn("Can't initialize tables", ex);
			result = !result;
		}
		return result;
	}
	
	@Override
	public User getUserById(String id) {
		User user = null;
		try{
			user = userDao.getUserById(id);
		} catch(DataBaseException ex){
			logger.warn("Can't execute method getUserById()", ex);
		}
		return user; 
	}
	
	@Override
	public boolean recoveryUser(String id) {
		try{
			userDao.recoveryUser(id);
		} catch(DataBaseException e){
			logger.warn("Can't recovery user with id: " + id, e);			
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
		try {			
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
		} catch (DataBaseException ex){
			logger.warn("Can't execute method: getUserBySearch() with search param:" + search.toString(), ex);
		}
		return usersList;
	}

	@Override
	public List<User> getUserList(Pagination pagination) {
		List<User> usersList = null;
		try{
			usersList =  userDao.getUsers(pagination);
		} catch (DataBaseException ex){
			logger.warn("Can't execute method: getUserList()", ex);
		}
		return  usersList;
	}

	@Override
	public boolean insertUser(User user) {
		boolean result = true;
		try{
			userDao.insertUser(user);
		} catch (DataBaseException ex){
			logger.warn("Can't execute method insertUser()", ex);
			result = !result;
		}
		return result;
	}

	@Override
	public boolean updateUser(User user) {
		boolean result = true;
		try{
			userDao.updateUser(user);
		} catch(DataBaseException ex){
			logger.warn("Can't execute method updateUser(): " + user.toString(), ex);
		}
		return result;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean result = true;
		try{
			userDao.deleteUser(id);
		} catch(DataBaseException ex){
			logger.warn("Can't execute deleteUser with id: " + id, ex);
			result = !result;
		}
		return result;
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