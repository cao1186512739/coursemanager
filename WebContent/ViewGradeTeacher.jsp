<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
		<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
		<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
		<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
		<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">																
		</script>
</head>
<body>
<script type="text/javascript">
			$(function(){
				editRow = undefined;
				datagrid = $("#dg").datagrid({
					url:'practice?method=ViewGradeTeacher',
					title:"成绩查询",
					singleSelect:false,
					collapsible:false,					
					method:'post',
					pagination:true, 
					pageSize:10,
					pageList:[ 10, 20, 30, 40],
					fit:true,
					fitColumns:true,
					border:false, 
					selectOnCheck:true,
					//idField : 't_id',
					checkOnSelect:true,
					selected:true,
					nowarp : true,
					rownumbers:true,
					//sortName: "t_id", //初始化表格时依据的排序 字段 必须和数据库中的字段名称相同
            		sortOrder: "asc", //正序
					columns:[[    
				        {field:'g_id',title:'编号',checkbox:true},    
				        {field:'s_no',title:'学号',width:80,align:'center'},    
				        {field:'s_name',title:'姓名',width:80,align:'center'},
				        {field:'tk_class',title:'专业班级',width:80,align:'center'},
				        {field:'tk_name',title:'课程名',width:80,align:'center'},
				        {field:'tk_type',title:'任务类型',width:80,align:'center'},		
				        {field:'g_1',title:'内容1',width:100,align:'center'},	
				        {field:'g_2',title:'内容2',width:100,align:'center'},
				        {field:'g_3',title:'内容3',width:100,align:'center'},	
				        {field:'g_4',title:'内容4',width:100,align:'center'},
				        {field:'g_5',title:'内容5',width:100,align:'center'},	
				        {field:'g_total',title:'总成绩',width:100,align:'center'},
				        {field:'g_remark',title:'备注',width:100,align:'center'}
				    ]]
				});
				$.fn.serializeJson=function(){  
		            var serializeObj={};  
		            var array=this.serializeArray();
		            $(array).each(function(){  
		                if(serializeObj[this.name]){  
		                    if($.isArray(serializeObj[this.name])){  
		                        serializeObj[this.name].push(this.value);  
		                    }else{  
		                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
		                    }  
		                }else{  
		                    serializeObj[this.name]=this.value;   
		                }  
		            });  
		            return serializeObj;  
		        }; 
		        
				$("#search").click(function(){
					var p = $("#searchForm").serializeJson();
					datagrid.datagrid('load',p);					
				});
				
				$("#cleanSearch").click(function(){			
					datagrid.datagrid('load',{});
					/*location.reload()*/ 
					history.go(0);
				});	
			});
		</script>	
		<div class="easyui-layout" fit="true" border="false">
			<div region="north" border="false" title="查询" style="height: 64px;overflow: hidden;">
				<form id="searchForm">
					<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">						
						<tr  style="float: left;">
							<th>课程名</th>
							<td>
								<select id="tk_name" name="tk_name" style="width: 200px;">
									<option value="">请选择</option>					
									<c:if test="${user.a_type==0 }">
										<c:forEach items="${allnameList }" var="aname">
											<option value="${aname.c_name }">${aname.c_name}</option>
										</c:forEach>
									</c:if>
									<c:if test="${user.t_type==1 }">
										<c:forEach items="${teachergradecourseList }" var="tcourse">
											<option value="${tcourse.tk_name }">${tcourse.tk_name}</option>
										</c:forEach>
									</c:if>									
									
								</select>
							</td>
						</tr>
						<c:if test="${user.a_type==0 || user.t_type==1 }">
							<tr  style="float: left;">
								<th>&nbsp;&nbsp;&nbsp;专业班级</th>
								<td>
									<select id="tk_class" name="tk_class" style="width: 200px;">
										<option value="">请选择</option>
										<c:if test="${user.a_type==0 }">
											<c:forEach items="${allclassList }" var="aclass">
												<option value="${aclass.c_class }">${aclass.c_class}</option>
											</c:forEach>
										</c:if>
										<c:if test="${user.t_type==1 }">
											<c:forEach items="${teachergradeclassList }" var="tclass">
												<option value="${tclass.tk_class }">${tclass.tk_class}</option>
											</c:forEach>
										</c:if>
																				
									</select>
								</td>
							</tr>
						</c:if>
						<tr  style="float: left;">
							<th>&nbsp;&nbsp;&nbsp;任务类型</th>
							<td>
								<select id="tk_type" name="tk_type" style="width: 200px;">
									<option value="">请选择</option>
									<option value="平时测试">平时测试</option>
									<option value="平时作业">平时作业</option>
									<option value="实验">实验</option>
									<option value="平时考勤">平时考勤</option>
								</select>
							</td>
						</tr>
						<tr style="float: left;">
			            	<td >
								<a id="search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" >查询</a>
								<a id="cleanSearch" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search">清空  </a>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div region="center" border="false">
				<table id="dg"></table>
			</div>
		</div>
		

</body>
</html>