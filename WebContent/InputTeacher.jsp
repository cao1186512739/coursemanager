<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 divansitional//EN" "http://www.w3.org/div/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body style="height: 100%; border: 1px; background-color: #95B8E7;">

	<div align="center">
		<form action="${pageContext.request.contextPath }/person?method=AddPerson" method="post">
				<div style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">类&nbsp;型:</div>
					<div>
						<select id="link_type" name="link_type"
						style="width: 150px;">
							<option value="1">教师</option>
						</select>
					</div>
				</div>
				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">工&nbsp;号:</div>
					<div style="float:left">
						<input type="text" name="t_no" id="t_no"
						style="width: 150px;" /></div>
				</div>

				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">姓&nbsp;名:</div>
					<div style="float:left">
						<input type="text" name="t_name" id="t_name"
						style="width: 150px;" /></div>
				</div>				

   				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">密&nbsp;码:</div>
					<div style="float:left">
						<input type="text" name="t_password" id="t_password"
						style="width: 150px;" /></div>
				</div>
				
				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">院&nbsp;系:</div>
					<div style="float:left">
						<select id="t_dept" name="t_dept" style="width: 150px;">
							<option value="">-请选择-</option>
							<option value="1">电气学院</option>						
						</select></div>
				</div>				

				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">教研室:</div>
					<div style="float:left"><input type="text" name="t_office"
						id="t_office" style="width: 150px;" /></div>
				</div>
			
				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">职&nbsp;称:</div>
					<div style="float:left">
						<input type="text" name="t_titles"
						id="t_titles" style="width: 150px;" /></div>
				</div>
					<p>
				<div align="center">
					<button type="submit" value="提交" class="button_ok">提交</button>
					<input type="button" onclick="history.go(-1)" value="返回"/>
					<td><input type="button" value="批量导入" /> 
					<input type="file" />					
					
					</td>
				</div>
		</form>
	</div>
</body>
</html>