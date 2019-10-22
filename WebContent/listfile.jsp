<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>下载文件显示页面</title>
  </head>
  
  <body>
      <!-- 遍历Map集合 -->
    <table>
    	<tr>
    		<td style="width: 350px;font-size: 18px;background: #efe8e8;" align="center">资料名称</td>
		    <td style="width: 350px;font-size: 18px;background: #efe8e8;" align="center">大小</td>
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
        </tr>
    </c:forEach>
     </table>
  </body>
</html>