<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>没有权限</title>
    <style>.error{color:red;}</style>
</head>
<body>
<shiro:principal/>没有授权。
<div class="error">您没有权限[${exception.message}]</div>
</body>
</html>