package com.coursemanager.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.Grade;
import com.coursemanager.entity.GradeInfo;
import com.coursemanager.entity.GradeInfo2;
import com.coursemanager.entity.Searchtask;
import com.coursemanager.entity.Student;
import com.coursemanager.entity.Task;
import com.coursemanager.entity.User;
import com.coursemanager.service.PracticeService;
import com.coursemanager.util.BaseServlet;
import com.coursemanager.util.Json;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import net.sf.json.JSONArray;

public class PracticeServlet extends BaseServlet {
	
	public void FindCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		PracticeService service = new PracticeService();
		List<Course> tnameList = null;
		List<Course> tclassList = null;
		try {
			tnameList = service.findAllCourse();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		try {
			tclassList = service.findAllClass();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		request.setAttribute("allclassList", tclassList);		
		request.setAttribute("allnameList", tnameList);
		request.getRequestDispatcher("/ViewGradeTeacher.jsp").forward(request, response);
	}		

	public void FindTeacherCourseByTid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Course> tcourseList = null;
		PracticeService service = new PracticeService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		try {
			tcourseList = service.findTeacherCourse(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("teachercourseList", tcourseList);
		request.getRequestDispatcher("/InputTaskInformation.jsp").forward(request, response);
		
	}		
	
	public void FindTeacherClassByTname(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String tname = request.getParameter("tk_name");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		PracticeService service = new PracticeService();
		List<Object> TclassList = null;
		Gson gson = new Gson();	
		try {
			TclassList = service.FindClassByTname(tname,tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String json = gson.toJson(TclassList);
		response.getWriter().write(json);
//		request.setAttribute("TclassList", TclassList);
	}
	
	public void FindTeacherClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Course> tclassList = null;
		List<Course> tcourseList = null;
		PracticeService service = new PracticeService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		try {
			tclassList = service.findTeacherClass(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			tcourseList = service.findTeacherCourse(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("teachercourseList", tcourseList);
		request.setAttribute("teacherclassList", tclassList);
		request.getRequestDispatcher("/ViewTaskInformation.jsp").forward(request, response);		
	}		
	
	public void FindStudentCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		PracticeService service = new PracticeService();
		List<Task> scourseList = null;
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String sclass = user.getS_class();
		try {
			scourseList = service.findStudentCourse(sclass);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		request.setAttribute("studentcourseList", scourseList);		
		request.getRequestDispatcher("/ViewTaskInformation.jsp").forward(request, response);
	}	
	
	public void AddTaskForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String[]> tasklist = request.getParameterMap();
		Task task = new Task();
		List<Course> courseList = null;
		PracticeService service = new PracticeService();
		try {
			BeanUtils.populate(task, tasklist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		String tname = task.getTk_name();
		String tclass = task.getTk_class();
		try {
			courseList = service.findCidByNameAndClass(tname,tclass);
			if(courseList!=null){
				String cid = courseList.get(0).getC_id();
				task.setC_id(cid);
			}
			
					
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("task1", task);
		
//		String json = gson.toJson(j);
//		response.getWriter().write(json);
	}
	
	
	public void AddTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String[]> tasklist = request.getParameterMap();
		Task task = new Task();
		Json j = new Json();
		Gson gson = new Gson();	
		List<Course> courseList = null;
		
		PracticeService service = new PracticeService();
		try {
			BeanUtils.populate(task, tasklist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}	
		HttpSession session = request.getSession();
		Task task2 = (Task) session.getAttribute("task1");
		String tkcontent = task.getTk_content();
		String tktitle = task.getTk_title();
		String tkvalue = task.getTk_value();
		task2.setTk_content(tkcontent);
		task2.setTk_title(tktitle);
		task2.setTk_value(tkvalue);
			try {
				service.addTask(task2);
				j.setSuccess(true);
				j.setMsg("添加成功");
				j.setObj(tktitle);
				j.setObj(tkcontent);
			} catch (SQLException e) {
				e.printStackTrace();
				j.setMsg("添加失败");	
			}
			
			String json = gson.toJson(j);
			response.getWriter().write(json);
	}
	
	public void AddTask2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String[]> tasklist = request.getParameterMap();
		Task task = new Task();		
		List<Course> courseList = null;
		PracticeService service = new PracticeService();
		int flag = 0;
		try {
			BeanUtils.populate(task, tasklist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		String tname = task.getTk_name();
		String tclass = task.getTk_class();
		try {
			courseList = service.findCidByNameAndClass(tname,tclass);
			if(courseList!=null){
				String cid = courseList.get(0).getC_id();
				task.setC_id(cid);
			}
			
					
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
			try {
				service.addTask2(task);
				flag = 1;	
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("/InputTaskInformation.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();				
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("/InputTaskInformation.jsp").forward(request, response);
			}	
			
	}
	
	
	public void ViewTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PracticeService service = new PracticeService();
		List<Task> taskList = null;		
		Map<String, String[]> searchinformation = request.getParameterMap();
		Searchtask searchtask  = new Searchtask();		
		try {
			BeanUtils.populate(searchtask, searchinformation);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		HttpSession session=request.getSession();	
		User user = (User)session.getAttribute("user");
		try {
			taskList = service.TaskfindPageBean(currentPage,pageSize,searchtask,user);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		int totalcount=0;
		try {
			totalcount = service.getTaskTotalCount(searchtask,user);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = "{\"total\":"+totalcount+",\"rows\":"+gson.toJson(taskList)+"}";
		response.getWriter().write(json);		
	}
		
	public void FindTeacherCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Course> tclassList = null;
		List<Course> tcourseList = null;
		PracticeService service = new PracticeService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		try {
			tclassList = service.findTeacherClass(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			tcourseList = service.findTeacherCourse(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("teachercourseList", tcourseList);
		request.setAttribute("teacherclassList", tclassList);
		request.getRequestDispatcher("/UpdateTaskInformation.jsp").forward(request, response);		
	}
	
	public void DelTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String tkid = request.getParameter("ids");
		PracticeService service = new PracticeService();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			service.delTaskBytid(tkid);			
			j.setSuccess(true);
			j.setMsg("删除成功");			
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("删除失败");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);
	}
	
	public void UpdateTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String[]> tasklist = request.getParameterMap();
		//封装数据
		Task task = new Task();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			BeanUtils.populate(task, tasklist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		PracticeService service = new PracticeService();
		try {
			service.updateTask(task);
			j.setSuccess(true);
			j.setMsg("修改数据成功");				
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("修改数据失败");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);
	}
	
	public void FindGradeCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Task> tcourseList = null;
		PracticeService service = new PracticeService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		try {
			tcourseList = service.findGradeCourse(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("teachercourseList", tcourseList);
		request.getRequestDispatcher("/InputGradeInformation.jsp").forward(request, response);		
	}

	public void FindGradeClassByTname(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String tkname = request.getParameter("tk_name");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		PracticeService service = new PracticeService();
		List<Task> GclassList = null;
		Gson gson = new Gson();	
		try {
			GclassList = service.FindGradeClass(tkname,tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		String json = gson.toJson(GclassList);
		response.getWriter().write(json);
	} 
	
	public void FindGradeType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String tkname = request.getParameter("tk_name");
		String tkclass = request.getParameter("tk_class");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		PracticeService service = new PracticeService();
		List<Task> GclassList = null;
		Gson gson = new Gson();	
		try {
			GclassList = service.FindGredeType(tkname,tkclass,tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String json = gson.toJson(GclassList);
		response.getWriter().write(json);
	} 	
	
	public void AddGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		HttpSession session2 = request.getSession();
		PracticeService service = new PracticeService();
		List<Grade> gradeList = new ArrayList<Grade>();
		Gson gson = new Gson();
		Json j = new Json();
		int flag = 0;		
		String ens = request.getParameter("entities");
		String ens1 = ens.replace("}{", "},{");
		String ens2 = "["+ens1.toString()+"]";	     
	    // 2、format the string	
		JSONArray array =JSONArray.fromObject(ens2.toString());
	    gradeList=JSONArray.toList(array, Grade.class);	   
	    List<Task> task2 = (List<Task>) session.getAttribute("taskList");
	    Task task = task2.get(0);
		String cid = task.getC_id();
		String tkid = task.getTk_id();
		User user = (User) session2.getAttribute("user");
		String tid = user.getT_id();
		try {
			service.addGrade(gradeList,cid,tkid,tid);
			j.setSuccess(true);
			j.setMsg("录入学生成绩成功");	
		} catch (SQLException e) {
			e.printStackTrace();			
			j.setMsg("录入学生成绩失败");
					
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);
		
	}
	
	public void ViewStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String tkname = request.getParameter("tk_name");
		String tkclass = request.getParameter("tk_class");
		String tktype = request.getParameter("tk_type");
		List<Task> taskList = null;
		List<Student> studentList = null;
		PracticeService service = new PracticeService();
		try {
			taskList = service.findTkid(tkname,tkclass,tktype);			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Task task = taskList.get(0);
		String proportion = task.getTk_proportion();
		Integer time = task.getTk_time();		
		String pro = proportion.substring(0, proportion.length()-1);
		Integer int_pro =   Integer.parseInt(pro);
		String avg = (int_pro/time)+"%";		
		
		
		request.setAttribute("avg", avg);
		session.setAttribute("taskList", taskList);
		//request.setAttribute("taskList", taskList);
		
		request.getRequestDispatcher("/InputStudentGrade.jsp").forward(request, response);				
	}
	
	public void ViewData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		List<GradeInfo> gradeList = null;
		PracticeService service = new PracticeService();
		List<Task> task = (List<Task>) session.getAttribute("taskList");
		Task task2 = task.get(0);
		String tkclass = task2.getTk_class();
		String tkid = task2.getTk_id();
		try {
			gradeList = service.findStudentByClass(tkclass,tkid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json =gson.toJson(gradeList);
		response.getWriter().write(json);	
				
	}
	
	public void FindGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		PracticeService service = new PracticeService();
		List<Course> tnameList = null;
		List<Course> tclassList = null;
		try {
			tnameList = service.findAllCourse();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		try {
			tclassList = service.findAllClass();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		request.setAttribute("allclassList", tclassList);		
		request.setAttribute("allnameList", tnameList);
		request.getRequestDispatcher("/ViewGradeTeacher.jsp").forward(request, response);
	}		
	
	
	
	public void FindTeacherGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Task> tclassList = null;
		List<Task> tcourseList = null;
		PracticeService service = new PracticeService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		try {
			tclassList = service.findGrandTeacherCourse(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			tcourseList = service.findGradeCourse(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("teachergradecourseList", tcourseList);
		request.setAttribute("teachergradeclassList", tclassList);
		request.getRequestDispatcher("/ViewGradeTeacher.jsp").forward(request, response);	
	}
	
	public void FindStudentGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Course> scourseList = null;
		boolean isvaluation = false;
		PracticeService service = new PracticeService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String sclass = user.getS_class();
		String sid = user.getS_id();
		try {
			isvaluation = service.ISevaluation(sid,sclass);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(isvaluation){
			try {
				scourseList = service.findGradeStudentCourse(sclass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("studentgradecourseList", scourseList);
			request.getRequestDispatcher("/ViewGradeInformation.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/goevaluation.jsp");
		}		
	}
	
	public void FindTeacherGrade2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Task> tclassList = null;
		List<Task> tcourseList = null;
		PracticeService service = new PracticeService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		try {
			tclassList = service.findGrandTeacherCourse(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			tcourseList = service.findGradeCourse(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("teachergradecourseList", tcourseList);
		request.setAttribute("teachergradeclassList", tclassList);
		request.getRequestDispatcher("/ViewGradeInformation2.jsp").forward(request, response);	
	}
	
	public void ViewGradeTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PracticeService service = new PracticeService();
		List<GradeInfo> gradeList = null;	
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		HttpSession session=request.getSession();	
		User user = (User)session.getAttribute("user");
		String tid = user.getT_id();
		Map<String, String[]> searchinformation = request.getParameterMap();
		Searchtask searchTGrade  = new Searchtask();		
		try {
			BeanUtils.populate(searchTGrade, searchinformation);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		try {
			gradeList = service.findGradeTeacher(currentPage,pageSize,searchTGrade,user);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			
		int totalcount=0;
		try {
			totalcount = service.getTGradeTotalCount(searchTGrade,user);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = "{\"total\":"+totalcount+",\"rows\":"+gson.toJson(gradeList)+"}";
		response.getWriter().write(json);		
	}
	
	public void ViewGradeStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PracticeService service = new PracticeService();
		List<GradeInfo> gradeList = null;
			
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		HttpSession session=request.getSession();	
		User user = (User)session.getAttribute("user");
		String sid = user.getS_id();				
		try {
			gradeList = service.findGradeStudent(currentPage,pageSize,sid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int totalcount = 0;
		
		try {
			totalcount = service.getStuGradeTotalCount(sid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = "{\"total\":"+totalcount+",\"rows\":"+gson.toJson(gradeList)+"}";
		response.getWriter().write(json);		
	}
	
	public void UpdataPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PracticeService service = new PracticeService();
		String pwd = request.getParameter("password");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int flag = 0; 
		try {
			service.updataPwd(pwd,user);
			flag = 1;
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/UpdataPassword.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/UpdataPassword.jsp").forward(request, response);
		}
		
	}
	
}
