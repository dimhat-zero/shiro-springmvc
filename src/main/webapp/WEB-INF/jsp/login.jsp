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
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
  	
  	<form:form name="login" commandName="user">  
  		<div class="form-group">
  			<form:label path="username">用户名：</form:label>
  			<form:input path="username"/>
  		</div>
  		<div class="form-group">
  			<form:label path="password">密码：</form:label>
  			<form:password path="password"/>
  		</div>
  		<form:button>登陆</form:button>
	</form:form> 
	${error }显示
	<form:errors path="*" cssStyle="color:red">${error }不显示</form:errors>  
	
  </body>
</html>
