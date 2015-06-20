<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>404</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>User login</title>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/bootstrap.css' />" />
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/style.css' />" />
</head>
<body>

<div class = "container">
	<div class = "row" id = "error-page-general-row">
		<div align="center" class = "span12">
			<p align = "center" id = "error-page-text-genereal">404</p>
			<p align = "center" id = "error-page-text-main">По видимому на свете нет ничего, что не могло бы случится</p>
			<p align = "right" id = "error-page-text-second" >(Марк Твен)</p>
			<a class = "btn btn-success" href="${pageContext.request.contextPath}/users"> На главную </a>	
		</div>
	</div>
</div>

<!-- footer -->
<%-- <div class = "navbar navbar-default navbar-fixed-bottom" id = "footer">	
	<div class = "container">			
			
		<font class = "navbar-text">© 2015 Developed by Grisha</font>	
		<a href="${pageContext.request.contextPath}/initializeTables"> Инициализировать таблици </a>	
		<a href="${pageContext.request.contextPath}/check"> Бросить експешин </a>	
	</div>
</div> --%>
</body>
</html>