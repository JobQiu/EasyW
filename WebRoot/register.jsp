<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Register</title>
    
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
  <h1> Register</h1>
  <hr>
  
  
  <form action="register" method="post">
  
  <table>
  	<tr>
  	<td>Username:</td>
  	<td>  <input type="text" name="name"/></td>
  	</tr>
  	<tr>
  	<td>Password:</td>
  	<td>  <input type="password" name="pwd2" /></td>
  	</tr>
  	<tr>
  	<td>Pwd again:</td>
  	<td>  <input type="password" name="pwd" /></td>
  	</tr>
  	
  	<tr>
  	<td>Email:</td>
  	<td>  <input type="text" name="email" /></td>
  	</tr>
  </table>
  
  	<input type="submit" value="Register" />
  </form>
  <hr>
  1. If you don't input a password, you can log in using a default password:123456.<br>
  2. You can update more information in your profile, such as your icon, your motto.

  </body>
</html>
