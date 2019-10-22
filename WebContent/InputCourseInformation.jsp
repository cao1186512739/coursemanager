<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
		<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/jquery.validate.min.js"></script>
		<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
		<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
		<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
		<style>
		
		.error{
			color:red;
		}
		</style>
		<script type="text/javascript">
			$(function(){
				$("#form1").validate({
					rules:{
						"c_name":{
							"required":true
						},
						"c_class":{
							"required":true						
						},
						"c_credit":{
							"required":true,
							"number":true
						}
						
					},
					messages:{
						"c_name":{
							"required":"课程名称不能为空"
						},
						"c_class":{
							"required":"专业班级不能为空"						
						},
						"c_credit":{
							"required":"学分不能为空",
							"number":"输入正确的数字"
						}
						
					}
				});
				
				
				<c:if test="${flag==1}">
					$.messager.show({
						title:'提示',
						msg:'添加课程信息成功',						
					});
				</c:if>	
				<c:if test="${flag==0}">
					$.messager.alert('错误','添加课程信息失败','error')							
				</c:if>	
				<c:if test="${flag==2}">
					$.messager.alert('错误','文件必须为excel类型','error')							
				</c:if>	
					<c:if test="${flag==3}">
					$.messager.alert('错误','excel表头不对应','error')							
				</c:if>			
			});
			var myDate = new Date();
			var date = myDate.getFullYear();

			
		</script>
</head>
<body style="height: 100%; border: 1px; ">
	<div style="width: 500px;margin: auto;">
	<div>信息单个输入</div>
				  <form id="form1" action="${pageContext.request.contextPath }/course?method=AddCourse" method="post">
					<input type="hidden" name="t_id" value="2">
					<div style="padding:13px 0;">
						<div style="width:100px;height: 2.4em;float: left;">课程名称</div>
						<div style="float:left">
							<input type="text" id="c_name" name="c_name" style="width: 250px;" />
						</div>
					</div>
					<div style="clear: both;">
						<div style="width:100px;height: 2.4em;float: left;">专业班级</div>
						<div style="float:left">
							<input type="text" id="c_class" name="c_class" style="width: 250px;" />
						</div>
					</div>
					<div style="clear: both;">
						<div style="width:100px;height: 2.4em;float: left;">学&nbsp;&nbsp;分</div>
						<div style="float:left">
							<input type="text" id="c_credit" name="c_credit" style="width: 250px;"  />
						</div>
					</div>
					<div style="clear: both;">
						<div style="width:100px;height: 2.4em;float: left;">学期</div>
						<div style="float:left">
							<select id="c_year" name="c_year" style="width: 255px;height: 23px">							
								<option value="${year-1}-${year}学年第一学期">${year-1}-${year}学年第一学期</option>
								<option value="${year-1}-${year}学年第二学期">${year-1}-${year}学年第二学期</option>
							</select>
						</div>
					</div>	
					<div style="clear: both;">			
						<button type="submit" id="submit_form" value="提交" style="width: 358px;height: 23px;">
							提交
						</button>
					</div>							
				</form>
		</div>
			
			
		<br/>
		<br/>
		<hr/>
			
		<div style="width: 500px;margin: auto;">
		<div>信息批量导入</div>
		<form action="${pageContext.request.contextPath}/course?method=add_course" method="post" enctype="multipart/form-data">
		批量导入:<input type="file" name="file1" style="margin: 18px;">
		<input type="submit" value="提交">
		</form>
		</div>
	
</body>
</html>