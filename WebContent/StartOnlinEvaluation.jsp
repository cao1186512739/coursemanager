<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script language="javascript"
	src="${pageContext.request.contextPath}/js/EasyUI/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/EasyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/EasyUI/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/EasyUI/jquery.min.js`"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/EasyUI/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	 <div id="cc" class="easyui-layout" fit="true" border="false">
		<div data-options="region:'center',title:'学生基本信息'"
			style="padding: 5px; background: #eee;">

			<form id="mysearch" action="${pageContext.request.contextPath }/evaluation?method=ProcessEvaluation" method="post" >
				<table cellSpacing="1" cellPadding="5" width="100%" align="center"
					bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
							height="26"><strong><STRONG>进行评教</STRONG> </strong></td>
					</tr>
                     
                     <%
                       String textContext= request.getParameter("c_id");
                     %>
                    <input type="hidden" name="c_id" value=<%=textContext%>>
          
					<tr>
						<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
							授课态度:</td>
						<td class="ta_01" bgColor="#ffffff" colspan="3" width="100px">
							<select name="te_attitude_grade" id="te_attitude_grade" style="width: 160px;">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
						</select>
						</td>
					</tr>
					<tr>
						<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
							授课方式:</td>
						<td class="ta_01" bgColor="#ffffff" colspan="3"><select
							name="te_method_grade" id="te_method_grade" style="width: 160px;">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
						</select></td>
					</tr>
					<tr>
						<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
							课堂互动:</td>
						<td class="ta_01" bgColor="#ffffff" colspan="3"><select
							name="te_interact_grade" id="te_interact_grade" style="width: 160px;">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
						</select></td>
					</tr>
					<tr>
						<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
							多媒体:</td>
						<td class="ta_01" bgColor="#ffffff" colspan="3"><select
							name="te_multimedia_grade" id="te_multimedia_grade" style="width: 160px;">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
						</select></td>
					</tr>
					<tr>
						<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
							影响力:</td>
						<td class="ta_01" bgColor="#ffffff" colspan="3"><select
							name="te_effect_grade" id="te_effect_grade" style="width: 160px;">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
						</select></td>
					</tr>



					<tr>
						<td class="ta_01" style="WIDTH: 100%" align="center"
							bgColor="#f5fafe" colSpan="4">
							<button type="submit" value="确定" class="button_ok">&#30830;&#234160;</button>
							<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
							<INPUT class="button_ok" type="button" onclick="history.go(-1)"
							value="返回" /> <span id="Label1"></span>
						</td>
					</tr>
				</table>


			</form>




		</div>
	</div>


</body>
</html>