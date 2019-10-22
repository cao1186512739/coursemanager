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
				<div>
					<th align="right">类&nbsp;型:</th>
					<td><select id="link_type" name="link_type"
						style="width: 255px;height:23px">	
							<option value="2">学生</option>
					</select></td>
				</div>
				
				<div id="student">
					<th>学&nbsp;号:</th>
					<td align="right"><input type="text" name="s_no" id="s_no"
						style="width: 150px;" /></td>
				</div>

				
				<div id="student">
					<th>姓&nbsp;名:</th>
					<td align="right"><input type="text" name="s_name" id="s_name"
						style="width: 150px;" /></td>
				</div>

   				
				<div id="student">
					<th>密&nbsp;码:</th>
					<td align="right"><input type="text" name="s_password" id="s_password"
						style="width: 150px;" /></td>
				</div>

				<div id="student">
					<th align="right">院&nbsp;系:</th>
					<td><select id="s_dept" name="s_dept" style="width: 255px;height:23px ">
							<option value="">-请选择-</option>
							<option value="1">电气学院</option>
							
					</select></td>
				</div>
	
				<div id="student">
					<th>班&nbsp;级:</th>
					<td align="right"><input type="text" name="s_class" id="s_class"
						style="width: 150px;" /></td>
				</div>
			
					<p>
				<div>
					<button type="submit" value="提交" class="button_ok">提交</button>
					<input type="button" onclick="history.go(-1)" value="返回"/>
					<td><input type="button" value="批量导入" /> <input type="file" />
					</td>
				</div>
		</form>
	</div>
</body>
</html>