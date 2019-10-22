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
					url:'practice?method=ViewGradeStudent',
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
				        {field:'tk_name',title:'课程名',width:80,align:'center'},
				        {field:'tk_type',title:'任务类型',width:80,align:'center'},  	
				        {field:'g_1',title:'内容1',width:100,align:'center'},	
				        {field:'g_2',title:'内容2',width:100,align:'center'},
				        {field:'g_3',title:'内容3',width:100,align:'center'},	
				        {field:'g_4',title:'内容4',width:100,align:'center'},
				        {field:'g_5',title:'内容5',width:100,align:'center'},	
				        {field:'g_total',title:'总成绩',width:100,align:'center'}
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
		        
				
			});
		</script>	
		<div class="easyui-layout" fit="true" border="false">
		
			<div region="center" border="false">
				<table id="dg"></table>
			</div>
		</div>
		

</body>
</html>