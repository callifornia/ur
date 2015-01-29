package com.prokopiv.bean;

import java.util.ArrayList;
import java.util.List;

public class UserListTemp {

	public List<User> makeUserList(){
		List<User> list = new ArrayList<User>();
		for(int i = 1; i < 25; i++){
			User user = new User(" "+ i + " ", "adminLogin_" + i, "adminPassword_" + i, "adminRole_" + i, 
					"userLastName_" + i, "userPhone_" + i, "userMail_" + i, "userAdress_" + i, "userGender_" + i, "userBirthDay_" + i, 
					"userEducation_" + i, "userDescription_" + i);
			list.add(user);
		}
		return list;		
	}
	
}
