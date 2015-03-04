package com.prokopiv.web.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.prokopiv.web.dao.UserRole;

@Repository
public class InitializationDataBaseImpl implements InitializationDataBase {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	private List<Integer> userIdList = new ArrayList<Integer>(100);
	private static final int NUMBER_ADMINS_IN_DB = 100;
	private static final int NUMBER_USERS_IN_DB = 100;
	private static final int NUMBER_ALL_USERS_IN_DB = NUMBER_ADMINS_IN_DB + NUMBER_USERS_IN_DB;
	
	@Override
	public boolean uploadData(){	
		uploadDataToUserRole();		
		uploadDataToUserAuthentication();
		uploadDataToUserAuthorization();
		uploadDataToUserGeneral();
		return true;
	}
	
	private boolean uploadDataToUserGeneral(){
		String userGeneralSql = "INSERT INTO user_general (user_id, user_fio, user_phone, user_mail, user_adress, user_gender, "
				+ "user_birthday, user_education, user_description) VALUES(?,?,?,?,?,?,?,?,?);";
		try (Connection connection = dataSource.getConnection();){
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(userGeneralSql);
			for(int i = 0; i < NUMBER_ALL_USERS_IN_DB; i++){
				ps.setInt(1, userIdList.get(i));
				ps.setString(2, "user_fio_" + i);
				ps.setString(3, 1111111111 + i + "");
				ps.setString(4, "user_" + i + "@gmail.com");
				ps.setString(5, "user_adress_" + i);
				ps.setString(6, "Male");
				ps.setDate(7, null);
				ps.setString(8, "Degree");
				ps.setString(9, "user_description_" + i);
				ps.addBatch();
			}
			ps.executeBatch();
			connection.commit();			
		} catch(SQLException e){
			e.printStackTrace();
		}		
		return false;
	}
	
	private boolean uploadDataToUserAuthorization(){
		String insertUserAuthorization = "INSERT INTO user_authorization (user_id, role_id) VALUES (?, ?);";
		try(Connection connection = dataSource.getConnection(); ){
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(insertUserAuthorization);
			Integer userRole = UserRole.ROLE_ADMIN;
			for(int i = 0; i < NUMBER_ALL_USERS_IN_DB; i ++){
				if(i > 25){
					userRole = UserRole.ROLE_REGULAR_USER;
				}
				ps.setInt(1, userIdList.get(i));
				ps.setInt(2, userRole);
				ps.addBatch();
			}
			ps.executeBatch();
			connection.commit();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean uploadDataToUserAuthentication(){
		String insertUserAuthentication = "INSERT INTO user_authentication (user_name, user_password, user_enable) VALUES (?, ?, true);";
		try(Connection connection = dataSource.getConnection(); ){
			connection.setAutoCommit(false);
				PreparedStatement ps = connection.prepareStatement(insertUserAuthentication, Statement.RETURN_GENERATED_KEYS);
				String userName = "admin_";
				for(int i = 0; i < NUMBER_ALL_USERS_IN_DB; i++){
					if(i > NUMBER_ADMINS_IN_DB){
						userName = "user_";
					}
					ps.setString(1, userName + i);
					ps.setString(2, encoder.encode(userName + i));
					ps.executeUpdate();
					ResultSet rs = ps.getGeneratedKeys();
					rs.next();
					userIdList.add(rs.getInt(1));
				}
			connection.commit();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}	
	
	private boolean uploadDataToUserRole(){
		String insertUserRoleSql = "INSERT INTO user_role (role_id, role_name) VALUES (?,?);";
		try(Connection connection = dataSource.getConnection()){
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(insertUserRoleSql);
			ps.setInt(1, UserRole.ROLE_ADMIN);
			ps.setString(2, "ROLE_ADMIN");
			ps.addBatch();
			ps.setInt(1, UserRole.ROLE_REGULAR_USER);
			ps.setString(2, "ROLE_REGULAR_USER");
			ps.addBatch();
			ps.executeBatch();		
			connection.commit();
		} catch(SQLException e){
			
		}
		return true;
	}	
	
	@Override
	public boolean createTables() {
		boolean result = false;
		String dropTableUserRoleSql = "DROP TABLE IF EXISTS user_role;";
		String dropTableUserAuthenticationSql = "DROP TABLE IF EXISTS user_authentication;";
		String dropTableUserAuthorizationSql = "DROP TABLE IF EXISTS user_authorization;";
		String dropTableUserGeneralSql = "DROP TABLE IF EXISTS user_general;";
		String dropTablePersistentSql = "DROP TABLE IF EXISTS persistent_logins;";
		
		String createPesistentSql = "CREATE TABLE persistent_logins (username VARCHAR(64) NOT NULL, series VARCHAR(64) NOT NULL, token VARCHAR(64) NOT NULL, last_used TIMESTAMP NOT NULL, PRIMARY KEY (series));";
		String createUserRoleSql = "CREATE TABLE user_role (ROLE_ID INT NOT NULL, ROLE_NAME VARCHAR(45) NOT NULL, PRIMARY KEY (ROLE_ID), UNIQUE INDEX ROLE_NAME_UNIQUE (ROLE_NAME ASC));"; 
		String createUserAuthenticationSql = "CREATE TABLE user_authentication (USER_ID INT NOT NULL AUTO_INCREMENT, USER_NAME VARCHAR(50) NOT NULL, USER_PASSWORD VARCHAR(60) NOT NULL, USER_ENABLE TINYINT(1) NOT NULL DEFAULT 1, PRIMARY KEY (USER_ID), UNIQUE INDEX USER_NAME_UNIQUE (USER_NAME ASC));";
		String createUserAuthorizationSql = "CREATE TABLE user_authorization ( USER_ID INT NOT NULL, ROLE_ID INT NOT NULL, PRIMARY KEY (USER_ID, ROLE_ID));";
		String createUserGeneralSql = "CREATE TABLE user_general (USER_ID INT NOT NULL, USER_FIO VARCHAR(100) NOT NULL, USER_PHONE VARCHAR(50) NOT NULL, USER_MAIL VARCHAR(50) NULL, USER_ADRESS VARCHAR(250) NULL, USER_GENDER VARCHAR(50) NOT NULL, USER_BIRTHDAY DATE NULL,USER_EDUCATION VARCHAR(50) NOT NULL, USER_DESCRIPTION MEDIUMTEXT NULL, PRIMARY KEY (USER_ID));";
		try(Connection connection = dataSource.getConnection();){
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(dropTableUserRoleSql);
			ps.addBatch();
			ps.addBatch(dropTableUserGeneralSql);
			ps.addBatch(dropTableUserAuthorizationSql);
			ps.addBatch(dropTableUserAuthenticationSql);
			ps.addBatch(dropTablePersistentSql);
			
			ps.addBatch(createUserRoleSql);
			ps.addBatch(createUserAuthorizationSql);
			ps.addBatch(createUserAuthenticationSql);
			ps.addBatch(createUserGeneralSql);
			ps.addBatch(createPesistentSql);
			ps.executeBatch();	
			connection.commit();
			result = !result;
		} catch(SQLException e){
			e.printStackTrace();
		}		
		return false;
	}	
}