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
<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<style>		
		.error{
			color:red;
		}
		</style>
<script type="text/javascript">
$(function() {
	$("#form1").validate({
		rules:{
			"myscope":{
				"required":true
			},
			"m_content":{
				"required":true
			}
		},
		messages:{
			"myscope":{
				"required":"发布对象不能为空"
			},
			"m_content":{
				"required":"消息内容不能为空"
			}
		}
	});
	
	//页面加载完毕后开始执行的事件
	$("#m_type").change(function() {

		if ($(this).val() == "教师") {
			$("form #teacher").show();
			$("form #student").hide();
		}
		if ($(this).val() == "学生") {
			$("form #teacher").hide();
			$("form #student").show();
		}
	});
	
	<c:if test="${flag==1}">
	$.messager.show({
		title:'提示',
		msg:'发布消息成功',						
	});
	</c:if>	
	<c:if test="${flag==0}">
		$.messager.alert('错误','发布消息失败','error')							
	</c:if>			


$("#mt_scope").change(function() {
	var myscope = $(this).val();
	var mtype = $("#m_type").val();
	var content = "";
	$.ajax({
		url : 'message?method=FindScope',
		data : {"myscope":myscope,"m_type":mtype},
		dataType : 'json',
		success : function(json){
			if (json.length>0) {
				for (var i = 0; i < json.length; i++) {
					content += '<option value="'+json[i]+'">'+json[i]+'</option>';
				}
				$("#tscope").html(content);
				
			}
		}
	});
});

$("#ms_scope").change(function() {
	var myscope = $(this).val();
	var mtype = $("#m_type").val();
	var content = "";
	$.ajax({
		url : 'message?method=FindScope',
		data : {"myscope":myscope,"m_type":mtype},
		dataType : 'json',
		success : function(json){
			if (json.length>0) {
				for (var i = 0; i < json.length; i++) {
					content += '<option value="'+json[i]+'">'+json[i]+'</option>';
				}
				$("#sscope").html(content);
				
			}
		}
	});
});
	
});
</script>
</head>
<body style="height: 100%; border: 1px; ">

	<div  style="width: 500px;height: 600px;margin: auto;">
		<form  id="form1" action="${pageContext.request.contextPath }/message?method=AddMessage" method="post">
				<div>
					<th >对象类型:</th>
					<td><select id="m_type" name="m_type"
						style="width: 250px; margin: 13px">
							<option value="教师">教师</option>
							<option value="学生">学生</option>							
					</select></td>
				</div>
				<div>	
					<th>消息类型:</th>
					<td><select id="m_object" name="m_object"
						style="width: 250px; margin: 13px">
							<option value="通知">通知</option>
							<option value="任务">任务</option>							
					</select></td>
											
				</div>
				<div id="teacher" >
					<th>发布对象:</th>
					<td><select id="mt_scope" name="myscope"
						style="width: 250px; margin: 13px">
							<option value="">请选择</option>					
							<option value="t_dept">院系</option>
							<option value="t_office">教研室</option>							
					</select>
					</td>
					</div>
					<div id="teacher" >					
						<div>
							<select id="tscope" name="mt_scope"
							style="width: 250px; margin: 13px;margin-left: 85px">
							<option value="">请选择</option>									
							</select>
						</div>
				    </div>
				<div id="student" style="display: none;">
					<th>发布对象:</th>
					<td><select id="ms_scope" name="myscope"
						style="width: 250px; margin: 13px">
							<option value="">请选择</option>					
							<option value="s_dept">院系</option>
							<option value="s_class">专业班级</option>							
					</select>
					</td>
					</div>
					<div id="student" style="display: none;">			
					<div>
					<select id="sscope" name="ms_scope"
						style="width: 250px; margin: 13px;margin-left: 85px">
							<option value="">请选择</option>									
					</select></div>
				</div>
				
				<div style="clear: both;">
					<div style="width:89px;height: 2.4em;float: left;" >消息内容:</div>
					<!--  <td align="right"><input type="text" name="t_content" id="t_content"
						style="width: 250px;" /></td>-->
					<div><textarea name="m_content" rows="10" cols="33" style="magin: 13px"></textarea></div>
				</div>
													
				<p>
				<div style="clear: both;margin-left: 90px">
					<button type="submit" value="提交" class="button_ok">提交</button>				
					<input type="reset"  value="重置" style="margin-left: 50px"/>
				</div>
		</form>
	</div>
</body>
</html>

