<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/font-awesome.css" type="text/css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/table.css" type="text/css">
</head>
<body>
	<%@include file="/view/admin/menu_admin.jspf"%>
	<form action="${pageContext.servletContext.contextPath}/Controller"
		method="GET">
		<center>
			Поиск пользователя : <input type="text" name=info> <select
				name="findBy">
				<option>id</option>
				<option>login</option>
			</select> <input type="hidden" name="command" value="admin_user_get">
			<input type="submit" value="Search"> <br>
		</center>
	</form>

	<%@ include file="/view/admin/findUser.jspf"%>
	
	
	
	<c:if test="${userList!=null}">
		<h1 align="center">USERS:</h1>
		<table border="1">
			<c:forEach items="${userList}" var="user" varStatus="varstatus">
				<tr>
					<td>${user.getId()}</td>
					<td>${user.getLogin()}</td>
					<td>${user.getPassword()}</td>
					<td>${user.getName()}</td>
					<td>${user.getSurname()}</td>
					<td>${user.getCity()}</td>
					<td>${user.getNumber()}</td>
					<td>${user.getRoleId()}</td>

				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>