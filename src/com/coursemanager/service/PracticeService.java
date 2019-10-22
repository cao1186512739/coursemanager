package com.coursemanager.service;

import java.sql.SQLException;
import java.util.List;

import com.coursemanager.dao.PracticeDao;
import com.coursemanager.entity.Course;
import com.coursemanager.entity.Grade;
import com.coursemanager.entity.GradeInfo;
import com.coursemanager.entity.GradeInfo2;
import com.coursemanager.entity.Searchtask;
import com.coursemanager.entity.Student;
import com.coursemanager.entity.Task;
import com.coursemanager.entity.User;

public class PracticeService {

	
	
	public List<Course> findAllCourse() throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findAllCourse();
	}

	public List<Course> findAllClass() throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findAllClass();
	}
	
	public List<Task> findStudentCourse(String sclass) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findStudentCourse(sclass);
	}
	
	public List<Course> findTeacherCourse(String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findTeacherCourse(tid);
	}

	public List<Course> findTeacherClass(String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findTeacherClass(tid);
	}
	
	public void addTask(Task task) throws SQLException {
		PracticeDao dao = new PracticeDao();
		dao.addTask(task);		
	}

	public List<Course> findCidByNameAndClass(String tname, String tclass) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findCidByNameAndClass(tname,tclass);
	}

	public List<Task> TaskfindPageBean(int currentPage, int pageSize, Searchtask searchtask, User user) throws SQLException {
		PracticeDao dao = new PracticeDao();
		List<Task> taskList = null;
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			taskList = dao.AdminfindTaskList(currentPage,pageSize,searchtask);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String tid = user.getT_id();
			taskList = dao.TeacherfindTaskList(currentPage,pageSize,searchtask,tid);
		}
		if(user.getS_type()!=null&&user.getS_type().trim().equals("2")){
			String sclass = user.getS_class();
			taskList = dao.StudentfindTaskList(currentPage,pageSize,searchtask,sclass);
		}
		return taskList;
	}

	public int getTaskTotalCount(Searchtask searchtask, User user) throws SQLException {
		PracticeDao dao = new PracticeDao();
		int total = 0;
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			total = dao.getAdminTotalCount(searchtask);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String tid = user.getT_id();
			total = dao.getTeacherTotalCount(searchtask,tid);
		}
		if(user.getS_type()!=null&&user.getS_type().trim().equals("2")){
			String sclass = user.getS_class();
			total = dao.getStudentTotalCount(searchtask,sclass);
		}
		return total;
	}

	public List<Object> FindClassByTname(String tname,String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.FindClassByTname(tname,tid);
	}

	public void delTaskBytid(String tkid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		String[] ids = tkid.split(",");
		for (String id : ids) {
		dao.delTaskBytid(id);
		}
		
	}
	
	public void updateTask(Task task) throws SQLException {
		PracticeDao dao = new PracticeDao();
		dao.updateTask(task);		
	}
	
	public List<Task> findGradeCourse(String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findGradeCourse(tid);		
	}
	
	public List<Task> FindGradeClass(String tkname, String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.FindGradeClass(tkname,tid);		
	}
	
	public List<Task> FindGredeType(String tkname, String tkclass, String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.FindGradeClass(tkname,tkclass,tid);	
	}

	public List<GradeInfo> findStudentByClass(String tkclass,String tkid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findStudentByClass(tkclass,tkid);		
	}

	public List<Task> findTkid(String tname, String tclass, String ttype) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findTkid(tname,tclass,ttype);		
	}

	public void addGrade(List<Grade> gradeList,String cid,String tkid, String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		int g1,g2,g3,g4,g5;
		
		for (Grade grade : gradeList) {
			int flag=0;
			if(grade.getG_1()==null){
				g1=0;
				flag++;
			}else{
				g1 = Integer.parseInt(grade.getG_1());
			}
			if(grade.getG_2()==null){
				g2=0;
				flag++;
			}else{
				g2 = Integer.parseInt(grade.getG_2());
			}
			if(grade.getG_3()==null){
				g3=0;
				flag++;
			}else{
				g3 = Integer.parseInt(grade.getG_3());
			}
			if(grade.getG_4()==null){
				g4=0;
				flag++;
			}else{
				g4 = Integer.parseInt(grade.getG_4());
			}
			if(grade.getG_5()==null){
				g5=0;
				flag++;
			}else{
				g5 = Integer.parseInt(grade.getG_5());
			}						 
			 int avg = (g1+g2+g3+g4+g5)/(5-flag);
			dao.addGrade(grade,cid,tkid,tid,avg);
		}					
	}

	

	

	public List<Task> findGrandTeacherCourse(String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findGrandTeacherCourse(tid);
	}

	public List<Course> findGradeStudentCourse(String sclass) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findGradeStudentCourse(sclass);
	}

	public boolean ISevaluation(String sid, String sclass) throws SQLException {
		PracticeDao dao = new PracticeDao();
		int courseTotal = 0;
		int evaluation = 0;
		courseTotal = dao.findCourseStudent(sclass);
		evaluation = dao.findEvaluation(sid);
		return courseTotal==evaluation?true:false;
	}

	public List<Task> findTaskId(String tid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findTaskId(tid);
	}

	public List<GradeInfo2> findGradeTeacher(List<Task> taskid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findGradeTeacher(taskid);
	}

	public List<Task> findTaskIdBysid(String sid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findTaskIdBysid(sid);
	}

	public List<GradeInfo> findGradeStudent(int currentPage, int pageSize, String sid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findGradeStudent(currentPage,pageSize,sid);
	}

	public List<Task> findTaskIdAdmin() throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findTaskIdAdmin();
	}

	public List<GradeInfo2> findGradeAdmin(List<Task> taskid) throws SQLException {
		PracticeDao dao = new PracticeDao();
		return dao.findGradeAdmin(taskid);
	}

	public void addTask2(Task task) throws SQLException {
		PracticeDao dao = new PracticeDao();
		dao.addTask2(task);
		
	}

	public void updataPwd(String pwd, User user) throws SQLException {
		PracticeDao dao = new PracticeDao();
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			String a_id = user.getA_id();
			dao.updataPwd_A(pwd,a_id);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String t_id = user.getT_id();
			dao.updataPwd_T(pwd,t_id);
		}
		if(user.getS_type()!=null&&user.getS_type().trim().equals("2")){
			String s_id = user.getS_id();
			dao.updataPwd_S(pwd,s_id);
		}
		
	}

	public int getStuGradeTotalCount(String sid) throws SQLException {
		PracticeDao dao = new PracticeDao();		
		int total = 0;
		return total = dao.getStuGradeTotalCount(sid);
	}

	public List<GradeInfo> findGradeTeacher(int currentPage, int pageSize, Searchtask searchTGrade, User user) throws SQLException {
		List<GradeInfo> gradeList = null;
		
		PracticeDao dao = new PracticeDao();
		
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			gradeList = dao.AdminfindGradeList(currentPage,pageSize,searchTGrade);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String tid = user.getT_id();
			gradeList = dao.findGradeTeacher(currentPage,pageSize,searchTGrade,tid);
		}
		
		return gradeList;
		
		
	}

	public int getTGradeTotalCount(Searchtask searchTGrade, User user) throws SQLException {
		PracticeDao dao = new PracticeDao();
		int total = 0;
		
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			total = dao.getAdminGradeTotalCount(searchTGrade);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String tid = user.getT_id();
			total = dao.getTGradeTotalCount(searchTGrade,tid);
		}
		
		return total;		 
	}

	
	

	

	


}
