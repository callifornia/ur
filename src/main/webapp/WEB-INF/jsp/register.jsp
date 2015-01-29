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

	<form:form action="insert" method="post" modelAttribute="user" >
	<table>
		<tr>
			<td>
				<p>User Login <form:input path='userLogin'/></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User User Password <form:input path="userPassword"/></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User Role  
					<form:radiobuttons path="userRole" items="${roles}"/>
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User last name <form:input path="userlastName"/></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User phone <form:input path="userPhone"/></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User mail <form:input path="userMail"/></p> 
			</td>
		</tr>
		<tr>
			<td>
				<p>User adress <form:textarea path="userAdress"/></p> 
			</td>
		</tr>
		<tr>
			<td>
				<p>User gender <form:select path="userGender" > 
									<form:options items="${genders}"/>
							   </form:select> 
					
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User birthday <form:input path="userBirthday"/></p>
			</td>
		</tr>		
		<tr>
			<td>
				<p>User education 
					<form:select path="userEducation">
						<form:options items="${education}"/>
					</form:select>
				</p>
			</td>
		</tr>		
		<tr>
			<td>
				<p>User description <form:textarea path="userDescription"/></p> 
			</td>
		</tr>
	</table>
		<input type = "submit" value="Register user">
		<a href="users"> Cancel </a>
	</form:form>

</body>
</html>