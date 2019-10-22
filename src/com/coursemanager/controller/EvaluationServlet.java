package com.coursemanager.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.Evaluation;
import com.coursemanager.entity.Teacher;
import com.coursemanager.entity.User;
import com.coursemanager.service.EvaluationService;
import com.google.gson.Gson;
import com.coursemanager.entity.ViewEvaluationResultTeacher;;

public class EvaluationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String method = request.getParameter("method");

		if ("StartEvaluation".equals(method)) {
			try {
				StartEvaluation(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("ProcessEvaluation".equals(method)) {
			try {
				ProcessEvaluation(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("ViewEvaluation".equals(method)) {
			try {
				ViewEvaluation(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("ViewEvaluationResult".equals(method)) {
			// 查看评教结果
			ViewEvaluationResult(request, response);
		} else if ("ViewEvaluationResultTeacher".equals(method)) {
			try {
				ViewEvaluationResultTeacher(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("ViewEvaluationResult_CourseName".equals(method)) {
			try {
				ViewEvaluationResult_CourseName(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("ViewEvaluationResult_TeacherName".equals(method)){
			ViewEvaluationResult_TeacherName(request, response);
		}

	}

	//管理员�?�过 查看老师名字  接着查看评教结果
	private void ViewEvaluationResult_TeacherName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String t_id = request.getParameter("t_id");
		
		HttpSession session = request.getSession();
		session.setAttribute("t_id", t_id);
		
		//转发网页
		request.getRequestDispatcher("/ViewEvaluationResult.jsp").forward(request, response);
		
	}

	// 管理员查看了课程�? 再进行对任课老师的索�?
	private void ViewEvaluationResult_CourseName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String c_name = request.getParameter("c_name");
		
		HttpSession session = request.getSession();
		session.setAttribute("c_name", c_name);

		// 创建对象
		EvaluationService service = new EvaluationService();
		List<Teacher> teachername = service.findTeacherNameByC_name(c_name);		
        
		request.setAttribute("teachername", teachername);
		request.getRequestDispatcher("/AdminViewEvaluationTeacherName.jsp").forward(request,response);
	}

	
	
	// 查看评教结果 <第一�?>------- 老师和学�?
	private void ViewEvaluationResultTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		HttpSession session = request.getSession();
		
		//老师和学生用
		String c_id = (String) session.getAttribute("c_id");
		
		
		//管理员进来时查看�?
		String t_id = (String) session.getAttribute("t_id");
		String c_name = (String) session.getAttribute("c_name");
		
		// 得到进入查看人的身份
		User user = (User) session.getAttribute("user");

		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		// 创建对象
		EvaluationService service = new EvaluationService();

		//管理员进�?
		if("0".equals(user.getA_type())){
			//通过t_name 得到相对应的t_id
			List<ViewEvaluationResultTeacher> EvaluationResultList=service.findEvaluationResultByC_Name_T_id(currentPage, pageSize,c_name,t_id);
			int total = service.findEvaluationResultByC_Name_T_idTotal(c_name,t_id);
			Gson gson = new Gson();
			String json = "{\"total\":" + total + ", \"rows\":" + gson.toJson(EvaluationResultList) + "}";
			response.getWriter().write(json);

		}
		
		// 老师进来
		if ("1".equals(user.getT_type())) {
			List<ViewEvaluationResultTeacher> ViewEvaluationResultTeacherList = service
					.findEvaluationResultByC_id(currentPage, pageSize, c_id);
			int total = service.findEvaluationResultByC_idTotal(c_id);
			Gson gson = new Gson();
			String json = "{\"total\":" + total + ", \"rows\":" + gson.toJson(ViewEvaluationResultTeacherList) + "}";
			response.getWriter().write(json);

		}

		// 学生进来
		if ("2".equals(user.getS_type())) {
			List<ViewEvaluationResultTeacher> ViewEvaluationResultTeacherList = service
					.findEvaluationResultByS_id(currentPage, pageSize, user, c_id);
			int total = service.findEvaluationResultByS_idTotal(user, c_id);
			Gson gson = new Gson();
			String json = "{\"total\":" + total + ", \"rows\":" + gson.toJson(ViewEvaluationResultTeacherList) + "}";
			response.getWriter().write(json);
		}
	}

	// 查看评教结果 <第一�?>
	private void ViewEvaluationResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String c_id = request.getParameter("c_id");
		HttpSession session = request.getSession();
		session.setAttribute("c_id", c_id);

		// 转发网页
		request.getRequestDispatcher("ViewEvaluationResult.jsp").forward(request, response);

	}

	// 查看评教
	private void ViewEvaluation(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// 得到进入查看人的身份
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// 创建业务对象
		EvaluationService service = new EvaluationService();

		// 如果是管理员
		if ("0".equals(user.getA_type())) {

			List<Course> viewcourseList = service.findCourseName();
			request.setAttribute("viewcourseList", viewcourseList);
			request.getRequestDispatcher("/AdminViewEvaluationCourseName.jsp").forward(request, response);
		}

		// 如果是教�?
		if ("1".equals(user.getT_type())) {

			List<Course> viewcourseList = service.findCourseByT_id(user);
			request.setAttribute("viewcourseList", viewcourseList);
			request.getRequestDispatcher("/ViewEvaluation.jsp").forward(request, response);

		}
		// 如果是学�?
		if ("2".equals(user.getS_type())) {
			List<Course> viewcourseList = service.findCourseByS_id(user);
			request.setAttribute("viewcourseList", viewcourseList);
			request.getRequestDispatcher("/ViewEvaluation.jsp").forward(request, response);
		}

	}

	// 进行评教
	private void ProcessEvaluation(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		try {
			Map<String, String[]> parameterMap = request.getParameterMap();
			Evaluation evaluation = new Evaluation();
			BeanUtils.populate(evaluation, parameterMap);
			// 得到session
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			EvaluationService service = new EvaluationService();
			// 进行评教
			service.ProcessEvaluation(evaluation, user);
			request.setAttribute("message", "评教成功");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// 失败的话
			request.setAttribute("message", "评教失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);

			e.printStackTrace();
		}

	}

	// �?始评�?
	private void StartEvaluation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 得到学生�?在的班级
		String s_class = user.getS_class();

		EvaluationService service = new EvaluationService();
		List<Course> courseList = service.findCourseByS_class(s_class);

		request.setAttribute("courseList", courseList);
		request.getRequestDispatcher("/OnlinEvaluation.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}