<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<title>Home</title>
</head>
<body>

	<h1>Search page</h1><br>
	<p> Login as: <sec:authentication property="name" /> </p>
	
	<a href="logout"> Logout </a><br>
	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		<a href="message"> Show messages</a><br><br>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_REGULAR_USER')" >
		<a href="message"> Send message</a><br><br>
	</sec:authorize>

	<a href="users"> Show all users</a>
</body>
</html>
