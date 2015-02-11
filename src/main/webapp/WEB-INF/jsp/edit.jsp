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
<h1>
	<h1>Edit page</h1><br>
	<p> Login as: <sec:authentication property="name"/></p>
	
	<a href="${pageContext.request.contextPath}/logout">Logout</a><br>
	<a href="${pageContext.request.contextPath}/search">Search page </a><br>
	<a href="${pageContext.request.contextPath}/users">Users page </a><br>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="../message"> Show messages</a><br><br>
	</sec:authorize>
	
	<form:form action="${pageContext.request.contextPath}/update" method="post" modelAttribute="user" >
	<table>
		<tr>
			<td>
				<p>User Login <form:input value = "${user.userLogin}" path='userLogin'/> 
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
				<spring:bind path="userRole">
					<c:forEach items='${roles}' var='roleName'>
						<c:choose>
							<c:when test="${roleName.key eq user.userRole}">
									<input type="radio" name="userRole" value="${roleName.key}"	checked="checked">${roleName.value}
							</c:when>
							<c:otherwise>
								<input type="radio" name="userRole" value="${roleName.key}">${roleName.value}
							</c:otherwise>
						</c:choose>
				    </c:forEach>
				</spring:bind>
				<form:errors path="userRole" /></p>
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User last name <form:input value  ="${userlastName}" path="userlastName"/>
					<form:errors path="userlastName" /></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>User phone(012)345-6789 <form:input value = "${userPhone}" path="userPhone"/>
					<form:errors path="userPhone" /></p>
			</td>
		</tr>
		<tr>
			<td>
				<p>
					User mail(optional) <form:input value = "${userMail}" path="userMail"/>
					<form:errors path="userMail" />
				</p> 
			</td>
		</tr>
		<tr>
			<td>
				<p>User adress(optional) <form:textarea value = "${userAdress}" path="userAdress"/>
					<form:errors path="userAdress" />
				</p> 
				
			</td>
		</tr>
		 <tr>
			<td>
			<p>User gender 
			<spring:bind path="userGender">
					<c:forEach items='${genders}' var='genders'>
						<c:choose>
							<c:when test="${genders eq user.userGender}">
									<input type="radio" name="userGender" value="${genders}" checked="checked">${genders}
							</c:when>
							<c:otherwise>
								<input type="radio" name="userGender" value="${genders}">${genders}
							</c:otherwise>
						</c:choose>
				    </c:forEach>
				</spring:bind>
				</p>
			</td>
		</tr> 
		<tr>
			<td>
				<p>User birthday (optional) <form:input id="datepicker" value = "${userBirthday}" path="userBirthday"/>
					<form:errors path="userBirthday" />
				</p>
			</td>
		</tr>		
		<tr>
			<td>
				<p>User education 
			<spring:bind path="userEducation">
					<c:forEach items='${education}' var='education'>
						<c:choose>
							<c:when test="${education eq user.userEducation}">
									<input type="radio" name="userEducation" value="${education}" checked="checked">${education}
							</c:when>
							<c:otherwise>
								<input type="radio" name="userEducation" value="${education}">${education}
							</c:otherwise>
						</c:choose>
				    </c:forEach>
				</spring:bind>
				<form:errors path="userEducation" />
				</p>
			</td>
		</tr> 		
		<tr>
			<td>
				<p>
					User description(optional) <form:textarea value = "${userEducation}" path="userDescription"/>
					<form:errors path="userDescription" />
				</p> 
			</td>
		</tr>
	</table>
		<input type = "submit" value="Save change">
		<a href="${pageContext.request.contextPath}/users"> Cancel </a> 
		<form:hidden path="userId" value="${user.userId}" />
	</form:form>
</h1>
</body>
</html>