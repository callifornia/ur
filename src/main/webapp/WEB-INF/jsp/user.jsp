<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Пользователь</title>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.10.2.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/show_message.js' />"></script>
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/bootstrap.css' />" />
	<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/style.css' />" />
</head>
<body>
<body>
	<div class = "header" id = "header">
		<div class = "container">
			<div class = "row">
				<div class = "span12"> 
					<form:form name="searchForm" action="${pageContext.request.contextPath}/searchRequest" method = "POST" modelAttribute="search">
						 <div id = "users-search-main-div">
					    	<label id = "users-search-label">Поиск</label>
					    	<form:select path="searchType" class="span2" id = "users-search-select">
					    			<form:option  value="all" checked = "checked">All</form:option>
					    			<form:option  value="login" checked = "checked">Логин</form:option>
					    			<form:option  value="phone" checked = "checked">Телефон</form:option>
					    			<form:option  value="lastName" checked = "checked">ФИО</form:option>
					    	</form:select>
	 						<form:input path="searchRow" class="span2" id="appendedInputButton" size="16" type="text" name = "name" />  						
	  						<button class="btn btn-inverse" id = "users-search-go" type="submit">Go!</button>  						
	  						
	  						<sec:authorize access="hasRole('ROLE_ADMIN')">
		  						<a href="${pageContext.request.contextPath}/register" class = "users-search-links" >
		  							<img src="<c:url value='/resources/img/add-user-icon.png' />" onmouseover = "this.src='<c:url value='/resources/img/add-user-icon-hover.png' />'" onmouseout = "this.src = '<c:url value='/resources/img/add-user-icon.png' />'" border = "0" alt = "">
		  						</a>
							</sec:authorize>
							<a href="${pageContext.request.contextPath}/logout">
								<img  id = "users-icon-exit-user" align="right" src="<c:url value='/resources/img/logout-icon.png' />" onmouseover = "this.src='<c:url value='/resources/img/logout-icon-hover.png' />'" onmouseout = "this.src = '<c:url value='/resources/img/logout-icon.png' />'" border = "0" alt = "">
							</a>
						</div>				 
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class = "row">
			<div class = "span12" id = "user-main-user-page">			
				<table border="0" class = "user-table">
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label"> Логин: </p></td>
						<td><p>${user.userLogin}</p></td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label"> Роль: </p></td>
						<td>
							<c:choose>
								<c:when test="${user.userRole == 'ROLE_ADMIN' }">
									<p class = "user-tanle-text-description">User role: Admin</p>
								</c:when>
								<c:when test="${user.userRole == 'ROLE_REGULAR_USER' }">
									<p class = "user-tanle-text-description">User role: User</p>
								</c:when>
								<c:otherwise>
									<p class = "user-tanle-text-description">инопланетянин</p>
								</c:otherwise>
							</c:choose>		
						</td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label"> ФИО: </p></td>
						<td><p class = "user-tanle-text-description">${user.userlastName}</p></td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label"> Телефон: </p></td>
						<td><p class = "user-tanle-text-description">${user.userPhone}</p></td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label" > День рождения: </p></td>
						<td><p class = "user-tanle-text-description">
						<c:choose>
							<c:when test="${not empty user.userBirthday}">
								${user.userBirthday}
							</c:when>
							<c:otherwise>
								не указано
							</c:otherwise>
						</c:choose>				
						</p></td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label" > Почта: </p></td>
						<td><p class = "user-tanle-text-description">
						<c:choose>
							<c:when test="${not empty user.userMail}">
								${user.userMail}
							</c:when>
							<c:otherwise>
								не указано
							</c:otherwise>
						</c:choose>				
						</p></td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label" > Адресс: </p></td>
						<td><p class = "user-tanle-text-description">
						<c:choose>
							<c:when test="${not empty user.userAdress}">
								${user.userAdress}
							</c:when>
							<c:otherwise>
								не указано
							</c:otherwise>
						</c:choose>				
						</p></td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label" > Пол: </p></td>
						<td><p class = "user-tanle-text-description">${user.userGender}</p></td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label" > Образование: </p></td>
						<td><p class = "user-tanle-text-description">${user.userEducation}</p></td>
					</tr>
					<tr>
						<td class = "user-table-left-side"><p class = "user-tanle-text-label" > Описание: </p></td>
						<td><p class = "user-tanle-text-description">
							<c:choose>
								<c:when test="${not empty user.userDescription}">
									${user.userDescription}
								</c:when>
								<c:otherwise>
									не указано
								</c:otherwise>
							</c:choose>	
						</p></td>
					</tr>
				</table>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href = "${pageContext.request.contextPath}/edit/${user.userId}" id = "user-edit-button" class = "btn  btn-primary"> Редактировать</a>
				<a href = "${pageContext.request.contextPath}/users" class = "btn"> На главную</a>
				</sec:authorize>			
				<sec:authorize access="hasRole('ROLE_REGULAR_USER')">
					<a href = "${pageContext.request.contextPath}/users" class = "btn  btn-primary"> На главную</a>
				</sec:authorize>
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
</body>
</html>