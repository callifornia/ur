<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file = "taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null }">
	<div>
		Login failed. <br>
		Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} 
	</div>
</c:if>

<h1>Custom login page</h1>
	<form name = "loginForm" action="<c:url value = 'j_spring_security_check' />" method = "POST">
		<table>
			<tr>
				<td>User: </td>
				<td><input type = "text" name = "j_username"></td>
				<script>
  					document.getElementsByName('j_username')[0].focus();
				</script>
			</tr>
			<tr>
				<td>Password: </td>
				<td><input type = "text" name = "j_password"></td>
			</tr>
			<tr>
				<td></td>
				<td>
					Remember me: <input type = "checkbox" name = "remember-me"><br>
					<font><a href="${pageContext.request.contextPath}/register">Register</font>
				</td>
			</tr>
			<tr>
				<td>
					<input type = "submit" name = "submit" value = "submit">
				</td>
			</tr>						
		</table>
	</form>
</body>
</html>