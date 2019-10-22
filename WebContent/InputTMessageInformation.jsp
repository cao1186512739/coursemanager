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
	
	
	<c:if test="${flag==1}">
	$.messager.show({
		title:'提示',
		msg:'发布通知成功',						
	});
	</c:if>	
	<c:if test="${flag==0}">
		$.messager.alert('错误','发布通知失败','error')							
	</c:if>			
	
});
</script>
</head>
<body style="height: 100%; border: 1px; ">

	<div style="width: 500px;margin: auto;">
		<form  id="form1" action="${pageContext.request.contextPath }/message?method=AddTMessage" method="post">
				<div>
					<th >对象类型:</th>
					<td>
						<input type="text" id="m_type" name="m_type" value="学生"
						style="width: 250px; margin: 13px" readonly="true">																		
					</td>
				</div>
				<div>	
					<th>消息类型:</th>
					<td><input type="text" id="m_object" name="m_object" value="通知"
						style="width: 250px; margin: 13px" readonly="true" >																			
					</td>
											
				</div>
									
				<div id="student">
					<th>发布对象:</th>
					<td><input type="text" id="ms_scope" name="" value="专业班级"
						style="width: 250px; margin: 13px" readonly="true">																		
					</td>
				</div>
				<div id="student">			
					<div>
					<select id="sscope" name="ms_scope"
						style="width: 255px;height:22px; margin: 13px;margin-left: 85px">					
						<c:forEach items="${teacherclassList }" var="course">
							<option value="${course.c_class }">${course.c_class }</option>
						</c:forEach>																													
					</select></div>
				</div>
				
				<div style="clear: both;">
					<div style="width:85px;height: 2.4em;float: left;" >消息内容:</div>
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

