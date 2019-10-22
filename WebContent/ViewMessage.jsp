<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
		 <script type="text/javascript">
			$(function(){
		
				});
		</script>

</head>
<body>
<script type="text/javascript">
			$(function(){
				editRow = undefined;
				datagrid = $("#dg").datagrid({
					url:'message?method=ViewMessage',
					title:"信息查询",
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
					idField : 'm_id',
					checkOnSelect:true,
					selected:true,
					nowarp : true,
					rownumbers:true,
					sortName: "m_id", //初始化表格时依据的排序 字段 必须和数据库中的字段名称相同
            		sortOrder: "asc", //正序
					columns:[[    
				        {field:'m_id',title:'编号',checkbox:true},    
				        {field:'m_type',title:'对象类型',width:100,align:'center'},    
				        {field:'m_object',title:'消息类型',width:80,align:'center'},				            
				        {field:'m_scope',title:'发布对象',width:80,align:'center'},	
				        {field:'m_content',title:'消息内容',width:80,align:'center'}				        
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
			<c:if test="${user.a_type==0}">
			<div region="north" border="false" title="查询" style="height: 64px;overflow: hidden;">
				<form id="searchForm">
					<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					
						<tr style="float: left;">
							<th>对象类型</th>
							<td>
								<select id="m_type" name="m_type" style="width: 200px;">
									<option value="">请选择</option>
									<option value="教师">教师</option>
									<option value="学生">学生</option>																			
								</select>
							</td>
						</tr>
								
						<tr  style="float: left;">
							<th>消息类型</th>
							<td>
								<select id="tk_name" name="tk_name" style="width: 200px;">
									<option value="">请选择</option>														
										<option value="消息">消息</option>
										<option value="通知">通知</option>						
								</select>
							</td>
						</tr>
						
						<tr  style="float: left;">
							<th>发布对象</th>
							<td>
								<select id="tk_name" name="tk_name" style="width: 200px;">
									<option value="">请选择</option>														
										<option value="院系">院系</option>
										<option value="教研室">教研室</option>										
								</select>
							</td>
						</tr>
						<tr  style="float: left;">
							
							<td>
								<select id="m_scpope" name="m_scpope" style="width: 200px;">
									<option value="">请选择</option>														
										<c:forEach items="${allnameList }" var="aname">
											<option value="${aname.c_name }">${aname.c_name}</option>
										</c:forEach>								
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
			</c:if>
			<div region="center" border="false">
				<table id="dg"></table>
			</div>
		</div>
		

</body>
</html>