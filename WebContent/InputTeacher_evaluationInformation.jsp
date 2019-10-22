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
		<form action="${pageContext.request.contextPath }/evaluation?method=AddEvaluation" method="post">
				<div>
					<th align="right">&nbsp;类&nbsp;&nbsp;型:</th>
					<td><select id="link_type" name="link_type"
						style="width: 150px;">
							<option value="2">学生</option>
					</select></td>
				</div>
				<div>
					<th>&nbsp;教学态度:</th>
					<td align="right"><input type="text" name="te_attitude_grade" id="te_attitude_grade"
						style="width: 150px;" /></td>
				</div>
				
				<div>
					<th>&nbsp;教学方法:</th>
					<td align="right"><input type="text" name="te_method_grade" id="te_method_grade"
						style="width: 150px;" /></td>
				</div>
				
   				<div>
					<th>&nbsp;教学作用:</th>
					<td align="right"><input type="text" name="te_interact_grade" id="te_interact_grade"
						style="width: 150px;" /></td>
				</div>				
				
				<div>
					<th>多媒体效果:</th>
					<td align="right"><input type="text" name="te_multimedia_grade"
						id="te_multimedia_grade" style="width: 150px;" /></td>
				</div>
				
				<div>
					<th>&nbsp;教学影响:</th>
					<td align="right"><input type="text" name="te_effect_grade" id="te_effect_grade"
						style="width: 150px;" /></td>
				</div>

				
					<p>
				<div>
					<button type="submit" value="提交" class="button_ok">提交</button>
					<td><input type="button" value="批量导入" /> <input type="file" />
					</td>
				</div>
		</form>
	</div>
</body>
</html>