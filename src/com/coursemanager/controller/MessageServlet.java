package com.coursemanager.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.Message;
import com.coursemanager.entity.MessageScope;
import com.coursemanager.entity.PageBean;
import com.coursemanager.entity.Searchmessage;
import com.coursemanager.entity.Searchtask;
import com.coursemanager.entity.Task;
import com.coursemanager.entity.User;
import com.coursemanager.service.MessageService;
import com.coursemanager.service.PracticeService;
import com.coursemanager.util.BaseServlet;
import com.coursemanager.util.Json;
import com.google.gson.Gson;

public class MessageServlet extends BaseServlet {
	
	public void FindScope(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		String mscope = request.getParameter("myscope");
		String mtype = request.getParameter("m_type");
		MessageService service = new MessageService();
		List<Object> scopeList = null;
		Gson gson = new Gson();	
		try {
			scopeList = service.FindScope(mtype,mscope);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String json = gson.toJson(scopeList);
		response.getWriter().write(json);
		
		
	}
	
	public void FindAllScope(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		String mscope = request.getParameter("myscope");
		
		MessageService service = new MessageService();
		List<Object> scopeList = null;
		Gson gson = new Gson();	
		try {
			scopeList = service.FindAllScope(mscope);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String json = gson.toJson(scopeList);
		response.getWriter().write(json);
		
		
	}
	
	public void AddMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		//获取数据
		Map<String, String[]> messagelist = request.getParameterMap();
		//封装数据
		Message message = new Message();
		
		try {
			BeanUtils.populate(message, messagelist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//设置表单中没有的元素	
		String sscope = message.getMs_scope();
		String tscope = message.getMt_scope();
		MessageService service = new MessageService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String type = user.getA_type();
		int flag = 0;
		try {
			service.addMessage(message,sscope,tscope,type);
			flag = 1;	
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/InputMessageInformation.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/InputMessageInformation.jsp").forward(request, response);			
		}		
		
	}
	
	public void AddTMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		//获取数据
		Map<String, String[]> messagelist = request.getParameterMap();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String type = user.getT_type();
		//封装数据
		Message message = new Message();
		
		try {
			BeanUtils.populate(message, messagelist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//设置表单中没有的元素			
		MessageService service = new MessageService();
		int flag = 0;
		try {
			service.addTMessage(message,type);
			flag = 1;	
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/InputTMessageInformation.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/InputTMessageInformation.jsp").forward(request, response);			
		}		
		
	}
	
	public void FindTeacherClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Course> tclassList = null;		
		MessageService service = new MessageService();
		HttpSession session = request.getSession();		
		User user = (User) session.getAttribute("user");
		String tid = user.getT_id();
		try {
			tclassList = service.getTClass(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("teacherclassList", tclassList);
		
		request.getRequestDispatcher("/InputTMessageInformation.jsp").forward(request, response);		
	}		
	
	public void SearchTeacherClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Course> tclassList = null;		
		MessageService service = new MessageService();
		HttpSession session = request.getSession();		
		User user = (User) session.getAttribute("user");
		String tid = user.getT_id();
		try {
			tclassList = service.getTClass(tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("teacherclassList", tclassList);
		
		request.getRequestDispatcher("/UpdateTMessageInformation.jsp").forward(request, response);		
	}		
	
	public void ViewMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MessageService service = new MessageService();
		List<Message> messageList = null;		
		Map<String, String[]> searchinformation = request.getParameterMap();
		Searchmessage searchmessage  = new Searchmessage();		
		try {
			BeanUtils.populate(searchmessage,searchinformation);
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
			messageList = service.MessagefindPageBean(currentPage,pageSize,searchmessage,user);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		int totalcount=0;
		try {
			totalcount = service.getMessageTotalCount(searchmessage,user);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = "{\"total\":"+totalcount+",\"rows\":"+gson.toJson(messageList)+"}";
		response.getWriter().write(json);		
	}
	
	//查询老师发布的通知
	public void ViewTMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MessageService service = new MessageService();
		List<Message> messageList = null;		
		Map<String, String[]> searchinformation = request.getParameterMap();
		Searchmessage searchmessage  = new Searchmessage();		
		try {
			BeanUtils.populate(searchmessage,searchinformation);
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
			messageList = service.TMessagefindPageBean(currentPage,pageSize,searchmessage,user);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		int totalcount=0;
		try {
			totalcount = service.getTMessageTotalCount(searchmessage,user);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = "{\"total\":"+totalcount+",\"rows\":"+gson.toJson(messageList)+"}";
		response.getWriter().write(json);		
	}
	
	public void DelMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String mid = request.getParameter("ids");
		MessageService service = new MessageService();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			service.DelMessage(mid);			
			j.setSuccess(true);
			j.setMsg("删除成功");			
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("删除失败");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);
	}
	
	public void UpdateMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String[]> messagelist = request.getParameterMap();
		//封装数据
		Message message = new Message();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			BeanUtils.populate(message, messagelist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		MessageService service = new MessageService();
		try {
			service.updateMessage(message);
			j.setSuccess(true);
			j.setMsg("修改数据成功");				
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("修改数据失败");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);
	}
	
	
	
}