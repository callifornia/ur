<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp"%>
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
	<h1>Users page</h1>
	<p> Login as: <sec:authentication property="name"/></p><br>
	<c:if test="${not empty success}"> <p class = "message">${success}</p><br><br></c:if>
	<a href="${pageContext.request.contextPath}/logout"> Logout </a><br>
	<sec:authorize access="hasRole('ROLE_REGULAR_USER')" >
		<a href="${pageContext.request.contextPath}/message"> Send message</a><br><br>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="${pageContext.request.contextPath}/message"> Show messages</a><br><br>
	</sec:authorize>
	<a href="${pageContext.request.contextPath}/search"> Search page</a><br>
	<a href="${pageContext.request.contextPath}/register">Register user</a><br><br>
	
	<table border = 1 align="center">
		<tr style = "font-weight: bold; font-size: 22px;" align="center">
			<td>User login</td>
			<td>User role</td>
			<td>User lastName</td>
			<td>User phone</td>
			<td>User Gender</td>
			<td>User Education</td>
			<sec:authorize access="hasRole('ROLE_ADMIN')"> 
				<td>User Action</td>
			</sec:authorize>
		</tr>
		<c:forEach var="user" items="${user}">
		<tr>
			<td><a href="${pageContext.request.contextPath}/user/${user.userId}">${user.userLogin}</a></td>
			<c:choose>
				<c:when test="${user.userRole == 'ROLE_ADMIN' }">
					<td>admin</td>
				</c:when>
				<c:when test="${user.userRole == 'ROLE_REGULAR_USER' }">
					<td>user</td>
				</c:when>
				<c:otherwise>
					<td>unknown role</td>
				</c:otherwise>
			</c:choose>
			<td>${user.userlastName}</td>
			<td>${user.userPhone }</td>
			<td>${user.userGender}</td>
			<td>${user.userEducation}</td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<td> 
				<a href="${pageContext.request.contextPath}/edit/${user.userId}">Edit </a>
				<a href="${pageContext.request.contextPath}/delete/${user.userId}">Delete </a>
			</td>
			</sec:authorize>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>