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
</head>
<body>
<script type="text/javascript">
			$(function(){
				editRow = undefined;
				datagrid = $("#dg").datagrid({
					url:'message?method=ViewTMessage',
					title:"消息查询",
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
					sortName: 'm_id', //初始化表格时依据的排序 字段 必须和数据库中的字段名称相同
            		sortOrder: 'asc', //正序
					columns:[[    
						{field:'m_id',title:'编号',checkbox:true},    							            
						{field:'m_type',title:'对象类型',width:80,align:'center'},							
						{field:'m_object',title:'消息类型',width:80,align:'center'},								
						{field:'m_scope',title:'发布对象',width:100,align:'center'},    
						{field:'m_content',title:'消息内容',width:80,align:'center',editor:'text'}																		
				    ]],
					toolbar: [{
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							var rows = datagrid.datagrid('getSelections');
							if (rows.length > 0) {
								var url = '';
								$.messager.confirm('请确认','您确定要删除当前所有选择的项目？',function(b){
									if (b) {
										var ids = [];
										for (var i = 0;i < rows.length; i++) {
											 ids.push(rows[i].m_id);
										}									
										$.ajax({
											url : 'message?method=DelMessage',
											data : {
												ids : ids.join(',')
											},
											dataType : 'json',
											success : function(json){
												if(json&&json.success){
												datagrid.datagrid('load');
												datagrid.datagrid('unselectAll');
												$.messager.show({
													title : '提示',
													msg : json.msg
												});
												}else{
													$.messager.alert('错误',json.msg,'error')
												}
											}
										});
									
									}
								});
							}else{
								$.messager.alert('提示','请选择要删除的记录！','error')
							}
						}
					},'-',{
						text:'修改',
						iconCls:'icon-edit',
						handler:function(){
							var rows = datagrid.datagrid('getSelections');
							if (rows.length == 1) {
								if (editRow != undefined) {
							datagrid.datagrid('endEdit',editRow);
								}	
								if (editRow == undefined) {
									var index = datagrid.datagrid('getRowIndex',rows[0]);
									datagrid.datagrid('beginEdit',index);
									editRow = index;
									datagrid.datagrid('unselectAll');
								}
							}else{
								$.messager.alert('提示','请选择一行进行修改！','error');
							}
						}
					},'-',{
						text:'保存',
						iconCls:'icon-save',
						handler:function(){
							datagrid.datagrid('endEdit',editRow);
						}
					},'-',{
						text:' 取消编辑',
						iconCls:'icon-redo',
						handler:function(){														
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('rejectChanges');
							editRow = undefined;
						}
					},'-',{
						text:' 取消选中',
						iconCls:'icon-undo',
						handler:function(){
							datagrid.datagrid('unselectAll');
							}
						}],
					onAfterEdit:function(rowIndex,rowData,changes){
						//console.info(rowData);
//						console.info(changes);
						var updated = datagrid.datagrid('getChanges','updated');
						var url = '';						
						if(updated.length > 0){
							url = 'message?method=UpdateMessage';
						}
						$.ajax({
							url : url,
							data : rowData,
							dataType : 'json',
							success : function(json){
								if(json&&json.success){
									datagrid.datagrid('acceptChanges');
									$.messager.show({
										msg : json.msg,
										title : '成功'
									});
								}else{
									datagrid.datagrid('rejectChanges');
									$.messager.alert('错误',json.msg,'error')
								}
								editRow = undefined;
								datagrid.datagrid('unselectAll');
							}
						});					
					},
					
					onDblClickRow : function(index, row){
						if (editRow != undefined) {
							datagrid.datagrid('endEdit',editRow);
						}	
						if (editRow == undefined) {						
							datagrid.datagrid('beginEdit',index);
							editRow = index;
							datagrid.datagrid('unselectAll');
						}
					}
					
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
						//"name":"daf"
						
				});
				$("#cleanSearch").click(function(){			
					datagrid.datagrid('load',{});
					history.go(0);
				});	
								
				
		});
		</script>	
		<div class="easyui-layout" fit="true" border="false">
			<div region="north" border="false" title="查询" style="height: 64px;overflow: hidden;">
				<form id="searchForm">
					<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">						
						<tr style="float: left;">
							<th>专业班级</th>
							<td>
								<select id="m_scope" name="m_scope" style="width: 200px;">
									<option value="">请选择</option>
									<c:forEach items="${teacherclassList }" var="course">
										<option value="${course.c_class }">${course.c_class }</option>
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
			<div region="center" border="false">
				<table id="dg"></table>
			</div>
		</div>
		

</body>
</html>