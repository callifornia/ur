<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file = "taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Register user</title>
	
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.10.2.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/show_message.js' />"></script>
	<link rel="icon" href="<c:url value="/resources/img/123.ico" />">
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/bootstrap.css' />" />
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/style.css' />" />
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
	<div class = "header" id = "header">
		<div class = "container">
			<div class = "row">
				<div class = "span12"> 
					<form:form name="searchForm" action="${pageContext.request.contextPath}/searchRequest" method = "POST" modelAttribute="search">
						 <div id = "users-search-main-div">
					    	<label id = "users-search-label">Search</label>
					    	<form:select path="searchType" class="span2" id = "users-search-select">
					    			<form:option  value="all" checked = "checked">All</form:option>
					    			<form:option  value="login" checked = "checked">Login</form:option>
					    			<form:option  value="phone" checked = "checked">Phone</form:option>
					    			<form:option  value="lastName" checked = "checked">Name</form:option>
					    	</form:select>
	 						<form:input path="searchRow" class="span2" id="appendedInputButton" size="16" type="text" name = "name" />  						
	  						<button class="btn btn-inverse" id = "users-search-go" type="submit">Go!</button>  						
	  						<sec:authorize access="hasRole('ROLE_ADMIN')">
		  						<a href="${pageContext.request.contextPath}/register" class = "users-search-links" >
		  							<img src="<c:url value='/resources/img/add-user-icon.png' />" onmouseover = "this.src='<c:url value='/resources/img/add-user-icon-hover.png' />'" onmouseout = "this.src = '<c:url value='/resources/img/add-user-icon.png' />'" border = "0" alt = "">
		  						</a>
							</sec:authorize>
							<a href="${pageContext.request.contextPath}/logout">
								<img src="<c:url value='/resources/img/logout-icon.png' />" onmouseover = "this.src='<c:url value='/resources/img/logout-icon-hover.png' />'" onmouseout = "this.src = '<c:url value='/resources/img/logout-icon.png' />'" border = "0" alt = "">
							</a>
						</div>				 
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class = "container">
		<!--  First section -->
		<form:form action="${pageContext.request.contextPath}/insert" method="post" modelAttribute="user">
		<div class = "row" id = "edit-user-first-top-row">
			<div class = "span5" id = "edit-user-first-row">
				<p class = "edit-user-login-text"> Login:</p>
				<form:input path="userLogin" value = "${userLogin}" />			
				<form:errors id = "edit-user-error-password" path="userLogin" />
			</div>
		</div>
		<div class = "row">
			<div class = "span5" id = "edit-user-first-row">
				<p class = "edit-user-password-text"> Password:</p>
				<form:input id ="edit-user-password-input" path="userPassword"/>
				<form:errors id = "edit-user-error-password" path="userPassword" />
			</div>
			<div class = "span5"><p class = "edit-user-role-text"> Role:</p>
				<spring:bind path="userRole">
					<c:forEach items='${roles}' var='roleName'>
						<c:choose>
							<c:when test="${roleName.key eq user.userRole}">
								<input type="radio" id = "edit-user-role-check" name="userRole" value="${roleName.key}"	checked="checked">${roleName.value}
							</c:when>
							<c:otherwise>
								<input type="radio" id = "edit-user-role-check" name="userRole" value="${roleName.key}">${roleName.value}
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</spring:bind><p>
				<form:errors path="userRole" id = "edit-user-error-role" /></p>
			</div>
			<center><img alt="" align="center" src="<c:url value='/resources/img/edit-line.png' />"></center>
		</div>
		
		<!--  Second section -->
		<div class = "row" id = "edit-user-second-row">
			<div class = "span5" id = "edit-user-first-row">
				<p class = "edit-user-fio-text"> Name:</p>
				<form:input id ="edit-user-fio-input" value  ="${userlastName}" path="userlastName"/>
				<form:errors id = "edit-user-error-password" path="userlastName" />
			</div>
			<div class = "span5">
				<p class = "edit-user-mail-text"> E-mail:</p>
				<form:input id ="edit-user-mail-input" value = "${userMail}" placeholder = "optional" path="userMail"/>
				<form:errors id = "edit-user-error-mail" path="userMail" />
			</div>		
		</div>
		<div class = "row" id = "edit-user-second-row">
			<div class = "span5" id = "edit-user-first-row">
				<p class = "edit-user-phone-text"> Phone:</p>
				<form:input id ="edit-user-phone-input" placeholder = "0123456789" value = "${userPhone}" path="userPhone"/>
				<form:errors id = "edit-user-error-password" path="userPhone" />
			</div>
			<div class = "span5">
				<p class = "edit-user-gender-text"> Gender:</p>
					<spring:bind path="userGender">
						<c:forEach items='${genders}' var='genders'>
							<c:choose>
								<c:when test="${genders eq user.userGender}">
									<input type="radio" id = "edit-user-role-check1" name="userGender" value="${genders}" checked="checked">${genders}
								</c:when>
								<c:otherwise>
									<input type="radio" id = "edit-user-role-check" name="userGender" value="${genders}">${genders}
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</spring:bind><p>
					<form:errors path="userGender" id = "edit-user-error-role" /></p>
			</div>	
			<center><img alt="" align="center" src="<c:url value='/resources/img/edit-line.png' />"></center>	
		</div>
		
		<!--  Thrid section -->
		<div class = "row" id = "edit-user-second-row">
			<div class = "span5" id = "edit-user-first-row">
				<p class = "edit-user-birthday-text"> Birthday:</p>
				<form:input id="datepicker" class = "edit-user-birthday-input" placeholder = "optional" value = "${userBirthday}" path="userBirthday"/>
			</div>
			<div class = "span6">
				<p class = "edit-user-education-text"> Education:</p>
				<spring:bind path="userEducation">
					<c:forEach items='${education}' var='education'>
						<c:choose>
							<c:when test="${education eq user.userEducation}">
								<input type="radio" id = "edit-user-education" name="userEducation" value="${education}" checked="checked">${education}
							</c:when>
							<c:otherwise>
								<input type="radio" id = "edit-user-education-other" name="userEducation" value="${education}">${education}
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</spring:bind><p>
				<form:errors path="userEducation" id = "edit-user-error-education" /></p>
			</div>
		</div>
		<div class = "row" id = "edit-user-second-row">
			<div class = "span5" id = "edit-user-first-row">
				<p class = "edit-user-adress-text">Address:</p>
				<form:textarea rows="6"  value = "${userAdress}" path="userAdress" placeholder = "optional"/>	
				<form:errors id = "edit-user-error-password" path="userAdress" />
			</div>
			<div class = "span5">
				<p class = "edit-user-description-text"> Descrition:</p>
				<form:textarea rows="6" placeholder = "optional" class = "edit-user-description-input" value = "${userDescription}" path="userDescription"/>	
				<form:errors id = "edit-user-error-password" path="userDescription" />
			</div>
		</div>
		<div class = "row" id = "edit-button-form">
			<div class = "span12" align="center">
				<button type="submit" id = "edit-form-save-button"  class="btn  btn-primary">Save</button>
				<a href="${pageContext.request.contextPath}/users" class = "btn">Cancel</a>
			</div>
		</div>
		</form:form>
	</div>
	
	<!-- footer -->
		<div class = "container">			
			<font class = "navbar-text">©2015 CRUD with Spring MVC</font>	
		</div>
</body>
</html>