<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 divansitional//EN" "http://www.w3.org/div/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
<style type="text/css">
.error{
color:red;
}

</style>
<script type="text/javascript">
	$(function() {
	
		<c:if test="${flag==1}">
		$.messager.show({
			title:'提示',
			msg:'添加人员信息成功',						
		});
		</c:if>	
		<c:if test="${flag==0}">
			$.messager.alert('错误','添加人员信息失败','error')							
		</c:if>	
		<c:if test="${flag==2}">
			$.messager.alert('错误','文件必须为excel类型','error')							
		</c:if>	
			<c:if test="${flag==3}">
			$.messager.alert('错误','excel表头不对应','error')							
		</c:if>			
		//页面加载完毕后开始执行的事件
		$("#link_type").change(function() {

			if ($(this).val() == "1") {
				$("form #teacher").show();
				$("form #student").hide();
			}
			if ($(this).val() == "2") {
				$("form #teacher").hide();
				$("form #student").show();
			}
		});
		
		//进行表单的校验
		$("#myform").validate({
			rules:{
				//首先校验老师的数据
				"t_no":{
					"required":true
				},
		       "t_name":{
		    	  "required":true
		       },
		       "t_password":{
		    	   "required":true 
		       },
		       "t_dept":{
		    	   "required":true 
		       },
		       "t_office":{
		    	   "required":true 
		       },
		       "t_titles":{
		    	   "required":true 
		       },
		     //现在校验学生的数据  
		       "s_no":{
					"required":true
				},
		       "s_name":{
		    	  "required":true
		       },
		       "s_password":{
		    	   "required":true 
		       },
		       "s_dept":{
		    	   "required":true 
		       },
		       "s_class":{
		    	   "required":true 
		       }, 
			},
			messages:{
				//首先校验老师的数据
				"t_no":{
					"required":"工号不能为空"
				   },
				 "t_name":{
			    	  "required":"姓名不能为空"
			       },
				 "t_password":{
			    	   "required":"密码不能为空" 
			       },
			       "t_dept":{
			    	   "required":"请选择的院系" 
			       },
			       "t_office":{
			    	   "required":"教研室不能为空" 
			       },
			       "t_titles":{
			    	   "required":"职称不能为空" 
			       },
			       ////现在校验学生的数据  
			       "s_no":{
						"required":"学号不能为空"
					   },
					 "s_name":{
				    	  "required":"姓名不能为空"
				       },
					 "s_password":{
				    	   "required":"密码不能为空" 
				       },
				       "s_dept":{
				    	   "required":"请选择的院系" 
				       },
				       "s_class":{
				    	   "required":"班级不能为空" 
				       }
			}
		});
	});
</script>



</head>
<body style="height: 130%; border: 1px; ">

	<div style="width: 500px;margin: auto;">
	<h4>人员信息录入</h4>
		<form id="myform" action="${pageContext.request.contextPath }/person?method=AddPersonInformation" method="post">
				<div style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">类&nbsp;型:</div>
					<div style="float:left"><select id="link_type" name="link_type"
						style="width: 255px; height:23px">
							<option value="1">教师</option>
							<option value="2">学生</option>
					</select></div>
				</div>
				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">工&nbsp;号:</div>
					<div style="float:left">
						<input type="text" name="t_no" id="t_no"
						style="width: 250px;" />
					</div>
				</div>
				<div id="student" style="display: none;clear: both;" >
					<div style="width:100px;height: 2.4em;float: left;">学&nbsp;号:</div>
					<div style="float:left">
						<input type="text" name="s_no" id="s_no"
						style="width: 250px;" />
					</div>
				</div>

				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">姓&nbsp;名:</div>
					<div style="float:left"><input type="text" name="t_name" id="t_name"
						style="width: 250px;" /></div>
				</div>
				<div id="student" style="display: none;clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">姓&nbsp;名:</div>
					<div style="float:left"><input type="text" name="s_name" id="s_name"
						style="width: 250px;" /></div>
				</div>

   				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">密&nbsp;码:</div>
					<div style="float:left"><input type="text" name="t_password" id="t_password"
						style="width: 250px;" /></div>
				</div>
				<div id="student" style="display: none;clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">密&nbsp;码:</div>
					<div style="float:left"><input type="text" name="s_password" id="s_password"
						style="width: 250px;" /></div>
				</div>

				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">院&nbsp;系:</div>
					<div style="float:left"><select id="t_dept" name="t_dept" style="width: 255px;  height: 23px">
							<option value="">-请选择-</option>
							<option value="1">电气学院</option>							
					</select></div>
				</div>
				<div id="student" style="display: none;clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">院&nbsp;系:</div>
					<div style="float:left"><select id="s_dept" name="s_dept" style="width: 255px;  height: 23px">
							<option value="">-请选择-</option>
							<option value="1">电气学院</option>						
					</select></div>
				</div>


				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">教研室:</div>
					<div style="float:left"><input type="text" name="t_office"
						id="t_office" style="width: 250px;" /></div>
				</div>
				<div id="student" style="display: none;clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">班&nbsp;级:</div>
					<div style="float:left"><input type="text" name="s_class" id="s_class"
						style="width: 250px;" /></div>
				</div>

				<div id="teacher" style="clear: both;">
					<div style="width:100px;height: 2.4em;float: left;">职&nbsp;称:</div>
					<div style="float:left"><input type="text" name="t_titles"
						id="t_titles" style="width: 250px;" />
					</div>
				</div>
				<div style="clear: both;">
					<button type="submit" value="提交" style="width: 358px;height: 23px;">提交</button>							
				</div>
		</form>
		
		
		<br />
		<hr style="width: 70%;margin-left: 0px;" />
		
		
		<!-- 开始进行数据批量导入 -->
	   <h4>批量导入</h4>
			
			<div style="width:100px;height: 2.4em;float: left;">上传类型:</div>
			<div>
			        <td>
			        <select id="type" name="" style="width: 255px;  height: 23px">
			        <option value="1">教师</option>
			        <option value="2">学生</option>
			        </select>
			        </td>
			</div>
			<!-- <from enctype="multipart/form-data" method="post" id="a">
			        
					<td><input type="button" value="批量导入" /> <input type="file" /></td>
					<td><input type="button" value="提交" onclick="a()"></td>
					
			</from>	 -->	
			
			<form enctype="multipart/form-data" method="post" id="a">
				<div style="clear: both;">
				<div style="width:100px;height: 2.4em;float: left;">上传文件:</div>
				<div style="width:120px;height: 2.4em;float: left;">
		 			<input type="file" name="file1" style="clear: both;"><br />
		 			  </div>
		 		<div style="clear: both;">	
		            <input type="button" value="提交" onclick="a()" style="width: 358px;height: 23px;">
		           </div>
		        </div>
		      
	            </form>
	            
				
		
		<script type="text/javascript">
		function a(){
			
		//	  alert();
			  var select = document.getElementById("type");
			  var value = select.value;
	
		//	  alert(value==1)
			  //老师
			  if(value==1){
				  
		//	  alert("老师");
				  
		      document.getElementById("a").action = "${pageContext.request.contextPath}/person?method=teacher_upload";
			  document.getElementById("a").submit();
			  
			  }
			  
			  //学生
             if(value==2){
            	 
        //     alert("学生");
            	 
             document.getElementById("a").action = "${pageContext.request.contextPath}/person?method=student_upload";
   			 document.getElementById("a").submit();  
   			 
			  }
			  
			
		}
		</script>
		
	</div>
</body>
</html>