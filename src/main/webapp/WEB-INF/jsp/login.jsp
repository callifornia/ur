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
			<div class = "span7"> <h2>Write somethink here</h2></div>
			<div class = "span5"> 				
				<div class = "row">	
					<form name = "loginForm" action="<c:url value = 'j_spring_security_check' />" method = "POST">
					<fieldset>				 	
						<c:choose>
							<c:when test="${SPRING_SECURITY_LAST_EXCEPTION != null }">
								<p>Неверно имя пользователя или пароль</p>
							</c:when>								
							<c:otherwise>
								<pl>&nbsp </p>
							</c:otherwise>
						</c:choose>						
					    <input type="text" id = "input-login" class="input-small" placeholder="Логин" name = "j_username" >
					    <input type="password" id = "input-password" class="input-small" placeholder="Пароль" name = "j_password">
					    <button type="submit" id = "input-login-button" class="btn btn-small">Войти</button>					    
					    <label class="checkbox">
						    <input type="checkbox" name = "remember-me"> Запомнить
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
			<p class = "main-registration-text">Регистрация</p>	
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
					<form:input path="userLogin" type="text" id = "main-registration-login" class="input-small" placeholder="Логин" />					
					<form:input path="userPassword" type="password" id = "main-registration-password" class="input-small" placeholder="Пароль"/>
					<div class = row>
						<div class = "span2">
							<form:errors cssClass="main-error-message" path="userlastName" />
						</div>						
					</div>	
					<form:input path="userlastName" type="text" id = "main-registration-fio" class="input-small" placeholder="Фамилия имя отчество"/>
					<div class = row>
						<div class = "span2">
							<form:errors cssClass="main-error-message" path="userPhone" />
						</div>						
					</div>
					<form:input path="userPhone" type="text" id = "main-registration-phone" class="input-small" placeholder="Телефон"/>
					<form:input path="userBirthday" type="text" class="input-small" id = "main-registration-birthday" placeholder="День рождения"/>										
					<p class = "main-registration-gender-text">Пол: <form:errors cssClass="main-error-message" path="userGender" /></p>	
					<form:radiobutton path="userGender" value = "Male" id = "main-registration-gender-male"/> Мужской
					<form:radiobutton path="userGender" value = "Female" id = "main-registration-gender-female" /> Женский
					<p class = "main-registration-education-text">Образование: <form:errors cssClass="main-error-message" path="userEducation" /></p>
					<form:radiobutton path="userEducation" value = "Degree" id = "main-registration-education-hight" /> Высшее
					<form:radiobutton path="userEducation" value = "Master Degree" id = "main-registration-education-degree" /> Бакалавр
					<form:radiobutton path="userEducation" value = "Other" id = "main-registration-education-other" /> другое<br><br>													
					<form:errors cssClass="main-error-message-user-mail" path="userMail" />	
					<form:input path="userMail" type="text" class="input-small" id = "main-register-email" placeholder="Эл. адресс (не обязательно)"/>
					<form:errors cssClass="main-error-message" path="userAdress" />					
					<form:textarea path="userAdress" rows="3" placeholder = "Адресс (не обязательно)" id = "main-registration-adress"/>
					<button type="submit"  class="btn btn-success">Регистрация</button>
				</div>
			</form:form>	
		</div>
	</div>
</div>

<!-- footer -->
<div class = "navbar navbar-default navbar-fixed-bottom" id = "footer">	
	<div class = "container">			
			
		<font class = "navbar-text">© 2015 Developed by Grisha</font>	
		<a href="${pageContext.request.contextPath}/initializeTables"> Инициализировать таблици </a>	
		<a href="${pageContext.request.contextPath}/check"> throw Exception() </a>	
	</div>
</div>
</body>
</html>