package com.prokopiv.web.dao;

public final class UserRole {
	
	private static final UserRole instance = new UserRole();
	
	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_REGULAR_USER = 2;
	
	private UserRole() {}
	
	public static UserRole getInstance(){
		return instance;
	}
}
