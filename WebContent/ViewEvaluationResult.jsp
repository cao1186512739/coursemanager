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
		//页面加载完成后，创建数据表格datagrid
		$("#mytable").datagrid({
			//定义标题行所有的列
			columns : [ [ {
				title : '标号',
				field : 'te_id',
				checkbox : true
			}, {
				title : '授课态度',
				field : 'te_attitude_grade',
				width : 100
			}, {
				title : '授课方式',
				field : 'te_method_grade',
				width : 100
			}, {
				title : '课堂互动',
				field : 'te_interact_grade',
				width : 150
			}, {
				title : '多媒体',
				field : 'te_multimedia_grade',
				width : 80
			},{
				title : '影响力',
				field : 'te_effect_grade',
				width : 80
			}, ] ],
			//		idField:'c_id' ,		//只要创建数据表格 就必须要加 ifField
			//指定数据表格发送ajax请求的地址
			url : 'evaluation?method=ViewEvaluationResultTeacher',
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

	});
		
</script>
</HEAD>
<body>

	<div id="cc" class="easyui-layout" fit="true" border="false">
		<div data-options="region:'center',title:'查看评教'"
			style="padding: 5px; background: #eee;">
			<table id="mytable"></table>
		</div>
	</div>
	
	
	
		
	
</body>
</HTML>