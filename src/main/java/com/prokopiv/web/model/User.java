package com.prokopiv.web.model;

import java.util.Date;

public class User {
	
	private String userId;
	private String userLogin;
	private String userPassword;	
	private String userRole;	
	private String userlastName;
	private String userPhone;
	private String userMail;
	private String userAdress;
	private String userGender;
	private Date userBirthday;
	private String userEducation;
	private String userDescription;
	private boolean userEnable;
	
	public User() {}
	
	public User(String userId, String userLogin, String userPassword,
			String userRole, String userlastName, String userPhone,
			String userMail, String userAdress, String userGender,
			 String userEducation, String userDescription) {

		this.userId = userId;
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userlastName = userlastName;
		this.userPhone = userPhone;
		this.userMail = userMail;
		this.userAdress = userAdress;
		this.userGender = userGender;
		this.userEducation = userEducation;
		this.userDescription = userDescription;
	}
	
	public boolean isUserEnable() {
		return userEnable;
	}

	public void setUserEnable(boolean userEnable) {
		this.userEnable = userEnable;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserlastName() {
		return userlastName;
	}
	public void setUserlastName(String userlastName) {
		this.userlastName = userlastName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserAdress() {
		return userAdress;
	}
	public void setUserAdress(String userAdress) {
		this.userAdress = userAdress;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
			this.userBirthday = userBirthday;
	}
	public String getUserEducation() {
		return userEducation;
	}
	public void setUserEducation(String userEducation) {
		this.userEducation = userEducation;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}	
}
