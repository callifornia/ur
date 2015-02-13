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
	
	<a href="${pageContext.request.contextPath}/logout"> Logout </a><br>
	<sec:authorize access="hasRole('ROLE_ADMIN')" >
		<a href="${pageContext.request.contextPath}/message"> Show messages</a><br><br>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_REGULAR_USER')" >
		<a href="${pageContext.request.contextPath}/message"> Send message</a><br><br>
	</sec:authorize>

	<form:form name="searchForm" action="${pageContext.request.contextPath}/searchRequest" method = "POST" modelAttribute="search">
		<table>
			<tr>
				<td>Search: </td>
				<td>
					<form:input name = "name" path="searchRow"/> 
				</td>
				<script>
  					document.getElementsByName('name')[0].focus();
				</script>
			</tr>
			<tr>
				<td>
					<form:radiobutton path="searchType" value="login" checked = "checked"  />login<br>
					<form:radiobutton path="searchType" value="phone"/>phone <br>
					<form:radiobutton path="searchType" value="lastName" />last Name<br>
					
				</td>
			
			</tr>
			
			<tr>
				<td>
					<input type = "submit" name = "submit" value = "submit">
				</td>
			</tr>						
		</table>
	</form:form>
</body>
</html>
