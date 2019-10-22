package com.coursemanager.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.Teacher;
import com.coursemanager.util.DataSourceUtils;

public class FileDao {

	public List<Object> FindCourseBySclass(String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from course where c_class=?";		      
		List<Object> query = runner.query(sql,new ColumnListHandler("c_name"),sclass);
		return query;
	}

	public List<Course> FindStudentCourse(String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from course where c_class=?";
		return runner.query(sql, new BeanListHandler<Course>(Course.class),sclass);
		
	}

	public List<Teacher> FindTeacherId(String cname, String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from teacher t JOIN course c ON t.t_id=c.t_id  where c_class=? and c_name=?";
		return runner.query(sql, new BeanListHandler<Teacher>(Teacher.class),sclass,cname);
	}

	public List<Course> FindCid(String cname, String tid,String cclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from course where c_name=? and t_id=? and c_class=?";
		return runner.query(sql, new BeanListHandler<Course>(Course.class),cname,tid,cclass);
		
	}

	public List<Course> FindCourseId(String cname, String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from course where c_name=? and c_class=?";
		return runner.query(sql, new BeanListHandler<Course>(Course.class),cname,sclass);
	}
	
}
