<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body style="height: 100%; border: 1px; background-color: #95B8E7;">
	
	<table id="dg" class="easyui-datagrid" title="录入成绩" style="width:700px;height:250px"
			data-options="toolbar:toolbar,
						  rownumbers:true,
						  singleSelect:true,
						  url:'practice?method=ViewData',
						  method:'get',
						  fit:true,
						  fitColumns:true,
						  checkOnSelect:true,
						  selectOnCheck:true,
						  onClickCell: onClickCell
						
						  ">
		<thead>
		<c:forEach items="${taskList }" var="task">
			<tr>
			<th data-options="field:'g_id',width:80,align:'center',checkbox:true" rowspan="2">编号</th>
				<th data-options="field:'t_id',width:100,align:'center',hidden:true" rowspan="2">TID</th>
				<th data-options="field:'s_no',width:100,align:'center'" rowspan="2">学号</th>
				<th data-options="field:'s_name',width:100,align:'center'" rowspan="2">姓名</th>
				<th data-options="field:'s_class',width:100,align:'center'" rowspan="2">班级</th>
				<th colspan="${task.tk_time+1}">${task.tk_type}</th>
				<th data-options="field:'g_remark',width:120,align:'center',editor:'text'" rowspan="2">备注</th>
			</tr>
			<tr>
				<c:if test="${task.tk_time==1||task.tk_time==2||task.tk_time==3||task.tk_time==4||task.tk_time==5 }">
					<th data-options="field:'g_1',width:100,align:'center',editor:'numberbox'">内容1(${avg})</th>
				</c:if>
				<c:if test="${task.tk_time==2||task.tk_time==3||task.tk_time==4||task.tk_time==5 }">
					<th data-options="field:'g_2',width:100,align:'center',editor:'numberbox'">内容2(${avg})</th>
				</c:if>
				<c:if test="${task.tk_time==3||task.tk_time==4||task.tk_time==5 }">
					<th data-options="field:'g_3',width:100,align:'center',editor:'numberbox'">内容3(${avg})</th>
				</c:if>
				<c:if test="${task.tk_time==4||task.tk_time==5 }">
					<th data-options="field:'g_4',width:100,align:'center',editor:'numberbox'">内容4(${avg})</th>
				</c:if>
				<c:if test="${task.tk_time==5 }">
					<th data-options="field:'g_5',width:100,align:'center',editor:'numberbox'">内容5(${avg})</th>
				</c:if>
				<th data-options="field:'g_total',width:100,align:'center'">总成绩</th>
			</tr>
			
			</c:forEach>
		</thead>
	</table>
	
	
	<script type="text/javascript"> 
		var toolbar = [{
			text:' 取消编辑',
			iconCls:'icon-redo',
			handler:function(){														
				$('#dg').datagrid('unselectAll');
				$('#dg').datagrid('rejectChanges');
				editRow = undefined;
			}
		},'-',{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				$('#dg').datagrid('endEdit',editIndex);
				var updated = $('#dg').datagrid('getChanges', 'updated');
				 if (updated.length > 0) {					
					 $.messager.confirm('请确认','您确定要提交录入的成绩？',function(b){
							if (b) {					 
					 		updateDatagrid();
							}
					 })			      
			 }
		}
		}];
	var editIndex = undefined; 
	$.extend($.fn.datagrid.methods, {
		editCell: function(jq,param){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				
				var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field){
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);

				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					
					col.editor = col.editor1;
				}
			});
		}
	});
	
	 
	function endEditing() {//该方法用于关闭上一个焦点的editing状态  
	    if (editIndex == undefined) {  
	        return true  
	    }  
	    if ($('#dg').datagrid('validateRow', editIndex)) {  
	        $('#dg').datagrid('endEdit', editIndex);  
	        editIndex = undefined;  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	//点击单元格事件：  
	function onClickCell(index, field){
			if (endEditing()){
				$('#dg').datagrid('selectRow', index)
						.datagrid('editCell', {index:index,field:field});
				editIndex = index;
			}
		}
	
	//单元格失去焦点执行的方法  
	
	function onAfterEdit(index, row, changes) {  
	    var inserted = $('#dg').datagrid('getChanges', 'updated'); 
	    var url = '';
	    if (inserted.length > 0) { 
	    	alert();
	    	updateDatagrid();
	    }else{
	    	editRow = undefined;  
	        $('#dg').datagrid('unselectAll');  
	        return;  
	    }
	   
	      
	}  
	
	//提交数据  
	function updateDatagrid()
	{
    var rows = $('#dg').datagrid('getRows');
    var entities = '';

// 循环 datagrid 中现有的数据，并且逐行复制给Entities ，并且转换成json格式
// 在后台反序列话成对象的对象集合。
    for(i = 0;i < rows.length;i++)
    {
       entities = entities  + JSON.stringify(rows[i]);  
    }
    $.ajax({
             url: 'practice?method=AddGrade',
             type: 'get',
             async: true,
             dataType: 'json',
             data: {'entities': entities},
             success: function (json) {
                 if(json&&json.success){               	 
						$.messager.show({
							msg : json.msg,
							title : '成功'
						});
                    }else{                   	
						$.messager.alert('错误',json.msg,'error')
                    }
                 $('#dg').datagrid('reload');
             }
           });
    
    }
		
	
	
	</script>
</body>
</html>