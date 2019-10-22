<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

$(function() {
	
	<c:if test="${flag==1}">
	$.messager.show({
		title:'提示',
		msg:'上传文件成功',						
	});
	</c:if>	
	
	
});

		     var cname;
		     var cclass;
		     var type;	     
		     //授课计划
			function editUser_1(c_name,c_class,index) {	
				 cname=c_name;
				 cclass=c_class;		
		         type=1;
		         //$("#caobing").show(); 
		         window.location.href = "${pageContext.request.contextPath}/ListFileServlet?method=FindTeacherFile&cname="+c_name+"&tclass="+c_class+"&type="+type;
		         //window.location.href = "${pageContext.request.contextPath}/filehome2.jsp?cname="+cname+"&cclass="+cclass+"type"+type;
			}
			
		     //实验计划
			function editUser_2(c_name,c_class,index) {	
				 cname=c_name;
				 cclass=c_class;		
		         type=2;
	     		// $("#caobing").show(); 	
		         window.location.href = "${pageContext.request.contextPath}/ListFileServlet?method=FindTeacherFile&cname="+c_name+"&tclass="+c_class+"&type="+type;
			}
			
		     //课程提问
			function editUser_3(c_name,c_class,index) {	
				 cname=c_name;
				 cclass=c_class;		
		         type=3;
	     		// $("#caobing").show(); 	
	     		 window.location.href = "${pageContext.request.contextPath}/ListFileServlet?method=FindTeacherFile&cname="+c_name+"&tclass="+c_class+"&type="+type;
			}
		     
		     //其他
			function editUser_4(c_name,c_class,index) {	
				 cname=c_name;
				 cclass=c_class;		
		         type=4;
	     		 //$("#caobing").show(); 	
	     		 window.location.href = "${pageContext.request.contextPath}/ListFileServlet?method=FindTeacherFile&cname="+c_name+"&tclass="+c_class+"&type="+type;
			}
			
			function a() {
		//		alert(cname);
		//		alert(cclass);
		//		alert(type)
				document.getElementById("a").action = "${pageContext.request.contextPath}/UploadHandleServlet?cname="+ cname + "&cclass=" + cclass + "&type=" + type;
				document.getElementById("a").submit();
			} 
			
			function b() {	
				window.location.href='${pageContext.request.contextPath}/ViewCourseInformation.jsp'; 
			}
		</script>
		
</head>
<body>

<div id="caobing" style="width:-webkit-fit-content;height:100%;display: none;">
	<form enctype="multipart/form-data" method="post" id="a">
     <!-- <input type="hidden" name="cname" value="c_name"> 
     <input type="hidden" name="cname" value="c_name">  -->
     <div style="margin: 20px">上传文件:</div>
     <input style="margin:20px" type="file" name="file1"><br /> 
	 <input  style="margin:20px" type="button" value="提交" onclick="a()">
	 <input type="button" value="返回" onclick="b()">
	</form>
	
	
	
</div>

<script type="text/javascript">
			$(function(){
				editRow = undefined;
				datagrid = $("#dg").datagrid({
					url:'course?method=ViewCourse',
					title:"课程信息查询",
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
					idField : 'c_id',
					checkOnSelect:true,
					selected:true,
					nowarp : true,
					rownumbers:true,				
					sortName: 'c_id', //初始化表格时依据的排序 字段 必须和数据库中的字段名称相同
            		sortOrder: 'asc', //正序
					columns:[[    
					          {field:'c_id',title:'编号',checkbox:true},    
						        {field:'c_name',title:'课程名',width:150,align:'center'},    
						        {field:'c_class',title:'专业班级',width:100,align:'center'},				            
						        {field:'c_credit',title:'学分',width:80,align:'center'},	
						        {field:'c_year',title:'学年',width:180,align:'center'},
						        {field:'ab',title:'授课计划',width:180,align:'center',formatter:function(val,rec,index){
						        	return '<a href="#" onclick="editUser_1(\''+rec.c_name+'\',\''+rec.c_class+'\',\''+index+'\')">'+'授课计划'+'</a>';
						        }},
						        {field:'ac',title:'实验计划',width:180,align:'center',formatter:function(val,rec,index){
						        	return '<a href="#" onclick="editUser_2(\''+rec.c_name+'\',\''+rec.c_class+'\',\''+index+'\')">'+'实验计划'+'</a>';
						        }},
						        {field:'ad',title:'课程提纲',width:180,align:'center',formatter:function(val,rec,index){
						        	return '<a href="#" onclick="editUser_3(\''+rec.c_name+'\',\''+rec.c_class+'\',\''+index+'\')">'+'课程提纲'+'</a>';
						        }},
						        {field:'ae',title:'其他',width:180,align:'center',formatter:function(val,rec,index){
						        	return '<a href="#" onclick="editUser_4(\''+rec.c_name+'\',\''+rec.c_class+'\',\''+index+'\')">'+'其他'+'</a>';
						        }}
						        ]],	
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
				
				function formatOper(value,row,index)
				 {					
					
										
				 
				  return '<a href="load.jsp?cname='+row.c_name+'" target="_blank">文件上传</a>';
				  /*
				  return '<a href="#" onclick="editUser('+index+')">文件上传</a>';  
				  return '上传文件：<input type="file" name="file1"><input type="submit" value="提交">';
				  */
				 }
				/*
				function editUser(index){
					$('#dg').datagrid('selectRow',index);
					var row = $('#dg').datagrid('getSelected');
					alert(row);
					if(row){
						window.location = "${pageContext.request.contextPath}/upload.jsp"+row.c_name;
					}
				}
				*/
				
		});
		</script>	
		<div class="easyui-layout" fit="true" border="false">
			<div region="north" border="false" title="查询" style="height: 64px;overflow: hidden;">
				<form id="searchForm">				
					<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
						<tr  style="float: left;">
							<th>学年</th>
							<td>
								<select id="c_year" name="c_year" style="width: 200px;">
									<option value="">请选择</option>
									<option value="${year-1}-${year}学年第一学期">${year-1}-${year}学年第一学期</option>
									<option value="${year-1}-${year}学年第二学期">${year-1}-${year}学年第二学期</option>
									<option value="${year-2}-${year-1}学年第一学期">${year-2}-${year-1}学年第一学期</option>
									<option value="${year-2}-${year-1}学年第二学期">${year-2}-${year-1}学年第二学期</option>		
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
		<div id="mydialog" title="fa" modal=true class="easyui-dialog" closed=true style="width:300px">
   				<form action="${pageContext.request.contextPath}/servlet/UploadHandleServlet" enctype="multipart/form-data" method="post">				 				    				        
				        上传文件：<input type="file" name="file1"><br/>				      
				    <input style="hidden" name="cname" value="cname" id="cname">
				    <input style="hidden" name="cclass" value="cclass" id="cclass">
			        <input type="submit" value="提交">
    			</form>	
   					
   		</div>

</body>
</html>