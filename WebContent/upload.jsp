<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
<script type="text/javascript">
	var obj = window.dialogArguments;

	var c_name = obj.c_name;
	var c_class = obj.cclass;
	var c_index = obj.c_index;

	function a() {
		alert("您传递的参数为：" + c_name);
		alert("您传递的参数为：" + c_class);
		alert("您传递的参数为：" + c_index);

		document.getElementById("a").action = "${pageContext.request.contextPath}/servlet/UploadHandleServlet?cname="+ c_name + "&cclass=" + c_class;
		document.getElementById("a").submit();
	}
</script>
</head>
<body>

	<form enctype="multipart/form-data" method="post" id="a">

		<input type="hidden" name="cname" value="c_name"> 
		
		 上传文件:<input type="file" name="file1"><br /> 
		
		<input type="button" value="提交" onclick="a()">
	</form>

	<%-- <form action="${pageContext.request.contextPath}/servlet/UploadHandleServlet"  enctype="multipart/form-data" method="post">

		<input type="hidden" name="cname" value="c_name">
	        上传用户：<input type="text" name="username"><br/>
	        上传文件1：<input type="file" name="file1"><br/>
	        上传文件2：<input type="file" name="file2"><br/>
        <input type="submit" value="提交">
    </form> --%>


</body>
</html>