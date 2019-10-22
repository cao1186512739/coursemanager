package com.coursemanager.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.coursemanager.dao.PersonDao;
import com.coursemanager.entity.SearchTeacher;
import com.coursemanager.entity.Student;
import com.coursemanager.entity.Teacher;
import com.coursemanager.entity.ViewStudent;
import com.coursemanager.entity.ViewStudent_update;
import com.coursemanager.entity.ViewTeacher_update;

public class PersonService {

	public void addTeacher(Teacher teacher) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.addTeacher(teacher);
	}

	public void addStudent(Student student) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.addStudent(student);
	}

	public void student_upload(Student student) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.student_upload(student);
	}

	public List<ViewStudent> findStudentData(int currentPage, int pageSize, Map<String, Object> map)
			throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.findStudentData(currentPage, pageSize, map);
	}

	public int findAllStudentTotal() throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.findAllStudentTotal();
	}

	public int findStudentTotal(Map<String, Object> map) throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.findStudentTotal(map);
	}

	public List<SearchTeacher> findTeacherData(int currentPage, int pageSize, Map<String, Object> map)
			throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.findTeacherData(currentPage, pageSize, map);
	}

	public int findAllTeacherTotal() throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.findAllTeacherTotal();
	}

	public int findTeacherTotal(Map<String, Object> map) throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.findTeacherTotal(map);
	}

	public void delStudentByS_id(String s_id) throws SQLException {
		PersonDao dao = new PersonDao();
		String[] id = s_id.split(",");
		for (String ids : id) {
			dao.deleteStudentByS_id(ids);
		}
	}

	public List<ViewStudent_update> findStudentData_update(int currentPage, int pageSize, Map<String, Object> map)
			throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.findStudentData_update(currentPage, pageSize, map);
	}

	public void UpdateStudentInformation(Student student) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.UpdateStudentInformation(student);

	}

	public void delTeacherByT_id(String t_id) throws SQLException {
		PersonDao dao = new PersonDao();
		String[] id = t_id.split(",");
		for (String ids : id) {
			dao.delTeacherByT_id(ids);
		}
		
	}

	public List<ViewTeacher_update> findTeacherData_update(int currentPage, int pageSize,
			Map<String, Object> map) throws SQLException {
		PersonDao dao = new PersonDao();
		return dao.findTeacherData_update(currentPage, pageSize, map);
	}

	public void UpdateTeacherInformation(Teacher teacher) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.UpdateTeacherInformation(teacher);
		
	}

	public void teacher_upload(Teacher teacher) throws SQLException {
		PersonDao dao = new PersonDao();
		dao.teacher_upload(teacher);
	}

}