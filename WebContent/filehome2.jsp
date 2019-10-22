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
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="js/EasyUI/themes/icon.css" />
<link rel="stylesheet" href="js/EasyUI/themes/default/easyui.css" />
<script type="text/javascript" src="js/EasyUI/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
		$(function(){
			<c:if test="${flag==1}">
			$.messager.show({
				title:'提示',
				msg:'上传文件成功',						
			});
			</c:if>	
			<c:if test="${flag==0}">
			$.messager.alert('错误','上传文件失败','error')							
			</c:if>	
			<c:if test="${flag==2}">
				$.messager.alert('错误','单个文件超出最大上传值！！！','error')							
			</c:if>	
			<c:if test="${flag==3}">
				$.messager.alert('错误','上传文件的大小超出限制的最大！！！','error')							
			</c:if>		
			<c:if test="${flag==4}">
			$.messager.show({
				title:'提示',
				msg:'删除文件成功',						
			});
			</c:if>
		});
		function showcontent(url) {
			$("#pageurl").attr("src", url);
		}
		function r() {
			window.location.href = "${pageContext.request.contextPath}/ViewCourseInformation.jsp";
		}
		
		function del(a) {
			$.messager.confirm('请确认','您确定要删除当前文件？',function(r){
				if (r) {
					//alert(a);
					window.location.href = "${pageContext.request.contextPath}/ListFileServlet?method=del&filename="+a;
				}
			});
		}
		
	
</script>
</head>
<body id="bylayout" class="easyui-layout">		

		<div data-options="region:'west',title:'文件上传',split:false,border:false,hideCollapsedContent:false" href="fileleft2.jsp" style="width:250px;">

		</div>	
	
		<div data-options="region:'center',title:'上传资料列表',border:false" style="padding:0;background:#fff;">
			<!-- 
			<iframe id="pageurl" style="width:99%;height:99%" scrolling="no"></ifranme>
			 -->
			 <!-- cellspacing="0" -->
			<table>
		    	<tr>
		    		<td style="width: 350px;font-size: 18px;background: #efe8e8;" align="center">资料名称</td>
		    		<td style="width: 350px;font-size: 18px;background: #efe8e8;" align="center">大小</td>
		    		<td style="width: 300px;font-size: 18px;background: #efe8e8;" align="center">操作</td>
		    	</tr>	   
		    	<c:forEach var="me" items="${fileNameMap}">
			        <c:url value="/servlet/DownLoadServlet" var="downurl">
			            <c:param name="filename" value="${me.name}"></c:param>
			        </c:url>
			        
			        <tr>
				        <td width="400" style="text-align: center;">
				        	<a href="${downurl}">${me.realName}</a>
				        </td>
				        <td style="text-align: center;">
			        		${me.length}
			        	</td>
			        	<td style="text-align: center;">
			        		<a href="#" id="del" onclick="del('${me.name}')">删除</a>
			        	</td>
			        </tr>
		    	</c:forEach>
		    </table> 
		</div>
						
	</body>
</html>