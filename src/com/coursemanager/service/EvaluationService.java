package com.coursemanager.service;

import java.sql.SQLException;
import java.util.List;

import com.coursemanager.dao.EvaluationDao;
import com.coursemanager.entity.Course;
import com.coursemanager.entity.Evaluation;
import com.coursemanager.entity.Teacher;
import com.coursemanager.entity.User;
import com.coursemanager.entity.ViewEvaluationResultTeacher;

public class EvaluationService {

	public List<Course> findCourseByS_class(String s_class) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findCourseByS_class(s_class);

	}

	public void ProcessEvaluation(Evaluation evaluation, User user) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		dao.ProcessEvaluation(evaluation, user);

	}

	public List<Course> findCourseByT_id(User user) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findCourseByT_id(user);

	}

	public List<ViewEvaluationResultTeacher> findEvaluationResultByC_id(int currentPage, int pageSize, String c_id) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findEvaluationResultByC_id(currentPage,pageSize,c_id);

	}

	public int findEvaluationResultByC_idTotal(String c_id) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findEvaluationResultByC_idTotal(c_id);
	}

	public List<Course> findCourseByS_id(User user) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findCourseByS_i(user);
	}

	public List<ViewEvaluationResultTeacher> findEvaluationResultByS_id(int currentPage,
			int pageSize, User user, String c_id) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findEvaluationResultByS_id(currentPage,pageSize,user,c_id);
	}

	public int findEvaluationResultByS_idTotal(User user, String c_id) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findEvaluationResultByS_idTotal(user,c_id);

	}

	public List<Course> findCourseName() throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findCourseName();
	}

	public List<Teacher> findTeacherNameByC_name(String c_name) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findTeacherNameByC_name(c_name);
	
	}
	
	public List<ViewEvaluationResultTeacher> findEvaluationResultByC_Name_T_id(int currentPage,
			int pageSize, String c_name, String t_id) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findEvaluationResultByC_Name_T_id(pageSize,currentPage,t_id,c_name);
		
	}

	public int findEvaluationResultByC_Name_T_idTotal(String c_name, String t_id) throws SQLException {
		EvaluationDao dao = new EvaluationDao();
		return dao.findEvaluationResultByC_Name_T_idTotal(c_name,t_id);
	}

	

}
