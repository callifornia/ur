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
<body>
<h1>
	<h1>User page</h1><br>
	<p> Login as: <sec:authentication property="name"/></p>
	
	<a href="${pageContext.request.contextPath}/logout">Logout</a><br>
	<a href="${pageContext.request.contextPath}/search">Search page </a><br>
	<a href="${pageContext.request.contextPath}/users">Users page </a><br>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="../message"> Show messages</a><br><br>
	</sec:authorize>
	<table>
		<tr>
			<td>
				<p>User Login: ${user.userLogin }</p>
			</td>
		</tr>
		<tr>
			<td>
				<c:choose>
				<c:when test="${user.userRole == 'ROLE_ADMIN' }">
					<p>User role: Admin</p>
				</c:when>
				<c:when test="${user.userRole == 'ROLE_REGULAR_USER' }">
					<p>User role: User</p>
				</c:when>
				<c:otherwise>
					<p>User role: unknown</p>
					</c:otherwise>
				</c:choose>				
			</td>
		</tr>
		<tr>
			<td>
				<p>User Last Name: ${user.userlastName}</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User phone: ${user.userPhone}</p>
			</td>
		</tr>
		<c:choose>
				<c:when test="${not empty user.userMail}">
					<tr>
						<td>
							<p>User mail: ${user.userMail}</p>
						</td>
					</tr>
				</c:when>
		</c:choose>
		<c:choose>
				<c:when test="${not empty user.userAdress}">
					<tr>
						<td>
							<p>User adress: ${user.userAdress}</p>
						</td>
					</tr>
				</c:when>
		</c:choose>	
		<tr>
			<td>
				<p>User gender: ${user.userGender}</p>
			</td>
		</tr>
		<c:choose>
				<c:when test="${not empty user.userBirthday}">
					<tr>
						<td>
							<p>User birthday: ${user.userBirthday}</p>
						</td>
					</tr>
				</c:when>
		</c:choose>	
		<tr>
			<td>
				<p>User education: ${user.userEducation}</p>
			</td>
		</tr>
		<c:choose>
				<c:when test="${not empty user.userDescription}">
					<tr>
						<td>
							<p>User description: ${user.userDescription}</p>
						</td>
					</tr>
				</c:when>
		</c:choose>	
	</table>
</body>
</body>
</html>