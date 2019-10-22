package com.coursemanager.service;

import java.sql.SQLException;
import java.util.List;

import com.coursemanager.dao.FileDao;
import com.coursemanager.entity.Course;
import com.coursemanager.entity.Task;
import com.coursemanager.entity.Teacher;

public class FileService {

	public List<Object> FindCourseBySclass(String sclass) throws SQLException {
		FileDao dao = new FileDao();
		return dao.FindCourseBySclass(sclass);
	}

	public List<Course> FindStudentCourse(String sclass) throws SQLException {
		FileDao dao = new FileDao();
		return dao.FindStudentCourse(sclass);
	}

	public List<Teacher> FindTeacherId(String cname, String sclass) throws SQLException {
		FileDao dao = new FileDao();
		return dao.FindTeacherId(cname,sclass);
	}

	public List<Course> FindCid(String cname, String tid,String cclass) throws SQLException {
		FileDao dao = new FileDao();
		return dao.FindCid(cname,tid,cclass);
	}

	public List<Course> FindCourseId(String cname, String sclass) throws SQLException {
		FileDao dao = new FileDao();
		return dao.FindCourseId(cname,sclass);
	}

	
}
