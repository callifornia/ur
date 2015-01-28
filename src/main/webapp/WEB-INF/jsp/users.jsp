<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Users page</h1>
	<p> Login as: <sec:authentication property="name"/></p><br>
	<c:if test="${not empty success}"> <p>${success}</p><br><br></c:if>
	<a href="logout"> Logout </a><br>
	<sec:authorize access="hasRole('ROLE_REGULAR_USER')" >
		<a href="message"> Send message</a><br><br>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		<a href="message"> Show all message</a><br><br>
		<a href="edit/12"> Edit page</a><br>
		<a href="delete/12"> Delete user(see logger)</a><br>
		<a href="register"> Register user(see logger)</a><br><br>
	</sec:authorize>
	<a href="search"> Search page</a><br>
	
</body>
</html>