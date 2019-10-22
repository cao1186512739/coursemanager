package com.coursemanager.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class BaseActionServlet extends HttpServlet {
	public void writeJson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		response.setContentType("text/html;charset=UTF-8"); 
		Map map = new HashMap();		
		String json = JSONObject.fromObject(map).toString();
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

}
