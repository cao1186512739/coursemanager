package com.coursemanager.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.coursemanager.entity.User;
import com.coursemanager.util.DataSourceUtils;

public class LoginDao {

	public User UserLogin(String username, String password, String type) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "";
		if (type!=null&&type.trim().equals("0")) {
			 sql = "select * from admin where a_no=? and a_password=? and a_type=?";			
		}
		if (type!=null&&type.trim().equals("1")) {
			 sql = "select * from teacher where t_no=? and t_password=? and t_type=?";		
		} 
		if (type!=null&&type.trim().equals("2")) {
			 sql = "select * from student where s_no=? and s_password=? and s_type=?";			
		}
		return runner.query(sql, new BeanHandler<User>(User.class), username,password,type);			
	}

}
