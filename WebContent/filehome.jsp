<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	function showcontent(url) {
		$("#pageurl").attr("src", url);
	}
</script>
</head>
<body id="bylayout" class="easyui-layout">		

		<div data-options="region:'west',title:'主要课程',split:false,border:false,hideCollapsedContent:false" href="fileleft.jsp" style="width:250px;">

		</div>		
		<div data-options="region:'center',title:'资料下载列表',border:false" style="padding:0;background:#fff;">
			<iframe id="pageurl" style="width:99%;height:99%" scrolling="no"></ifranme>
		</div>
		
		
		
	</body>
</html>