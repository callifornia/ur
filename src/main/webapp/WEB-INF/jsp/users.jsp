<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Пользователи</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.10.2.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/show_message.js' />"></script>
<link type="text/css" rel="stylesheet"  href="<c:url value='/resources/css/bootstrap.min.css' />" />


</head>
<body>
<div class = "container" >
	<h1>Users page</h1>
	<p>
		Login as:
		<sec:authentication property="name" />
	</p>
	<br>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:if test="${not empty success}">
			<p class="message">${success}</p>
			<br>
			<br>
		</c:if>
	</sec:authorize>

	<a href="${pageContext.request.contextPath}/logout"> Logout </a>
	<br>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="${pageContext.request.contextPath}/register">Register user</a>
		<br>
		<br>
	</sec:authorize>
	
	<form:form name="searchForm" action="${pageContext.request.contextPath}/searchRequest" method = "POST" modelAttribute="search">
		<table>
			<tr>
				<td>Search: </td>
				<td>
					<form:input name = "name" path="searchRow"/> 
				</td>
				<script>
  					document.getElementsByName('name')[0].focus();
				</script>
			</tr>
			<tr>
				<td>
					<form:radiobutton path="searchType" value="all" checked = "checked" />all users<br>
					<form:radiobutton path="searchType" value="login" />login<br>
					<form:radiobutton path="searchType" value="phone"/>phone <br>
					<form:radiobutton path="searchType" value="lastName" />last Name<br>					
				</td>			
			</tr>			
			<tr>
				<td>
					<input type = "submit" name = "submit" value = "submit">
				</td>
			</tr>						
		</table>
	</form:form>
	
	

	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<table class="table table-striped table-bordered">
		<thead style="background-color: #596068; color: white">		
			<tr>
				<th>id</th>
				<th>Логин</th>
				<th>Роль</th>
				<th>ФИО</th>
				<th>Телефон</th>
				<th>Статус</th>
				<th>Почта</th>
				<th>Стать</th>
				<th>Что с ним сделать?</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${user}">
			<c:choose>
					<c:when test="${user.userEnable == false }">
						<tr class = "error">
							<td>${user.userId}</td>
							<td><a href="${pageContext.request.contextPath}/user/${user.userId}">${user.userLogin}</a></td>
							<c:choose>
								<c:when test="${user.userRole == 'ROLE_ADMIN' }">
									<td>admin</td>
								</c:when>
								<c:when test="${user.userRole == 'ROLE_REGULAR_USER' }">
									<td>user</td>
								</c:when>
								<c:otherwise>
									<td>unknown role</td>
								</c:otherwise>
							</c:choose>
							<td>${user.userlastName}</td>
							<td>${user.userPhone }</td>
							<c:choose>
								<c:when test="${user.userEnable == true }">
									<td class = "error">живой</td>
								</c:when>
								<c:when test="${user.userEnable == false }">
									<td>казнен</td>
								</c:when>
							</c:choose>
							<td>${user.userMail}</td>
							<td>${user.userGender}</td>
							<c:choose>
								<c:when test="${user.userEnable == false }">
									<td><a href="${pageContext.request.contextPath}/recovery/${user.userId}">воскресить</a></td>
								</c:when>
								<c:otherwise>
								<td>
									<a href="${pageContext.request.contextPath}/edit/${user.userId}">поправить</a> 
									<a href="${pageContext.request.contextPath}/delete/${user.userId}">казнить</a>
								</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td>${user.userId}</td>
							<td><a href="${pageContext.request.contextPath}/user/${user.userId}">${user.userLogin}</a></td>
							<c:choose>
								<c:when test="${user.userRole == 'ROLE_ADMIN' }">
									<td>admin</td>
								</c:when>
								<c:when test="${user.userRole == 'ROLE_REGULAR_USER' }">
									<td>user</td>
								</c:when>
								<c:otherwise>
									<td>unknown role</td>
								</c:otherwise>
							</c:choose>
							<td>${user.userlastName}</td>
							<td>${user.userPhone }</td>
							<c:choose>
								<c:when test="${user.userEnable == true }">
									<td class = "error">живой</td>
								</c:when>
								<c:when test="${user.userEnable == false }">
									<td>казнен</td>
								</c:when>
							</c:choose>
							<td>${user.userMail}</td>
							<td>${user.userGender}</td>
							<c:choose>
								<c:when test="${user.userEnable == false }">
									<td><a href="${pageContext.request.contextPath}/recovery/${user.userId}">воскресить</a></td>
								</c:when>
								<c:otherwise>
								<td>
									<a href="${pageContext.request.contextPath}/edit/${user.userId}">поправить</a> 
									<a href="${pageContext.request.contextPath}/delete/${user.userId}">казнить</a>
								</td>
					</c:otherwise>
				</c:choose>
			</tr>



					</c:otherwise>
				</c:choose>
					
			
		</c:forEach>
	</tbody>
</table>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_REGULAR_USER')">
		<table class="table table-hover table-bordered table-striped">
			<tr>
				<td>Логин</td>
				<td>Роль</td>
				<td>ФИО</td>
				<td>Телефон</td>
				<td>Почта</td>
				<td>Стать</td>
			</tr>
			<c:forEach var="user" items="${user}">
				<c:if test="${user.userEnable == true}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/user/${user.userId}">${user.userLogin}</a></td>
						<c:choose>
							<c:when test="${user.userRole == 'ROLE_ADMIN' }">
								<td>admin</td>
							</c:when>
							<c:when test="${user.userRole == 'ROLE_REGULAR_USER' }">
								<td>user</td>
							</c:when>
							<c:otherwise>
								<td>unknown role</td>
							</c:otherwise>
						</c:choose>
						<td>${user.userlastName}</td>
						<td>${user.userPhone }</td>
						<td>${user.userMail}</td>
						<td>${user.userGender}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</sec:authorize>
		<div class="pagination pagination-centered">
		  <ul>
			 <c:if test="${not empty pagi.previousPage }">
				<li><a href="${pageContext.request.contextPath}/users/${pagi.previousPage}">Предыдущая</a></li>
			</c:if>
			<c:forEach items="${pagi.pageList}" var="page">
				<c:choose>
					<c:when test="${pagi.currentPage == page}">
					 	<li class="active"><a href="#">${page}</a></li>
					</c:when>
					<c:otherwise>
	     				<li> <a href="${pageContext.request.contextPath}/users/${page}"> ${page} </a> </li>
					</c:otherwise>
				</c:choose>
			</c:forEach> 	  
		    <c:if test="${not empty pagi.nextPage}">
				<li><a href="${pageContext.request.contextPath}/users/${pagi.nextPage}">Следующая</a></li>
			</c:if>	   
		  </ul>
		</div>
	</div>
</body>
</html>