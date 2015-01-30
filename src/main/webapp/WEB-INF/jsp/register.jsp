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
				<p>User Login <form:input path='userLogin'/> 
				<form:errors path="userLogin" /></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User Password <form:input path="userPassword"/>
				<form:errors path="userPassword" /></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User Role  
					<form:radiobuttons path="userRole" items="${roles}"/>
					<form:errors path="userRole" /></p>
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User last name <form:input path="userlastName"/>
					<form:errors path="userlastName" /></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User phone(012)345-6789 <form:input path="userPhone"/>
					<form:errors path="userPhone" /></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>
					User mail(optional) <form:input path="userMail"/>
					<form:errors path="userMail" />
				</p> 
			</td>
		</tr>
		<tr>
			<td>
				<p>User adress(optional) <form:textarea path="userAdress"/>
					<form:errors path="userAdress" />
				</p> 
			</td>
		</tr>
		<tr>
			<td>
				<p>User gender <form:select path="userGender" > 
									<form:options items="${genders}"/>
									<form:option value=""></form:option>
							   </form:select> 
							   <form:errors path="userGender" />
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User birthday (optional) <form:input path="userBirthday"/></p>
			</td>
		</tr>		
		<tr>
			<td>
				<p>User education 
					<form:select path="userEducation">
						<form:options items="${education}"/>
					</form:select>
					<form:errors path="userEducation" />
				</p>
			</td>
		</tr>		
		<tr>
			<td>
				<p>
					User description(optional) <form:textarea path="userDescription"/>
					<form:errors path="userDescription" />
				</p> 
			</td>
		</tr>
	</table>
		<input type = "submit" value="Register user">
		<a href="users"> Cancel </a>
	</form:form>

</body>
</html>