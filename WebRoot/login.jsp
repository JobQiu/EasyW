<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
    
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
  <h1>Login</h1>
  <hr>
  <form action="<%= basePath %>login"  method="post">
  	Username:<input type="text" name="name" /><br/><br/>
  	Password:<input type="password" name="pwd"/><br/><br/>
  	<input type="submit" value="Submit"/> &nbsp &nbsp Remember me for 7 days<input type="checkbox" name="remember" /><br/><br/>
  	&nbsp &nbsp 	<a href="<%= basePath %>reset.jsp" >Forget password</a>&nbsp &nbsp &nbsp &nbsp
  	<a href="<%= basePath %>register.jsp" >Register</a><br/><br/>
  </form>
  <hr>
  Powered by JobQ, for more information, read the <a href="intro.jsp">Intro</a>.
  </body>
</html>
