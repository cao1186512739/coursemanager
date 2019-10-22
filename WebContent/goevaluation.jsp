<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">	
	$(function(){
		/*alert("dfas");*/
		$.messager.confirm('确认进行评教', '你尚未完成评教，现在是否现在进行评教？', function(r){
			if (r){
			    location.href = "${pageContext.request.contextPath}/InputCourseInformation.jsp";
 			}
		});


	})
</script>
</head>
<body>

</body>
</html>