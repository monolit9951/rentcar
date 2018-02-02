<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/directive/page.jspf"%>
<%@ include file="/view/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/table.css"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/view/accounts/menu.jspf"%>
	<c:if test="${!accounts.isEmpty()}">
		<center>
			<h1>История</h1>
			<table>
				<tr>
					<th>Номер счета
					<th>Оплаченная сумма
					<th>Сообщение
					<th>Дата получения счета
				</tr>
				<c:forEach items="${accounts}" var="acc" varStatus="status">
					<tr>
						<td>${acc.getId()}
						<td>${acc.getPrice()}$
						<td>${acc.getMessage()}
						<td>${acc.getDateCreate()}
					</tr>
				</c:forEach>
			</table>
		</center>
	</c:if>
	<c:if test="${accounts.isEmpty()}">
		<center>
			<h1>Пусто</h1>
		</center>
	</c:if>
</body>
</html>
