<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="easyui-accordion" style="width: 100px;" data-options="fit:true">
		<!--overflow: auto;自动展开-->
		<div title="课程列表" style="padding: 5px; ">
			<ul class="easyui-tree">
				<c:forEach items="${studentcourseList }" var="course">
				<li>
					<span>${course.c_name }</span>
					<ul>
					<li><a id="" href="#" name="" onclick="showcontent('${pageContext.request.contextPath}/ListFileServlet?method=ListTeacherFile&cname=${course.c_name }&type=<%= 1 %>')">授课计划</a></li>
					<li><a id="" href="#" name="" onclick="showcontent('${pageContext.request.contextPath}/ListFileServlet?method=ListTeacherFile&cname=${course.c_name }&type=<%= 2 %>')">实验计划</a></li>
					<li><a id="" href="#" name="" onclick="showcontent('${pageContext.request.contextPath}/ListFileServlet?method=ListTeacherFile&cname=${course.c_name }&type=<%= 3 %>')">课程提问</a></li>
					<li><a id="" href="#" name="" onclick="showcontent('${pageContext.request.contextPath}/ListFileServlet?method=ListTeacherFile&cname=${course.c_name }&type=<%= 4 %>')">其他</a></li>
					</ul>
				</li>
				</c:forEach>
											
				
			</ul>
		</div>
		
		
				
		
	</div>
</body>
</html>