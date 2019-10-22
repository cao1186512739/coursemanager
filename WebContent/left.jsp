<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>



</head>
<body>
<div class="easyui-accordion" style="width: 100px;" data-options="fit:true">
		<!--overflow: auto;自动展开-->
		<c:if test="${user.a_type==0||user.t_type==1}">
		<div title="人员信息管理" >
			<ul>
			<c:if test="${user.a_type=='0'}">
				<li class="in"><a href="#" onclick="showcontent('InputPersonInformation.jsp')">人员信息录入</a></li>								
			</c:if>	
				<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/ViewStudent.jsp')">人员信息查询</a></li>										
		 	<c:if test="${user.a_type=='0'}">
		 		<li class="update"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/UpdataStudentInforation.jsp')">人员信息更改</a></li>
			</c:if>			    
			</ul>
		</div>
		</c:if>
		<div title="课程信息管理" >
			<ul >
			
				<c:if test="${user.t_type==1}">	
					<li class="in"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/InputCourseInformation.jsp')">课程信息录入</a></li>
				</c:if>			
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/ViewCourseInformation.jsp')">课程信息查询</a></li>
				<c:if test="${user.a_type==0}">
					<li class="update"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/UpdateCourseInformation.jsp')">课程信息更改</a></li>
				</c:if>	
				<c:if test="${user.s_type==2}">			
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/ListFileServlet?method=FindStudentCourse')">相关资料</a></li>
				</c:if>			
			</ul>
		</div>
		<div title="平时成绩管理" >
			<ul >
				<c:if test="${user.t_type==1}">	
					<li class="in"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/practice?method=FindTeacherCourseByTid')">发布任务</li>
				</c:if>
				<!-- 
				<c:if test="${user.a_type==0}">	
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/practice?method=FindCourse')">查询任务</a></li>
				</c:if>
				<c:if test="${user.t_type==1}">	
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/practice?method=FindTeacherClass')">查询任务</a></li>
				</c:if>
				 -->
				<c:if test="${user.s_type==2}">	
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/practice?method=FindStudentCourse')">查询任务</a></li>
				</c:if>
				
		         <c:if test="${user.t_type==1}">		         	
					<li class="update"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/practice?method=FindTeacherCourse')">任务更改</a></li>
				</c:if>
				
				<c:if test="${user.t_type==1}">	
					<li class="in"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/practice?method=FindGradeCourse')">录入成绩</li>
				</c:if>	
				<c:if test="${user.a_type==0}">								
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/practice?method=FindCourse')">查询成绩</a></li>	
				</c:if>	
				<c:if test="${user.t_type==1}">								
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/practice?method=FindTeacherGrade')">查询成绩</a></li>	
				</c:if>			
				<c:if test="${user.s_type==2}">								
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/ViewGradeStudent.jsp')">查询成绩</a></li>	
				</c:if>					
			</ul>
		</div>
				
		<div title="网上评教" >
			<ul >
				<c:if test="${user.s_type==2}">
					<li class="in"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/evaluation?method=StartEvaluation')">开始评教</a></li>
				</c:if>
				<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/evaluation?method=ViewEvaluation')">查询评教结果</a></li>
			</ul>
		</div>
		<div title="消息提醒" >
			<ul >		
				<c:if test="${user.a_type==0}">
					<li class="in"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/InputMessageInformation.jsp')">发布消息</a></li>										
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/UpdateMessageInformation.jsp')">查看消息</a></li>	
				</c:if>	
				<c:if test="${user.t_type==1}">	
					<li class="in"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/message?method=FindTeacherClass')">发布消息</a></li>							
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/ViewMessage.jsp')">查看教师消息</a></li>
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/message?method=SearchTeacherClass')">更改学生消息</a></li>	
				</c:if>			
				<c:if test="${user.s_type==2}">								
					<li class="search"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/ViewMessage.jsp')">查看消息</a></li>	
				</c:if>			
			</ul>
		</div>
		<div title="其他功能" >
			<ul>									
				<li class="update"><a href="#" onclick="showcontent('${pageContext.request.contextPath}/UpdataPassword.jsp')">修改密码</a></li>							
			</ul>
		</div>
	</div>
</body>
</html>