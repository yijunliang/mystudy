<%@page import="com.yjl.entity.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	User user = (User) request.getAttribute("user");
    String userId = request.getParameter("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/user/updateUser">
		<input type="hidden" value="<%=userId%>" name="id"/><br /> 
		用户名:<input type="text" value="<%=user.getUserName()%>" name="userName" /><br /> 
		密 码:<input type="text" value="<%=user.getPassword()%>" name="password" /><br /> 
		<input type="submit" value="确定">
	</form>
</body>
</html>