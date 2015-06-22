<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>User login</title>
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
    		$( "#main-registration-birthday" ).datepicker({
    			dateFormat: "dd-mm-yy"
    		});
  		});
  	</script>
</head>
<body>

<div class = "header" id = "header">
	<div class = "container">
		<div class = "row">
			<div class = "span7"> <h2>Just do it </h2>
				<div style = "height: 40px" class = "row">
					<div class = "span6">					
						<c:choose>
							<c:when test="${not empty success}">
								<div id = "show" class="alert alert-success">
									${success}	
								</div>
							</c:when>	
							<c:when test="${not empty error}">
								<div id = "show" class="alert alert-error">
									${error}	
								</div>
							</c:when>	
							<c:otherwise>
								<p style="color: #b4c2e0">by default: admin_0/admin_0, user_101/user_101</p>
							</c:otherwise>						
						</c:choose>	
					</div>
				</div>
			</div>
			<div class = "span5"> 				
				<div class = "row">	
					<form name = "loginForm" action="<c:url value = 'j_spring_security_check' />" method = "POST">
					<fieldset>				 	
						<c:choose>
							<c:when test="${SPRING_SECURITY_LAST_EXCEPTION != null }">
								<p>Invalid user name or password</p>
							</c:when>								
							<c:otherwise>
								<pl>&nbsp </p>
							</c:otherwise>
						</c:choose>						
					    <input type="text" id = "input-login" class="input-small" placeholder="Enter login" name = "j_username" >
					    <input type="password" id = "input-password" class="input-small" placeholder="Enter password" name = "j_password">
					    <button type="submit" id = "input-login-button" class="btn btn-small">Sig in</button>					    
					    <label class="checkbox">
						    <input type="checkbox" name = "remember-me"> Remember
						</label>
						</fieldset>
						
					</form>	
				</div>
			</div>
		</div>
	</div>
</div>

<div class = "container">
	<div class = "row" id = "main-conteiner">
		<div class = "span7" >
		<img src="<c:url value='/resources/img/main-pic.png' />" id = "main-pic" >
		</div>
		<div class = "span5" >
			<p class = "main-registration-text">Registration</p>	
			<form:form class="form" action="${pageContext.request.contextPath}/inserted" method="post" modelAttribute="user" >		
				<div class = "row">
					<div class = row>
						<div class = "span2">
							<form:errors cssClass="main-error-message" path="userLogin" />
						</div>
						<div class = "span3">
							<form:errors cssClass="main-error-message-password" path="userPassword" />
						</div>
					</div>
					<form:input path="userLogin" type="text" id = "main-registration-login" class="input-small" placeholder="Enter login" />					
					<form:input path="userPassword" type="password" id = "main-registration-password" class="input-small" placeholder="Enter password"/>
					<div class = row>
						<div class = "span2">
							<form:errors cssClass="main-error-message" path="userlastName" />
						</div>						
					</div>	
					<form:input path="userlastName" type="text" id = "main-registration-fio" class="input-small" placeholder="Enter name and last name"/>
					<div class = row>
						<div class = "span2">
							<form:errors cssClass="main-error-message" path="userPhone" />
						</div>						
					</div>
					<form:input path="userPhone" type="text" id = "main-registration-phone" class="input-small" placeholder="Enter phone"/>
					<form:input path="userBirthday" type="text" class="input-small" id = "main-registration-birthday" placeholder="Enter birthday"/>										
					<p class = "main-registration-gender-text">Gender: <form:errors cssClass="main-error-message" path="userGender" /></p>	
					<form:radiobutton path="userGender" value = "Male" id = "main-registration-gender-male"/> Male
					<form:radiobutton path="userGender" value = "Female" id = "main-registration-gender-female" /> Female
					<p class = "main-registration-education-text">Education: <form:errors cssClass="main-error-message" path="userEducation" /></p>
					<form:radiobutton path="userEducation" value = "Degree" id = "main-registration-education-hight" /> Master
					<form:radiobutton path="userEducation" value = "Master Degree" id = "main-registration-education-degree" /> Bachelor's
					<form:radiobutton path="userEducation" value = "Other" id = "main-registration-education-other" /> other<br><br>													
					<form:errors cssClass="main-error-message-user-mail" path="userMail" />	
					<form:input path="userMail" type="text" class="input-small" id = "main-register-email" placeholder="E-mail (optional)"/>
					<form:errors cssClass="main-error-message" path="userAdress" />					
					<form:textarea path="userAdress" rows="3" placeholder = "Adress (optional)" id = "main-registration-adress"/>
					<button type="submit"  class="btn btn-success">Sign up</button>
				</div>
			</form:form>	
		</div>
	</div>
</div>

<!-- footer -->

	<div class = "container">			
		<font class = "navbar-text">©2015 CRUD with Spring MVC</font>	
	</div>

</body>
</html>