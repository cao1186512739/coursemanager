<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<u>请选择课程,点击检索按钮进入网上评教</u>
	</div><br>
	<div align="center">
		<form action="StartOnlinEvaluation.jsp">
			 <select id="c_id" name="c_id" style="width: 150px">
			 <c:forEach items="${courseList }" var="course">
					<option value="${course.c_id}">${course.c_name }</option>
				</c:forEach>
			</select>
			</select>
			 <input type="submit" value="检索" id="jiansuo">
		</form>



	</div>
</body>
</html>