<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file = "taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Register page</h1><br>
	<p><sec:authentication property="name"/></p>
	<a href="logout"> Logout </a><br>
	<a href="search">Search page</a><br>
	<a href="users">Users page</a><br><br>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="message"> Show messages</a><br><br>
	</sec:authorize><br><br>
	<form action="insert" method="post">
		<input type = "submit" value="Register user">
		<a href="users"> Cancel </a>
	</form>

</body>
</html>