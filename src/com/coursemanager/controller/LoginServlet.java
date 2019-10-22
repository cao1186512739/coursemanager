package com.coursemanager.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursemanager.entity.User;
import com.coursemanager.service.LoginService;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("link_type");
		LoginService service = new LoginService();		
		User user = null;
			try {
				user = service.UserLogin(username,password,type);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(user!=null){
				//登录成功
				//***************判断用户是否勾选了自动登录*****************
				
				String autoLogin = request.getParameter("autoLogin");
				if("autoLogin".equals(autoLogin)){
					//要自动登录
					//创建存储用户名的cookie
					if(user.getA_no() != null){
					Cookie cookie_username = new Cookie("cookie_username",user.getA_no());
					cookie_username.setMaxAge(10*60);
					//创建存储密码的cookie
					Cookie cookie_password = new Cookie("cookie_password",user.getA_password());
					cookie_password.setMaxAge(10*60);
					response.addCookie(cookie_username);
					response.addCookie(cookie_password);
					}
					
					if(user.getT_no() != null){
						Cookie cookie_username = new Cookie("cookie_username",user.getT_no());
						cookie_username.setMaxAge(10*60);
						//创建存储密码的cookie
						Cookie cookie_password = new Cookie("cookie_password",user.getT_password());
						cookie_password.setMaxAge(10*60);
						response.addCookie(cookie_username);
						response.addCookie(cookie_password);
						}
					
					if(user.getS_no() != null){
						Cookie cookie_username = new Cookie("cookie_username",user.getS_no());
						cookie_username.setMaxAge(10*60);
						//创建存储密码的cookie
						Cookie cookie_password = new Cookie("cookie_password",user.getS_password());
						cookie_password.setMaxAge(10*60);
						response.addCookie(cookie_username);
						response.addCookie(cookie_password);
						}	

				}
				
				//将登录的用户的user对象存到session
				session.setAttribute("user", user);
				//重定向到首页											
			}else{
				//失败 转发到登录页 提出提示信息
				request.setAttribute("loginInfo", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			int year;
			HttpSession session1 = request.getSession();
			Calendar cal=Calendar.getInstance();    
			year=cal.get(Calendar.YEAR); 
			session1.setAttribute("year", year);
			
			request.setAttribute("user",user);
			//response.sendRedirect(request.getContextPath()+"/home.jsp");
			request.getRequestDispatcher("/home.jsp").forward(request, response);
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
