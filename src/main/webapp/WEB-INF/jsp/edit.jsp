<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file = "taglibs.jsp" %>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Редактирование пользователя</title>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.10.2.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/show_message.js' />"></script>
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
	<form:form action="${pageContext.request.contextPath}/update" method="post" modelAttribute="user">
	
	<div class = "row" id = "edit-user-first-top-row">
		<div class = "span5" id = "edit-user-first-row">
			<p class = "edit-user-login-text"> Логин:</p>
			<form:input path="userLogin" value = "${userLogin}" readonly="true"/>			
		</div>
		<div class = "span5">
			<p class = "edit-user-id-text"> id:</p>
			<form:input  path="userId" value = "${userId}" readonly="true"/>
		</div>		
	</div>
	
	<div class = "row">
		<div class = "span5" id = "edit-user-first-row">
			<p class = "edit-user-password-text"> Пароль:</p>
			<form:input id ="edit-user-password-input" path="userPassword"/>
			<form:errors id = "edit-user-error-password" path="userPassword" />
		</div>
		<div class = "span5"><p class = "edit-user-password-text"> Роль:</p>
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
			</spring:bind>
		</div>
		<center><img alt="" align="center" src="<c:url value='/resources/img/edit-line.png' />"></center>
	</div>
	
	<!--  Second section -->

	
	<div class = "row" id = "edit-user-second-row">
		<div class = "span5" id = "edit-user-first-row">
			<p class = "edit-user-fio-text"> ФИО:</p>
			<form:input id ="edit-user-fio-input" value  ="${userlastName}" path="userlastName"/>
			<form:errors id = "edit-user-error-password" path="userlastName" />
		</div>
		<div class = "span5">
			<p class = "edit-user-mail-text"> Почта:</p>
			<form:input id ="edit-user-mail-input" value = "${userMail}" path="userMail"/>
			<form:errors id = "edit-user-error-mail" path="userMail" />
		</div>		
	</div>
	
	<div class = "row" id = "edit-user-second-row">
		<div class = "span5" id = "edit-user-first-row">
			<p class = "edit-user-phone-text"> Телефон:</p>
			<form:input id ="edit-user-phone-input" value = "${userPhone}" path="userPhone"/>
			<form:errors id = "edit-user-error-password" path="userPhone" />
		</div>
		<div class = "span5">
			<p class = "edit-user-password-text"> Пол:</p>
			<form:errors path="userGender" />
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
				</spring:bind>
		</div>	
		<center><img alt="" align="center" src="<c:url value='/resources/img/edit-line.png' />"></center>	
	</div>
	
	<!--  Thrid section -->
	
	
	<div class = "row" id = "edit-user-second-row">
		<div class = "span5" id = "edit-user-first-row">
			<p class = "edit-user-birthday-text"> День<br>рождения:</p>
			<form:input id="datepicker" class = "edit-user-birthday-input" value = "${userBirthday}" path="userBirthday"/>
		</div>
		<div class = "span6">
			<p class = "edit-user-password-text"> Образование:</p>
			<form:errors path="userEducation" />
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
			</spring:bind>
		</div>
	</div>
	
	<div class = "row" id = "edit-user-second-row">
		<div class = "span5" id = "edit-user-first-row">
			<p class = "edit-user-adress-text">Адресс:</p>
			<form:textarea rows="6" value = "${userAdress}" path="userAdress"/>	
			<form:errors id = "edit-user-error-password" path="userAdress" />
		</div>
		<div class = "span5">
			<p class = "edit-user-description-text"> Описание:</p>
			<form:textarea rows="6" class = "edit-user-description-input" value = "${userDescription}" path="userDescription"/>	
			<form:errors id = "edit-user-error-password" path="userDescription" />
		</div>
	</div>
	<div class = "row" id = "edit-button-form">
		<div class = "span12" align="center">
			<button type="submit" id = "edit-form-save-button"  class="btn btn-success">Сохранить</button>
			<a href="${pageContext.request.contextPath}/users" class = "btn">Отмена</a>
		</div>
	</div>
	</form:form>
</div>
	
	
	
			
			
		<%-- <p> ФИО:</p>
		<form:input value  ="${userlastName}" path="userlastName"/>
		<p> Почта:</p>
		<form:input value = "${userMail}" path="userMail"/>
		<p> Телефон:</p>
		<form:input value = "${userPhone}" path="userPhone"/>
		<p> Пол:</p> --%>
		<%-- <spring:bind path="userGender">
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
		</spring:bind> --%>
		
	<%-- 	<p> День рождения:</p>
		<form:input id="datepicker" value = "${userBirthday}" path="userBirthday"/>
		 --%>
		<%-- <p> Образование:</p>
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
		</spring:bind> --%>
		<%-- <p> Адресс:</p>
		<form:textarea value = "${userAdress}" path="userAdress"/>		
		<p> Описание:</p>
		<form:textarea value = "${userDescription}" path="userDescription"/>	 --%>	












<%-- <h1>
	<h1>Edit page</h1><br>
	<p> Login as: <sec:authentication property="name"/></p>
	
	<a href="${pageContext.request.contextPath}/logout">Logout</a><br>
	<a href="${pageContext.request.contextPath}/users/1">Users page </a><br>
	
	<form:form action="${pageContext.request.contextPath}/update" method="post" modelAttribute="user" >
	<table>
		<tr>
			<td>
				<p>User Login : ${user.userLogin} </p>
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
		<a href="${pageContext.request.contextPath}/users/1"> Cancel </a> 
		<form:hidden path="userId" value="${user.userId}" />
	</form:form>
</h1> --%>
</body>
</html>