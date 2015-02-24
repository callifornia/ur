<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file = "taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
	
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.10.2.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.js' />"></script>
  	<link href="<c:url value='/resources/css/jquery-ui.css' />" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript">
 		 $(function() {
    		$( "#datepicker" ).datepicker({
    			dateFormat: "dd-mm-yy"
    		});
  		});
  </script>

</head>
<body>
<h1>REGISTRED FROM LOGIN PAGE</h1><br>

	<form:form action="${pageContext.request.contextPath}/inserted" method="post" modelAttribute="user" >
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
	    <sec:authorize access="hasRole('ROLE_ADMIN')">
			<tr>
				<td>
					<p>
						User Role <form:radiobuttons path="userRole" items="${roles}"/>
						<form:errors path="userRole" />
					</p>
				</td>
			</tr>
		</sec:authorize>					
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
									<form:option value=""></form:option>
									<form:options items="${genders}"/>
							   </form:select> 
							   <form:errors path="userGender" />
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User birthday (optional) <form:input  id="datepicker" path="userBirthday"/></p>
				
			</td>
		</tr>		
		<tr>
			<td>
				<p>User education 
					<form:select path="userEducation">
						<form:option value=""></form:option>
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
		<a href="${pageContext.request.contextPath}/users"> Cancel </a>
	</form:form>

</body>
</html>