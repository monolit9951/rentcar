<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h1>Неоплаченные счета</h1>
			<table>
				<tr>
					<th>Номер счета
					<th>Сумма к оплате
					<th>Сообщение
					<th>Дата получения счета
					<th>Оплата
				</tr>
				<c:forEach items="${accounts}" var="acc" varStatus="status">
					<tr>
						<td>${acc.getId()}
						<td>${acc.getPrice()}$
						<td>${acc.getMessage()}
						<td>${acc.getDateCreate()}
						<td><a href="${pageContext.servletContext.contextPath}/Controller?command=account_not_payed&id=${acc.getId()}">Оплатить</a>
					</tr>
				</c:forEach>
			</table>
		</center>
	</c:if>
	<c:if test="${accounts.isEmpty()}">
		<center>
			<h1>Неоплаченных счетов не найдено</h1>
		</center>
	</c:if>
</body>
</html>
