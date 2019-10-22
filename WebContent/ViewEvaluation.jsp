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
		<u>请选择课程,点击确定按钮进入查询评教</u>
	</div>
	<div align="center">
		<form action="${pageContext.request.contextPath}/evaluation?method=ViewEvaluationResult" method="post">
			 <select id="c_id" name="c_id" style="width: 150px">
			 <c:forEach items="${viewcourseList }" var="viewcourse">
					<option value="${viewcourse.c_id}">${viewcourse.c_name }</option>
				</c:forEach>
			</select>
			 <input type="submit" value="查看评教">
		</form>



	</div>
</body>
</html>