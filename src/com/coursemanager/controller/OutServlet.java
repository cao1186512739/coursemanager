package com.coursemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//注销
public class OutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//false代表：不创建session对象，只是从request中获取。
		HttpSession session = request.getSession(false);		
		session.removeAttribute("user");
		
		//将存储在客户端的cookie删除掉
				Cookie cookie_username = new Cookie("cookie_username","");
				cookie_username.setMaxAge(0);
				//创建存储密码的cookie
				Cookie cookie_password = new Cookie("cookie_password","");
				cookie_password.setMaxAge(0);

				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
				
		//重定向到login.jsp
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		//request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
