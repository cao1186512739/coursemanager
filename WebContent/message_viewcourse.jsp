<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息提醒</title>
<script type="text/javascript">
function b() {
	window.location.href='${pageContext.request.contextPath}/ViewCourseInformation.jsp'; 
}

</script>
</head>
<body>
${message };
<br/>
 <input type="button" value="返回" onclick="b()">
</body>
</html>