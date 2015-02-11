<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file = "taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
  	$('.message').delay(3000).fadeOut(800);
});
</script>
</head>
<body>

<p>Login as: <sec:authentication property="name"/></p>

	<a href="${pageContext.request.contextPath}/logout"> Logout </a><br>
	<a href="${pageContext.request.contextPath}/search">Search page</a><br>
	<a href="${pageContext.request.contextPath}/users">Show all users</a><br>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/register">Register page</a><br><br>
</sec:authorize>

	<h1> USER MESSAGES PAGE </h1>
	<h2> LIST OF MY MESSAGES</h2>
	<h2> Date, Read or not</h2>
	<c:if test="${not empty message}">
		 <font class = "message" color="red">${message}</font>
	</c:if>
	

	<a href="${pageContext.request.contextPath}/message/new_message">New message</a><br><br>
</body>

</html>