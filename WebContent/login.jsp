<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 divansitional//EN" "http://www.w3.org/div/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/login.css" />
<style type="text/css">
	.error{
			color:red;
		}
</style> 

<script>
	$(function() {
		
		$("#loginInfo").html("${loginInfo}");
		$("#loginInfo").css("color","red");
		
		$("#form1").validate({
			rules:{
				"username":{
					"required":true
				},
				"password":{
					"required":true
				},
				
			},
			
			messages:{
				"username":{
					"required":"用户名不能为空"
				},
				"password":{
					"required":"密码不能为空"							
				}
			}
		});
		
		
		
		//回车提交功能
		loginInputForm.find('input').on('keyup', function(event) {
			if (event.keyCode == '13') {
				loginInputForm.submit();
			}
		});
	

		$("#dd").panel({
			//modal: true, //模式化  背景变为灰色
			zIndex : 1000,
			title : '登录'
		});
	});
</script>
</head>
<body style="margin: 0px; padding: 0px;">

		<table id="table1" border="0px" align="center"  cellpadding="0px" cellspacing="0px">
			<tr>
				<td id="td1">
					<img src="img/login/c.png" style="display: block;"/>
				</td>
			</tr>
			<tr>
				<td background="img/login/ss.jpg" >
				<form id="form1" action="${pageContext.request.contextPath }/login" method="post">
					<table id="table2" border="0px" align="center" cellspacing="0px" cellpadding="0px">
						<tr>
								<td>
									<select id="select" name="link_type">
										<option value="0">管理员</option>
										<option value="1">教师</option>
										<option value="2">学生</option>
									</select>
								</td>
							</tr>
						
						<tr><td><input id="username" size="20" type="text" name="username" placeholder="请输入用户名"/></td></tr>
						
						<tr><td><input id="password" type="password" size="20" name="password" placeholder="请输入密码"/></td></tr>
						<tr><td><span id="loginInfo"></span></td></tr>
						<tr><td id="td4">
							<input type="checkbox" name="hobby" /><font size="2">记住密码</font>&nbsp;
							<input type="checkbox" name="autoLogin" value="autoLogin" /><font size="2">自动登录</font>
						</td></tr>
						
						<tr><td><input id="login" type="submit" value="登录" /></td></tr>
					</table>
				</td>
				</form>
			</tr>
			
		</table>
		<div align="center" style="margin-top: 10px;font-size: 15px;color: #847f7f;">
			
			Copyright 2017-2018  课程过程网络管理系统  All Rights Reserved.

		</div>
	</body>
</html>