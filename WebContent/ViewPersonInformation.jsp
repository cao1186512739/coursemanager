<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
		<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
		<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
		<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
		<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
		<script>
			$(function(){
            //页面加载完毕后开始执行的事件
            $("#link_type").change(function(){
            	
                if($(this).val()=="1")
                {
                    $("table #teacher").show();
                    $("table #student").hide();
                }
                if($(this).val()=="2")
                {
                    $("table #teacher").hide();
                    $("table #student").show();
                }
            });
        });
		</script>
</head>
<body>
<table class="easyui-datagrid" title="课程信息查询" data-options="
					singleSelect:true,
					collapsible:false,
					url:'11.json',
					method:'get',
					pagination:true, 
					pageSize:5,
					pageList:[ 5, 10, 15, 20],
					fit:true,
					fitColumns:true,
					border:false,
					toolbar: '#tb',
					footer:'#ft',
					selectOnCheck:true,
					idField : 'coursename',
					checkbox : true,
					nowarp : true,
					rownumbers:true,
					">
					<thead>
				<tr>
					<th data-options="field:'BadgeNumber',width:180,align:'center',checkbox:true">工号</th>
					<th data-options="field:'YouNmae',width:100,align:'center'">姓名</th>
					<th data-options="field:'TeacherOffice',width:80,align:'center'">教研室</th>
					<th data-options="field:'CollegeDepartment',width:80,align:'center'">院系</th>
					<th data-options="field:'HolderOffice',width:80,align:'center'">职称</th>
					<th data-options="field:'Operate',width:80,align:'center'">操作</th>
				</tr>
			</thead>		
		</table>

		<div id="tb" style="padding:10px 5px;">
			<div>
				<tr>
                <td align="right">类&nbsp;型:</td>
                <td>
                    <select id="link_type" name="link_type" style="width: 162px;">
                    	<!--<option value="0">-请选择-</option>-->
                        <option value="1" style="width: 160px;">教师</option>
                        <option value="2" style="width: 160px;">学生</option>
                    </select>
                </td>
            </tr>
			</div>
			<div>
				<table>
				<tr id="teacher" style="float: left;">
	                <td>工&nbsp;号:</td>
	                <td align="left"><input type="text" name="" id="" style="width: 160px;"/></td>
	            </tr>
	            <tr id="student" style="display:none;float: left;" >
	                <td>学&nbsp;号:</td>
	                <td align="left"><input type="text" name="" id="" style="width: 160px;"/></td>
	            </tr>
	            
	            <tr style="float: left;">
	                <td>姓&nbsp;名:</td>
	                <td align="left"><input type="text" name="" id="" style="width: 160px;"/></td>
	            </tr>
	            
	            <tr id="teacher" style="float: left;">
	                <td>教研室:</td>
	                <td align="left"><input type="text" name="" id="" style="width: 160px;"/></td>
	            </tr>
	            <tr id="student" style="display:none;float: left;" >
	                <td>班&nbsp;级:</td>
	                <td align="left"><input type="text" name="" id="" style="width: 160px;"/></td>
	            </tr>
	            <tr style="float: left;">
	            	<td >
					<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
					</td>
				</tr>
				</table>
			</div>
		</div>
		
		<div id="ft" style="padding:2px 5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" >增加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" >修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" >删除</a>
		
	</div>
	
</body>
</html>