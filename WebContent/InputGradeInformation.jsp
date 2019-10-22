<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 divansitional//EN" "http://www.w3.org/div/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	.error{
			color:red;
		}
</style>
<script type="text/javascript">
		$(function(){
			
			$("#form1").validate({
				rules:{
					"tk_name":{
						"required":true
					},
					"tk_class":{
						"required":true
					},
					"tk_type":{
						"required":true
					},
					
				},
				
				messages:{
					"tk_name":{
						"required":"请选择课程名"
					},
					"tk_class":{
						"required":"请选择专业班级"							
					},
					"tk_type":{
						"required":"请选择任务类型"
					}
				}
			});
		
					
			
			$("#tk_name").change(function() {
				var tkname = $(this).val();
				var content = '<option value="">'+'请选择'+"</option>";
				$.ajax({
					url : 'practice?method=FindGradeClassByTname',
					data : {"tk_name":tkname},
					dataType : 'json',
					success : function(json){
						if (json.length>0) {
							for (var i = 0; i < json.length; i++) {
								content += '<option value="'+json[i].tk_class+'">'+json[i].tk_class+"</option>";
							}
							$("#tk_class").html(content);
						}
					}
				});
			});
			$("#tk_class").change(function() {
				var tname = $("#tk_name").val();
				var tclass = $(this).val();
				var content = "";
				$.ajax({
					url : 'practice?method=FindGradeType',
					data : {"tk_name":tname,"tk_class":tclass},
					dataType : 'json',
					success : function(json){
						if (json.length>0) {
							for (var i = 0; i < json.length; i++) {
								content += '<option value="'+json[i].tk_type+'">'+json[i].tk_type+"</option>";
							}
							$("#tk_type").html(content);
						}
					}
				});
			});	
			
			
		});

</script>
</head>
<!-- background-color: #95B8E7; -->
<body style="height: 100%; border: 1px;  ">

	<div style="width: 500px;margin: auto;">
	<form id="form1" method="post" action="${pageContext.request.contextPath }/practice?method=ViewStudent">
		<!-- <form action="${pageContext.request.contextPath }/" method="post"> -->
				<div>
					<div style="width:100px;height: 2.4em;float: left;">&nbsp;课程名:</div>
					<div style="float:left">
						<select id="tk_name" name="tk_name" style="width: 240px;height: 1.8em;">
							<option value="">请选择</option>
								<c:forEach items="${teachercourseList }" var="course">
									<option value="${course.tk_name }">${course.tk_name}</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<div style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">专业班级:</div>
					<div style="float:left">
					
						<select id="tk_class" name="tk_class" style="width: 240px;height: 1.8em;">
							<option value="">请选择</option>
							
						</select>
					</div>
				</div>
				<div style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">任务类型:</div>
					<div style="float:left">
						<select id="tk_type" name="tk_type" style="width: 240px;height: 1.8em;">
							<option value="">请选择</option>
							
						</select>
					</div>
				</div>				
				
				<div style="clear: both;">
				 
					<input type="submit" id="addtask" value="检索" style="width: 340px;height: 23px;">	
					
					 <!--
					<button type="submit" id="submit_form" value="检索" class="button_ok">
							提交
					</button>	
					 -->			
				</div>
		</form>
	</div>
</body>
</html>