<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file = "taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>Login as: <sec:authentication property="name"/></p>

	<a href="${pageContext.request.contextPath}/logout"> Logout </a><br>
	<a href="${pageContext.request.contextPath}/search">Search page</a><br>
	<a href="${pageContext.request.contextPath}/users">Show all users</a><br>

<h1> ADMIN MESSAGES PAGE </h1>
<h2>LIST OF MESSAGES(user from who this message and send date)</h2>
	
</body>

</html>