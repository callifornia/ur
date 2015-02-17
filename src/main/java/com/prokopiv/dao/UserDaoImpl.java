package com.prokopiv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prokopiv.bean.Search;
import com.prokopiv.bean.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	@Autowired
	UserListTemp userListTemp;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	User user;
	
	@Override
	public boolean getUserByLogin(String login) {
		boolean result = true;
		String sql = "SELECT 1 FROM user_authentication WHERE user_name = ?";
		PreparedStatement pr = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = dataSource.getConnection();
			pr = connection.prepareStatement(sql);
			pr.setString(1, login);
			rs = pr.executeQuery();
			while(rs.next()){
				logger.info("rs: " + rs + ", rs.getRow(): " + rs.getRow());
			}
		} catch (SQLException e){

		}				
		return result;
	}

	@Override
	public User getUserById(String id) {
//		String sql = "SELECT * FROM users WHERE userid = ?";
//		PreparedStatement pr = null;
//		ResultSet rs = null;
//		try {
//			Connection conn = dataSource.getConnection();
//			pr = conn.prepareStatement(sql);
//			pr.setString(1, id);
//			rs = pr.executeQuery();
//			while(rs.next()){
//				user.setUserId(rs.getString("userid"));
//				user.setUserLogin(rs.getString("login"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return userListTemp.getUserById(id);
	}

	@Override
	public List<User> getUserListBySearch(Search search) {
		return userListTemp.getUserListBySearch(search);
	}

	@Override
	public List<User> getUserList() {
		return new UserListTemp().getUserList();
	}

	@Override
	public boolean insertUser(User user) {
		userListTemp.inserUser(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		return userListTemp.updateUser(user);
	}

	@Override
	public boolean deleteUser(String id) {
		return userListTemp.deleteUser(id);
	}
}