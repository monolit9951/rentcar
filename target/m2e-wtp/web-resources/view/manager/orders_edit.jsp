<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/table.css"
	type="text/css">
</head>
<body>
	<%@ include file="/view/manager/menu.jspf"%>
	<br>
	<table border="1">
		<tr>
			<th>Номер заказа
			<th>Количество дней
			<th>Конечная сумма
			<th>Детали заказа
		</tr>
		<c:forEach items="${orders}" var="order" varStatus="varstatus">
			<tr>
				<td>№ ${order.getId()} ${order.getName()}
				<td>${order.getDays()}
				<td>${order.getPrice()}
				<td><a
					href="${pageContext.servletContext.contextPath}/Controller?command=order_edit&id=${order.getId()}">
						Рассмотреть</a>
		</c:forEach>
	</table>
</body>
</html>