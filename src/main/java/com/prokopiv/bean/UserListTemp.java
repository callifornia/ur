package com.prokopiv.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserListTemp {

	private static List<User> list = new ArrayList<User>();
	private static final Logger logger = LogManager.getLogger(UserListTemp.class);
	
	public User getUserById(String id) {
		for (User user : list) {
			if (user.getUserId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getUserList() {
		return list;
	}

	public boolean inserUser(User user) {
		list.add(user);
		return true;
	}

	public boolean deleteUser(String id) {
		logger.info("UserListTemp: delete user with id: " + id + "listSize before: " + list.size() );
		int userIndex = -1;
		for(User user : list){
			if(user.getUserId().equalsIgnoreCase(id)){
				logger.info("Find this user");
				userIndex = list.indexOf(user);
			}
		}
		if(userIndex != -1){
			list.remove(userIndex);
		} else{
			System.out.print("asd");
		}
		logger.info("UserListTemp: delete user with id: " + id + "listSize after: " + list.size() );
		return true;
	}

	public boolean updateUser(User user) {
		if (user == null) {
			return false;
		}
		for (User oldUser : list) {
			if (oldUser.getUserId().equalsIgnoreCase(user.getUserId())) {
				oldUser.setUserAdress(user.getUserAdress());
				oldUser.setUserBirthday(user.getUserAdress());
				oldUser.setUserDescription(user.getUserDescription());
				oldUser.setUserEducation(user.getUserEducation());
				oldUser.setUserGender(user.getUserGender());
				oldUser.setUserlastName(user.getUserlastName());
				oldUser.setUserMail(user.getUserMail());
				oldUser.setUserPassword(user.getUserPassword());
				oldUser.setUserPhone(user.getUserPhone());
				oldUser.setUserRole(user.getUserRole());
			}
		}
		return true;
	}

	private List<User> makeUserList() {
		for (int i = 1; i < 100; i++) {
			User user = new User("" + i, "adminLogin_" + i,
					"adminPassword_" + i, "adminRole_" + i,
					"userLastName_" + i, "userPhone_" + i, "userMail_" + i,
					"userAdress_" + i, "userGender_" + i, "userBirthDay_" + i,
					"userEducation_" + i, "userDescription_" + i);
			list.add(user);
		}
		return list;
	}
}