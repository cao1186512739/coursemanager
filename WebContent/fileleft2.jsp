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
<div class="easyui-accordion" style="width: 100px;background: #fff" data-options="fit:true" >


<form enctype="multipart/form-data" method="post" id="a" action="${pageContext.request.contextPath}/UploadHandleServlet">
     <!-- <input type="hidden" name="cname" value="c_name"> 
     <input type="hidden" name="cname" value="c_name">  -->
     <div style="margin: 20px;font-size: 18px;background: #efe8e8">上传文件:</div>
     <input style="margin:20px" type="file" name="file1"><br /> 
	 <input  style="margin:20px" type="submit" value="提交" >
	
	 <input type="button" name="Submit" onclick="r()" value="返回">
</form>
		
		
		
				
		
	</div>
</body>
</html>