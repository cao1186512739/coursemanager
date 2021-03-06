<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script type="text/javascript">
	$(function() {
		//页面加载完毕后开始执行的事件
		//页面加载完成后，创建数据表格datagrid

		//定义标题行所有的列
		$("#mytable").datagrid({
			//定义标题行所有的列
			columns : [ [  {
				title : '编号',
				field : 's_id',
				checkbox : true
			} , {
				title : '学号',
				field : 's_no',
				width : 100
			}, {
				title : '姓名',
				field : 's_name',
				width : 100
			}, {
				title : '院系',
				field : 's_dept',
				width : 150
			}, {
				title : '班级',
				field : 's_class',
				width : 80
			}, ] ],
			//		idField:'c_id' ,		//只要创建数据表格 就必须要加 ifField
			//指定数据表格发送ajax请求的地址
			url : 'person?method=ViewStudent',
			fit : true,//列表直接在下面
			fitColumns : true,
			striped : true,//隔行变色
			loadMsg : "数据正在加载,请稍等...",
			rownumbers : true,
			//	singleSelect : true,

			//显示分页条
			pagination : true,
			pageList : [ 10, 20, 30 ]
		});

		$("#link_type")
				.change(
						function() {
							if ($(this).val() == "1") {
								window.location.href = "${pageContext.request.contextPath}/ViewTeacher.jsp";
							}
						});

		/*
		 * 点击搜索按钮
		 */
		$('#searchbtn').click(function() {
			$('#mytable').datagrid('load', serializeForm($('#mysearch')));
		});

		/**
		清空按钮 
		 **/
		$('#clearbtn').click(function() {
			$('#mysearch').form('clear');
			$('#mytable').datagrid('load', {});
		});

	});

	//js方法：序列化表单 			
	function serializeForm(form) {
		var obj = {};
		$.each(form.serializeArray(), function(index) {
			if (obj[this['name']]) {
				obj[this['name']] = obj[this['name']] + ',' + this['value'];
			} else {
				obj[this['name']] = this['value'];
			}
		});
		return obj;
	}
</script>
</HEAD>
<body>


	<div id="cc" class="easyui-layout" fit="true" border="false">
		<div data-options="region:'north',title:'人员信息查询	',split:true"
			style="height: 64px; overflow: hidden;">
			<form id="mysearch" method="post">
				<table class="tableForm datagrid-toolbar"
					style="width: 100%; height: 100%;">
					<c:if test="${user.a_type=='0'}">
					<tr style="float: left;">
					
						<td>类型</td>
						<td>
				        <select id="link_type" style="width: 140px;">
									<option value="2">学生</option>
									<option value="1">教师</option>
								</select>
							</td>
					</tr>
					</c:if>
					<tr style="float: left;">
						<td>&nbsp;&nbsp;&nbsp;学号</td>
						<th><input name="s_no" class="easyui-form" style="" /></th>
					</tr>
					<tr style="float: left;">
						<td>&nbsp;&nbsp;&nbsp;姓名</td>
						<td><input name="s_name" class="easyui-form" style="" /></td>
					</tr>
					<tr style="float: left;">
						<td>&nbsp;&nbsp;&nbsp;班级</td>
						<td><input name="s_class" class="easyui-form" style="" /></td>
					</tr>
					
					<tr style="float: left;">
						<td>
						<a id="searchbtn" iconCls='icon-search' class="easyui-linkbutton">查询</a>
				        <a id="clearbtn" iconCls='icon-clear' class="easyui-linkbutton">清空</a>
						</td>
					</tr>


				</table>
			</form>
		</div>


		<div data-options="region:'center',title:'学生基本信息'"
			style="padding: 5px; background: #eee;">
			<table id="mytable"></table>
		</div>
	</div>




</body>
</HTML>
