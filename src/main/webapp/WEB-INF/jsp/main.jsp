<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Проекты</title>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/bootstrap.css' />" />
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/style.css' />" />
</head>
<body>
	<!-- body-->
	<div class = "container">
		<div class = "row">
			<div class = "span12" id = "general-main-page">
				<h1>Portfolio</h1>
					<img id = "general_line" src="<c:url value='/resources/img/line.png' />" border = "0" >
					<div class = "span12">
						<div class = "span4" align="center" id = "general-pic-btn" >
							<img id = "general_line" src="<c:url value='/resources/img/icon_progect.png' />" border = "0" ><br>
							<a href="login"  id = "general-button" class="btn btn-success" type="button">View Project</a>
						</div>
						<h3>Технологии:</h3>
 						<p>Spring Security, Spring MCV, JSP, JDBC, MySQL, Bootstrap, Log4j, Maven</p>
						<h3>Исходный код:</h3>
						<p><a target="_blank" href = "https://github.com/callifornia/SpringMVC-JDBC">https://github.com/callifornia/SpringMVC-JDBC</a></p>
						<h3>Краткое описание:</h3>
						<p>Серверное приложения которое взаимодействует с БД. <br>Главная страница - авторизация (запомнить меня), регистрация нового пользователя. <br>После успешной 
							регистрации или авторизации пользователь попадает на страницу, на которой отображаются 
							уже зарегестрированные пользователи, и форма поиска пользователей (по логину, фио, 
							телефону).<br><br> Учетная запись администратор позволяет редактировать а так же удалять или 
							востанавливать учетные записии. <br>Учетная запись пользователь(при регистрации 
							устанавливается по умолчанию) позволяет только просматривать.
						</p>
					</div>
					<img id = "general_line" src="<c:url value='/resources/img/line.png' />" border = "0" >
					<div class = "span12">
						<div class = "span4" align="center" id = "general-pic-btn" >
							<img id = "general_line" style = "width: 253px; height: 161px" src="<c:url value='/resources/img/icon2_progect.png' />" border = "0" ><br>
							<a href=""  id = "general-button" class="btn btn-success disabled" type="button">View Project</a> 
						</div>
						<h3>Технологии:</h3>
 						<p>Servlet, JSP, JDBC, MySQL, HTML/CSS/JavaScript, Log4j2, Maven</p>
						<h3>Исходный код:</h3>
						<p><a target="_blank" href = "https://github.com/callifornia/Servlet-JSP-JDBC">https://github.com/callifornia/Servlet-JSP-JDBC</a></p>
						<h3>Краткое описание:</h3>
						<p>Серверное приложения которое взаимодействует с БД. <br>Главная страница - авторизация<br>После успешной 
							авторизации пользователь попадает на страницу поиска пользователей (по вхождению логина, вхождения телефона).<br><br> 
							Учетная запись администратор позволяет редактировать а так же удалять учетные записии.
						</p>
					</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<div class = "navbar navbar-default navbar-fixed-bottom" id = "footer">	
		<div class = "container">			
			<font class = "navbar-text">© 2015 Developed by Grisha</font>	
		</div>
	</div>

</body>
</html>