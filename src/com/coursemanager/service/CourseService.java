package com.coursemanager.service;

import java.sql.SQLException;
import java.util.List;

import com.coursemanager.dao.CourseDao;
import com.coursemanager.entity.Course;
import com.coursemanager.entity.PageBean;
import com.coursemanager.entity.Searchcourse;
import com.coursemanager.entity.User;

public class CourseService {

	public void addCourse(Course course) throws SQLException {
		CourseDao dao = new CourseDao();
		dao.addCourse(course);
		
	}

	public void delCourseByCid(String cid) throws SQLException {
		CourseDao dao = new CourseDao();
		String[] id = cid.split(",");
		for (String ids : id) {
		dao.delCourseByCid(ids);
		}
	}
	
	public void updateCourse(Course course) throws SQLException {
		CourseDao dao = new CourseDao();
		dao.updateCourse(course);		
	}

	public List<Course> CoursefindPageBean(int currentPage, int pageSize,Searchcourse searchcourse,User user,String date) throws SQLException {
		CourseDao dao = new CourseDao();
		List<Course> courseList = null;
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			courseList = dao.AdminfindCourseList(currentPage,pageSize,searchcourse,date);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String tid = user.getT_id();
			courseList = dao.TeacherfindCourseList(currentPage,pageSize,searchcourse,tid,date);
		}
		if(user.getS_type()!=null&&user.getS_type().trim().equals("2")){
			String sclass = user.getS_class();
			courseList = dao.StudentfindCourseList(currentPage,pageSize,searchcourse,sclass,date);
		}
		return courseList;
	}

	public int getUITotalCount(Searchcourse searchcourse,User user) throws SQLException {
		CourseDao dao = new CourseDao();
		int total = 0;
		List<Course> courseList = null;
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			total = dao.getAdminTotalCount(searchcourse);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String tno = user.getT_no();
			total = dao.getTeacherTotalCount(tno);
		}
		if(user.getS_type()!=null&&user.getS_type().trim().equals("2")){
			String sclass = user.getS_class();
			total = dao.getStudentTotalCount(sclass);
		}
		return total;
	}

	public void add_course(Course course, String t_id) throws SQLException {
		CourseDao dao = new CourseDao();
		dao.add_course(course,t_id);		
		
	}
	
}
