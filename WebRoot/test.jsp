<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
    <form>
    <%for(int i=0;i<10;i++) {%>
    <input type="checkbox" id="<%=i%>"/> 买这个<br/>
    <%} %>
   
   
    </form>
     --------<br>
      learn c tag
    <%
  //定义一个用户数组
  String[] zhangs={"zhang1",      "zhang2",      "zhang3",      "zhang4"};
  request.setAttribute("zhangsan",zhangs);
 %>
    
    <table border=1 width=400>
  <tr align=center >
   <td>内容</td>
   <td>索引值</td>
   <td>共访问过</td>
   <td>是否为第一个成员</td>
   <td>是否为最后一个成员</td>
  </tr>
  <c:forEach items="${zhangsan}" var="z" varStatus="s">
   <tr align=center>
    <td><c:out value="${z}"/></td>
    <td><c:out value="${s.index}"/></td>
    <td><c:out value="${s.count}"/></td>
    <td><c:out value="${s.first}"/></td>
    <td><c:out value="${s.last}"/></td>  
   </tr>
  </c:forEach>
 </table>
 <hr/> 
    
  </body>
</html>
