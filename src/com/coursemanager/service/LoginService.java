package com.coursemanager.service;

import java.sql.SQLException;

import com.coursemanager.dao.LoginDao;
import com.coursemanager.entity.User;

public class LoginService {

	public User UserLogin(String username, String password, String type) throws SQLException {
		LoginDao dao = new LoginDao();
		return dao.UserLogin(username,password,type);
	}
	

}
