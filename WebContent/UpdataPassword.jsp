<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 divansitional//EN" "http://www.w3.org/div/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>

	.error{
			color:red;
		}
</style>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
		<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/jquery.validate.min.js"></script>
		<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
		<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
		<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

			$(function(){
				$("#form1").validate({
					rules:{
						"password":{
							"required":true,
							"rangelength":[6,12]
						},
						"repassword":{
							"required":true,
							"rangelength":[6,12],
							"equalTo":"#password"
						}
					},
					messages:{
						"password":{
							"required":"密码不能为空",
							"rangelength":"密码长度6-12位"
						},
						"repassword":{
							"required":"密码不能为空",
							"rangelength":"密码长度6-12位",
							"equalTo":"两次密码不一致"
						},
					}
				});
				
				<c:if test="${flag==1}">
				$.messager.show({
					title:'提示',
					msg:'修改密码成功',						
				});
				</c:if>	
				<c:if test="${flag==0}">
					$.messager.alert('错误','修改密码失败','error')							
				</c:if>			
										
			});
			
			
</script>
</head>
<body style="height: 100%; border: 1px; ">
	
	<div style="width: 500px;margin: auto;">
		 <div>
				<div style="margin-left: 60px"><h4>修改个人密码</h4></div>
				<hr  style="width: 50%; float: left;">
				</div>
		<br>
		
		  <form id="form1" action="${pageContext.request.contextPath }/practice?method=UpdataPassword" method="post">
		
				<div style="clear: both;">
					<div style="width:80px;height: 2.4em;float: left;">用户名:</div>
					<div style="float:left">
						<c:if test="${user.a_type==0}">
							<input type="text" name="username" value="${user.a_no }" disabled="disabled">
						</c:if>
						<c:if test="${user.t_type==1}">
							<input type="text" name="username" value="${user.t_no }" disabled="disabled">
						</c:if>
						<c:if test="${user.s_type==2}">
							<input type="text" name="username" value="${user.s_no }" disabled="disabled">
						</c:if>
					</div>
				</div>
				<div style="clear: both;">
					<div style="width:80px;height: 2.4em;float: left;">新密码:</div>
					<div style="float:left">
						 <input type="password" id="password" name="password"  placeholder="请输入密码">
					</div>
				</div>
				<div style="clear: both;">
					<div style="width:80px;height: 2.4em;float: left;">确认密码:</div>
					<div style="float:left">
						 <input type="password" name="repassword"  placeholder="请输入确认密码">
					</div>
				</div>
				<div style="clear: both;margin-left: 60px">
					<button type="submit" id="submit_form" value="提交" class="button_ok">
							提交
					</button>
					<input type="reset"  value="重置" style="margin-left: 50px"/>
				</div>
				
				
				
			</form>
   				</div>
	
</body>
</html>