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
			$(function(){
		
				});
		</script>

</head>
<body>
<script type="text/javascript">
			$(function(){
				editRow = undefined;
				 $.ajax({
				        url :"practice?method=GetTable",
				        type:"POST",
		                dataType:'json',
		                data:{},
				        success : function(data) {
				        	datagrid = $("#dg").datagrid({
								url:'practice?method=ViewTask',
								title:"任务信息查询",
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
								idField : 'tk_id',
								checkOnSelect:true,
								selected:true,
								nowarp : true,
								rownumbers:true,
								sortName: "tk_id", //初始化表格时依据的排序 字段 必须和数据库中的字段名称相同
			            		sortOrder: "asc", //正序
								columns:data
							});
				        }
				    });
				
				
				}
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
										<c:forEach items="${teachercourseList }" var="tcourse">
											<option value="${tcourse.c_name }">${tcourse.c_name}</option>
										</c:forEach>
									</c:if>
									<c:if test="${user.s_type==2 }">
										<c:forEach items="${studentcourseList }" var="scourse">
											<option value="${scourse.tk_name }">${scourse.tk_name}</option>
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
											<c:forEach items="${teacherclassList }" var="tclass">
												<option value="${tclass.c_class }">${tclass.c_class}</option>
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
									<option value="小测试">小测试</option>
									<option value="作业">作业</option>
									<option value="实验">实验</option>
									<option value="期中考试">期中考试</option>
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