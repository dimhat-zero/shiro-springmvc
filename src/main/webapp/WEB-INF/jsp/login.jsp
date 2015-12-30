<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
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
  	
  	<form:form name="login" commandName="user" method="POST" >  
  		<div class="form-group">
  			<form:label path="username">用户名：</form:label>
  			<form:input path="username"/>
  			<form:errors path="username" cssStyle="color:red"></form:errors>  
  		</div>
  		<div class="form-group">
  			<form:label path="password">密码：</form:label>
  			<form:password path="password"/>
  			<form:errors path="password" cssStyle="color:red"></form:errors>  
  		</div>
  		<form:button>登陆</form:button>
	</form:form> 
	${error }
	
  </body>
</html>
