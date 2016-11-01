<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>

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

<script type="text/javascript" src="resource/jquery-1.7.1.js"></script>
<script type="text/javascript">
$(function(){

	$('#captchaImage').click(function() {
			$('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());
	}); 
	
	$('#name').focusout(function(){
		$.ajax({
			url:"<%=basePath%>checkUsernameExists",
			type:"post",
			data:{"username":$("#name").val()},
			success:function(data){	
				$('#nameCheck').html(data);
			}
		})
	})
	
	$('#email').focusout(function(){
		$.ajax({
			url:"<%=basePath%>checkEmail",
			type:"post",
			data:{"email":$("#email").val()},
			success:function(data){	
				$('#emailCheck').html(data);
			}
		})
	})
})

</script>
  </head>
  
  <body>
  <h1> Register</h1>
  <hr>
  
  
  <form action="register" method="post">
  
  <table>
  	<tr>
  	<td>Username:</td>
  	<td>  <input type="text" id="name" name="name"/></td>
  	<td><p id="nameCheck"></p></td>
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
  	<td>  <input type="text" id="email" name="email" /></td>
  	<td><p id="emailCheck"></p></td>
  	</tr>
  	<tr>
  	<td>Verification code:</td>
  		<td><input type="text" id="captcha" name="captcha" class="text" maxlength="10" /></td>
  	
  		<td> <img id="captchaImage" src="captcha.form"/> Click me</td>
  	</tr>
  </table>
  
  	<input type="submit" value="Register" />
  </form>
  <hr>
  1. If you don't input a password, you can log in using a default password:123456.<br>
  2. You can update more information in your profile, such as your icon, your motto.

  </body>
</html>
