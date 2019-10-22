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
<link rel="stylesheet" href="css/mainui.css" /> 
<script type="text/javascript">
	function showcontent(url) {
		 $("#pageurl").attr("src", url);
		 $("a").click(function () {
             clearClass();
             
             $(this).addClass("bg_color");
         });
         function clearClass() {
             $("a").each(function () {
                 $(this).removeClass("bg_color");
             });
	        }
       

	}
        </script>

 <style type="text/css">
        .bg_color
        {
            cursor:pointer;
            color:Red;
        }
</style>

</head>
<body id="bylayout" class="easyui-layout" style="overflow-x : hidden;">


		<div data-options="region:'north',title:'',split:false" style="height:86px;margin:0;padding:0;overflow:hidden">
			<img src="img/main/01.png" width="100%" height="86px" border="1" style="position: relative;" />
			<div style="position: absolute;left: 80px;top: 16px;">
				<img src="img/main/07.png" >
			<div style="position: absolute;left: 272px;top: 1px;">
				<img src="img/main/03.png" style="vertical-align: middle;">
			</div>
			</div>
			<div style="position: absolute;left: 370px;top: 27px;">
				<div style="font-size: 28px;color: white;" >					
					课程过程网络管理系统
				</div>	
				
			</div>	
			
			<div style="position: absolute;right:50px;top: 35px;">
			<!--  
				<a style="font-size: 17px;color: white;" >					
					退出
				</a>
			-->
				<a href="${pageContext.request.contextPath }/out" style="font-size: 17px;color: white;">退出</a>  	
			</div>	
			<div style="position: absolute;right:110px;top: 35px;">
				<a href="${pageContext.request.contextPath }/home.jsp" style="font-size: 17px;color: white;">首页</a>  	
			</div>			
		</div>
			
			
	

		<div data-options="region:'west',iconCls:'icon-reload',title:'功能菜单',split:true,hideCollapsedContent:false" href="left.jsp" style="width:250px;">

		</div>
		<c:if test="${user.a_type==0}">
		<div data-options="region:'center',title:'管理员欢迎你'" style="padding:0;background:#fff;">		 	
			<iframe id="pageurl" width="100%" height="100%" scrolling="no"></ifranme>
		</div>
		</c:if>
		<c:if test="${user.t_type==1}">
		<div data-options="region:'center',title:'${user.t_name }老师欢迎你'" style="padding:0;background:#eee;">
		<!-- 
			<img src="" width="100%" />
		-->
			<iframe id="pageurl" width="100%" height="100%" scrolling="no"></ifranme>
		</div>
		</c:if>
		<c:if test="${user.s_type==2}">
		<div data-options="region:'center',title:'${user.s_name }同学欢迎你'" style="padding:0;background:#fff;">
			<iframe id="pageurl" width="100%" height="100%" scrolling="no"></ifranme>
		</div>
		</c:if>
		
	</body>
</html>