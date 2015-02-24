<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file = "taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>User login</title>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.10.2.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/show_message.js' />"></script>
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/bootstrap.css' />" />
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/style.css' />" />
</head>
<body>

<div class = "header" id = "header">
	<div class = "container">
		<div class = "row">
			<div class = "span7"> <h2>Write somethink here</h2></div>
			<div class = "span5"> 				
				<div class = "row">	
					<form>
					<fieldset>
						<label>Неверно имя пользователя или пароль &nbsp </label>
					    <input type="text" id = "input-login" class="input-small" placeholder="Логин" >
					    <input type="password" id = "input-password" class="input-small" placeholder="Пароль">
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
			<img src="<c:url value='/resources/img/mainpic.png' />" id = "main-pic" >
		</div>
		<div class = "span5" >
			<p class = "main-registration-text">Регистрация</p>	
			<form class="form">
				<div class = "row">
					<input type="text" id = "main-registration-login" class="input-small" placeholder="Логин" >
					<input type="password" id = "main-registration-password" class="input-small" placeholder="Пароль">	
					<input type="text" id = "main-registration-fio" class="input-small" placeholder="Фамилия имя отчество">	
					<input type="text" id = "main-registration-phone" class="input-small" placeholder="Телефон">
					<input type="text" class="input-small" id = "main-registration-birthday" placeholder="День рождения">
				</div>
				<p class = "main-registration-gender-text">Пол:</p>
				<label class="radio" id = "main-registration-gender-male">
					<input type="radio" name="optionsRadios1" id="optionsRadios1" value="option1"> Мужской
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios1" id="optionsRadios2" value="option2"> Женский
				</label>

				<p class = "main-registration-education-text">Образование:</p>
				<label class="radio" id = "main-registration-education-hight">
					<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1">Высшее
				</label>
				<label class="radio" id = "main-registration-education-degree">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">Бакалавр
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">другое
				</label>				

				<input type="text" class="input-small" id = "main-register-email" placeholder="Эл. адресс (не обязательно)">
				<textarea rows="3" placeholder = "Адресс (не обязательно)" id = "main-registration-adress"></textarea>
				<button type="submit"  class="btn btn-success">Регистрация</button>
			</form>	
		</div>
	</div>
</div>







 	<%-- <div class = "navbar navbar-inverse navbar-fixed-top">
		<div class = "navbar-inner" id = "header">
			<div class = "container" id ="container-main-page">
				<div class = "row" > 
					<div class = "span7">
							<h1 style = "color:white">Write somethink here</h1>
					</div>				
					<div class = "span5">
						<div class = "row">
							<div class = "span5" style = "color: white"><p>Неверное имя пользователя или пароль</p></div>
						</div>
						<div class = "row" id = "login-main-page">
							<div class = "span5">
								<form class="form" name = "loginForm" action="<c:url value = 'j_spring_security_check' />" method = "POST" >
								  <input type="text" id = "input-login" class="input-small" placeholder="Логин" name = "j_username">
								  <input type="password" id = "input-password" class="input-small" placeholder="Пароль" name = "j_password">
								  <button type="submit" id = "input-button" class="btn btn-small">Войти</button>
								  <label class="checkbox">
								    <input type="checkbox" name = "remember-me"> Запомнить
								  </label>
								</form>						
							</div>
						</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div class = "row">
		<div class = "container" >
			<div class = "row">
				<div class  = "span7"><img src="<c:url value='/resources/img/main-pic.png' />" class="img-polaroid">Picture</div>
				<div class  = "span5"> Form </div>
			</div>
		</div> 
	</div> --%>
		



		<%-- <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null }">
			<div>
				Login failed. <br>
				Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} 
			</div>
		</c:if>
		<a href="${pageContext.request.contextPath}/initializeTables"> initialize tables </a>
		<c:if test="${not empty inserted}"> <p class = "message">${inserted}</p><br><br></c:if>
			<form class="form-signin" name = "loginForm" action="<c:url value = 'j_spring_security_check' />" method = "POST">
				<h2 class="form-signin-heading">Please sign in</h2>
				<input type="text" class="input-block-level" placeholder="login" name = "j_username"> 
				<input type="password" class="input-block-level" placeholder="Password" name = "j_password"> 
				<label lass="checkbox"> 
					<input type="checkbox" name = "remember-me" value="remember-me"> Не выходить из системы
				</label>
				<font><a href="${pageContext.request.contextPath}/registered">Register </a></font>
				<button class="btn btn-success" type="submit">Регистрация</button>
			</form> --%>
</body>
</html>