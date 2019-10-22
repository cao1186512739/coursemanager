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
		<u>请选择你要查看老师的名字,点击确定按钮进入查询评教</u>
	</div>
	<div align="center">
		<form action="${pageContext.request.contextPath}/evaluation?method=ViewEvaluationResult_TeacherName" method="post">
			 <select id="t_id" name="t_id" style="width: 150px">
			 <c:forEach items="${teachername }" var="teachername">
					<option value="${teachername.t_id }">${teachername.t_name }</option>
				</c:forEach>
			</select>
			 <input type="submit" value="查看评教">
		</form>



	</div>
</body>
</html>