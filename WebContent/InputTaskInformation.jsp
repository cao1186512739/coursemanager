<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 divansitional//EN" "http://www.w3.org/div/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

	.error{
			color:red;
		}

.btn02 { width:100px; height:24px; border:1px solid #87a3c1; background:url(/uploadfile/1632433537.gif); color:#555; font-size:12px; line-height:180%; cursor:pointer;}
.btn04 { width:100px; height:24px; border:1px solid #a2904d; background:url(/uploadfile/1632433537.gif)　0　-66px; color:#630; font-size:12px; cursor:pointer; line-height:180%;}
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
						"tk_name":{
							"required":true
						},
						"tk_class":{
							"required":true
						}
					},
					messages:{
						"tk_name":{
							"required":"请选择课程名"
						},
						"tk_class":{
							"required":"请选择专业班级"							
						}
					}
				});
				$("#form2").validate({
					rules:{
						"tk_title":{
							"required":true
						}
					},
					messages:{
						"tk_title":{
							"required":"dsa"
						}
					}
				});
				
				
				
				$("#tk_name").change(function() {
					var tkname = $(this).val();
					var content = "";
					$.ajax({
						url : 'practice?method=FindTeacherClassByTname',
						data : {"tk_name":tkname},
						dataType : 'json',
						success : function(json){
							if (json.length>0) {
								for (var i = 0; i < json.length; i++) {
									content += '<option value="'+json[i]+'">'+json[i]+'</option>';
								}
								$("#tk_class").html(content);
								
							}
						}
					});
				});
				var title = "";
				var Tcontent = "";
				var value = "";
				$("#addtask").click(function(){
					title = $("#tk_title").val();
					Tcontent = $("#tk_content").val();
					value = $("#tk_value").val();
					$.ajax({
						type : 'post',
						url : 'practice?method=AddTask',
						data : $("#form2").serialize(),
						dataType : 'json',
						success : function(json){																					
							$("#mydialog").dialog('close');
							
							$.messager.show({
								title : '提示',
								msg : json.msg
							});
							
							var content = '<div style="margin-left: 10px;">';
							content += '<div style="font-weight: 400;font-size: 20px;color: red;">'+title+'</div>';
							content += '<div style="font-weight: 400;font-size: 20px;color: red;">'+value+'分'+'</div>';
							content += '<div style="line-height: 40px;">'+' 内容：'+Tcontent+'</div>';
							content += '<div style="border-top:#00686b 1px dashed; overflow:hidden;height:1px"></div>';
							content += '</div><br>'
							$("#taskcontent2").append(content);
							
						},
						error:function(json){
						$.messager.alert('错误',json.msg,'error')
					}																						
					});
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
				
			});
</script>
</head>
<body style="height: 100%; border: 1px; ">
	
	<div style="width: 500px;margin: auto;">
		  <form id="form1" action="${pageContext.request.contextPath }/practice?method=AddTask2" method="post">
				<div>
					<div style="width:100px;height: 2.4em;float: left;">课程名:</div>
					<div style="float:left">
						<select id="tk_name" name="tk_name" style="width: 250px;height: 23px;" >
							<option value="">请选择</option>
								<c:forEach items="${teachercourseList }" var="course">
									<option value="${course.c_name }">${course.c_name}</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<div style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">专业班级:</div>
					<div style="float:left">
					
						<select id="tk_class" name="tk_class" style="width: 250px;height: 23px;" >
							<option value="">请选择</option>
							
						</select>
					</div>
				</div>
				<div style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">任务类型:</div>
					<div style="float:left">
						<select id="tk_type" name="tk_type" style="width: 250px; height: 23px;" >	
							<option value="平时考勤">平时考勤</option>						
							<option value="平时测试">平时测试</option>
							<option value="平时作业">平时作业</option>
							<option value="实验">实验</option>
							
						</select>
					</div>
				</div>
				<div style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">次数:</div>
					<div style="float:left">
						<select id="tk_time" name="tk_time" style="width: 250px; height: 23px;" >
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</div>
				</div>
				<div style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">任务比重:</div>
					<div style="float:left">
						<select id="tk_proportion" name="tk_proportion" style="width: 250px; height: 23px;" >
							<option value="10%">10%</option>
							<option value="15%">15%</option>
							<option value="20%">20%</option>
							<option value="25%">25%</option>
							<option value="30%">30%</option>
							<option value="35%">35%</option>
							<option value="40%">40%</option>
							<option value="45%">45%</option>
							<option value="50%">50%</option>
							<option value="55%">55%</option>
							<option value="60%">60%</option>
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
	
</body>
</html>