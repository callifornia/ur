package com.prokopiv.dao;

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

import com.prokopiv.bean.User;
import com.prokopiv.formvalidation.Pagination;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	DataSource dataSource;		
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	Pagination pagination;
	
	@Override
	public boolean recoveryUser(String id) {
		boolean result = false;
		try (Connection connection = dataSource.getConnection()){
			connection.setAutoCommit(false);
			String sql = "UPDATE user_authentication SET user_enable = true WHERE user_id = ?";
			try(PreparedStatement ps = connection.prepareStatement(sql)){
				Integer userId = Integer.valueOf(id);
				ps.setInt(1, userId);
				ps.executeUpdate();
			} catch(SQLException e){
				connection.rollback();
			}
			connection.commit();
			result = !result;
		} catch(SQLException e){
			e.printStackTrace();
		}		
		return result;
	}
	
	@Override
	public List<User> getUsersByLogin(String login) {
		ArrayList<User> userList = new ArrayList<User>();
		String sql = "SELECT ua.user_id, ur.role_name, ua.user_name, ug.user_fio, ug.user_phone, ua.user_enable, ug.user_mail, ug.user_gender FROM user_authentication as ua, user_general as ug, user_role as ur, user_authorization as uz WHERE ua.user_name like ? AND ua.user_id = ug.user_id AND (ua.user_id = uz.user_id AND uz.role_id = ur.role_id)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, login + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				User user = new User();
				user.setUserId(String.valueOf(rs.getInt("user_id")));
				user.setUserLogin(rs.getString("user_name"));
				user.setUserRole(rs.getString("role_name"));
				user.setUserlastName(rs.getString("user_fio"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserEnable(rs.getBoolean("user_enable"));
				user.setUserMail(rs.getString("user_mail"));
				user.setUserGender(rs.getString("user_gender"));
				userList.add(user);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}		
		return userList;
	}
	
	@Override
	public List<User> getUsersByPhone(String phone) {
		ArrayList<User> userList = new ArrayList<User>();
		String sql = "SELECT ua.user_id, ur.role_name, ua.user_name, ug.user_fio, ug.user_phone, ua.user_enable, ug.user_mail, ug.user_gender FROM user_authentication as ua, user_general as ug, user_role as ur, user_authorization as uz WHERE ug.user_phone like ? AND ua.user_id = ug.user_id AND (ua.user_id = uz.user_id AND uz.role_id = ur.role_id)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, phone + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(String.valueOf(rs.getInt("user_id")));
				user.setUserLogin(rs.getString("user_name"));
				user.setUserRole(rs.getString("role_name"));
				user.setUserlastName(rs.getString("user_fio"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserEnable(rs.getBoolean("user_enable"));
				user.setUserMail(rs.getString("user_mail"));
				user.setUserGender(rs.getString("user_gender"));
				userList.add(user);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}		
		return userList;
	}
	
	@Override
	public List<User> getUsersByLastName(String lastName) {
		ArrayList<User> userList = new ArrayList<User>();
		String sql = "SELECT ua.user_id, ur.role_name, ua.user_name, ug.user_fio, ug.user_phone, ua.user_enable, ug.user_mail, ug.user_gender FROM user_authentication as ua, user_general as ug, user_role as ur, user_authorization as uz WHERE ug.user_fio like ? AND ua.user_id = ug.user_id AND (ua.user_id = uz.user_id AND uz.role_id = ur.role_id)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, lastName + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(String.valueOf(rs.getInt("user_id")));
				user.setUserLogin(rs.getString("user_name"));
				user.setUserRole(rs.getString("role_name"));
				user.setUserlastName(rs.getString("user_fio"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserEnable(rs.getBoolean("user_enable"));
				user.setUserMail(rs.getString("user_mail"));
				user.setUserGender(rs.getString("user_gender"));
				userList.add(user);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}		
		return userList;
	}
	
	@Override
	public boolean userExist(String login) {
		boolean result = true;
		String sql = "SELECT 1 FROM user_authentication WHERE user_name = ?";
		try (Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				result = !result;
			}			
		} catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User getUserById(String id) {
		String sql = "SELECT ua.user_id, ua.user_name, ua.user_enable, ur.role_name,  ug.user_fio, ug.user_phone, ug.user_mail, ug.user_adress, ug.user_gender, ug.user_birthday, ug.user_education, ug.user_description FROM user_authentication as ua, user_general as ug, user_role as ur, user_authorization as uz WHERE ua.user_id = ? AND ua.user_id = ug.user_id AND (ua.user_id = uz.user_id AND uz.role_id = ur.role_id)";
		User user = new User();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, Integer.valueOf(id));
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setUserId(String.valueOf(rs.getInt("user_id")));
			user.setUserLogin(rs.getString("user_name"));
			user.setUserAdress(rs.getString("user_adress"));
			user.setUserBirthday(rs.getDate("user_birthday"));
			user.setUserEnable(rs.getBoolean("user_enable"));
			user.setUserDescription(rs.getString("user_description"));
			user.setUserEducation(rs.getString("user_education"));
			user.setUserGender(rs.getString("user_gender"));
			user.setUserlastName(rs.getString("user_fio"));
			user.setUserMail(rs.getString("user_mail"));
			user.setUserPhone(rs.getString("user_phone"));
			user.setUserRole(rs.getString("role_name"));
		} catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public List<User> getUsers(Pagination pagination) {
		ArrayList<User> userList = new ArrayList<User>();
		String getUsersSql = "SELECT ua.user_id, ua.user_enable, ur.role_name, ua.user_name, ug.user_fio, ug.user_phone, ug.user_mail, ug.user_gender "
									+ "FROM user_authentication as ua, user_general as ug, user_role as ur, user_authorization as uz WHERE ua.user_id = ug.user_id AND (ua.user_id = uz.user_id AND uz.role_id = ur.role_id) limit " + pagination.getLimitOffset() +"," + pagination.getLimitResords()  + ";";
		try (Connection connection = dataSource.getConnection(); 
				Statement st = connection.createStatement();){
			ResultSet rs = st.executeQuery(getUsersSql);
			while (rs.next()){
				User user = new User();			
				user.setUserId(String.valueOf(rs.getInt("user_id")));
				user.setUserLogin(rs.getString("user_name"));
				user.setUserRole(rs.getString("role_name"));
				user.setUserlastName(rs.getString("user_fio"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserEnable(rs.getBoolean("user_enable"));
				user.setUserMail(rs.getString("user_mail"));
				user.setUserGender(rs.getString("user_gender"));
				userList.add(user);
			}
			rs = st.executeQuery("SELECT count(*) from user_authentication");
			rs.next();
			pagination.setTotalRecords(rs.getInt(1));
		} catch(SQLException e){
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public boolean insertUser(User user) {
		boolean result = false;		
		String userAuthenticationSql = "INSERT INTO user_authentication (user_name, user_password, user_enable) VALUES (?, ?, ?);";
		String userAuthorizationSql = "INSERT INTO user_authorization (user_id, role_id) VALUES (?,?);";
		String userGeneralSql = "INSERT INTO user_general (user_id, user_fio, user_phone, user_mail, user_adress, user_gender, "
				+ "user_birthday, user_education, user_description) VALUES(?,?,?,?,?,?,?,?,?);";
		try (Connection connection = dataSource.getConnection()){
			connection.setAutoCommit(false);
			Integer genId = null;
			Integer role = UserRole.ROLE_ADMIN;
			
			try (PreparedStatement ps = connection.prepareStatement(userAuthenticationSql, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = ps.getResultSet();){
				
				ps.setString(1, user.getUserLogin());
				ps.setString(2, encoder.encode(user.getUserPassword()));
				ps.setBoolean(3, true);
				ps.executeUpdate();
				ResultSet res = ps.getGeneratedKeys();
				res.next();
				genId = res.getInt(1);				
			} catch(SQLException e){
				e.printStackTrace();
				connection.rollback();
			}
			try (PreparedStatement ps = connection.prepareStatement(userAuthorizationSql)){
				
				if(!"role_admin".equalsIgnoreCase(user.getUserRole())){
					role = UserRole.ROLE_REGULAR_USER;
				}
				ps.setInt(1, genId);
				ps.setInt(2, role);
				ps.executeUpdate();
			} catch(SQLException e){
				e.printStackTrace();
				connection.rollback();
			}
			try (PreparedStatement ps = connection.prepareStatement(userGeneralSql)){
				ps.setInt(1, genId);
				ps.setString(2, user.getUserlastName());
				ps.setString(3, user.getUserPhone());
				ps.setString(4, user.getUserMail());
				ps.setString(5, user.getUserAdress());
				ps.setString(6, user.getUserGender());
				ps.setDate(7, convertToSqlDate(user.getUserBirthday()));
				ps.setString(8, user.getUserEducation());
				ps.setString(9, user.getUserDescription());
				ps.executeUpdate();
			} catch(SQLException e){
				e.printStackTrace();
				connection.rollback();
			}		
			connection.commit();
			result = !result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public boolean updateUser(User user) {
		boolean result = false;
		String userAuthorizationSql  = "UPDATE user_authorization SET role_id = ? WHERE user_id = ?";
		String userGeneralSql = "UPDATE user_general SET user_fio = ?, user_phone = ?, user_mail = ?, user_adress = ?, user_gender = ?, user_birthday = ?, user_education = ?, user_description = ? WHERE user_id = ?";
		
		try (Connection connection = dataSource.getConnection()){
			java.sql.Date sqlDate = convertToSqlDate(user.getUserBirthday());
			connection.setAutoCommit(false);
			Integer userId = Integer.valueOf(user.getUserId());

			if(user.getUserPassword() != null){
				String userAuthenticationSql = "UPDATE user_authentication SET user_password = ? WHERE user_id = ?";
				try(PreparedStatement ps = connection.prepareStatement(userAuthenticationSql)){
					ps.setString(1, encoder.encode(user.getUserPassword()));					
					ps.setInt(2, userId);
					ps.executeUpdate();
				} catch (SQLException e){
					e.printStackTrace();
					connection.rollback();
				}
			}
			
			try (PreparedStatement ps = connection.prepareStatement(userAuthorizationSql)){
				Integer role = UserRole.ROLE_REGULAR_USER;
				if("ROLE_ADMIN".equalsIgnoreCase(user.getUserRole())){
					role = UserRole.ROLE_ADMIN;
				}
				ps.setInt(1, role);
				ps.setInt(2, userId);
				ps.executeUpdate();
			} catch(SQLException e){
				e.printStackTrace();
				connection.rollback();
			}
			
			try (PreparedStatement ps = connection.prepareStatement(userGeneralSql)){
				ps.setString(1, user.getUserlastName());
				ps.setString(2, user.getUserPhone());
				ps.setString(3, user.getUserMail());
				ps.setString(4, user.getUserAdress());
				ps.setString(5, user.getUserGender());
				ps.setDate(6, sqlDate);
				ps.setString(7, user.getUserEducation());
				ps.setString(8, user.getUserDescription());
				ps.setInt(9, userId);
				ps.executeUpdate();
			} catch(SQLException e){
				e.printStackTrace();
				connection.rollback();
			}
			connection.commit();
			result = !result;
		} catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean result = false;
		String sql = "UPDATE user_authentication SET user_enable = false WHERE user_id = ?";
		try (Connection connection = dataSource.getConnection()){
			connection.setAutoCommit(false);
			Integer userId = Integer.valueOf(id);
			try(PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1, userId);
				ps.executeUpdate();
			} catch (SQLException e){
				e.printStackTrace();
				connection.rollback();
			}
			connection.commit();
			result = !result;
		} catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	private java.sql.Date convertToSqlDate(java.util.Date date){
		return (date != null) ? new java.sql.Date(date.getTime()) : null;
	}
}