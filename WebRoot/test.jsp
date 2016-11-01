<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    <script type="text/javascript" src="resource/jquery-1.7.1.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
$(function(){

	$('#captchaImage').click(function() {
			$('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());
	}); 
})

</script>
  </head>
  
  <body>
  <input type="text" id="captcha" name="captcha" class="text" maxlength="10" />
              <img id="captchaImage" src="captcha.form"/>
    This is my JSP page. <br>
  </body>
</html>
