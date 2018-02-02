<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<table border="1">
		<tr>
			<th>Номер заказа
			<th>Количество дней
			<th>Конечная сумма
			<th>Детали заказа
			<th>Оплата
		</tr>
		<c:forEach items="${orders}" var="order" varStatus="varstatus">
			<tr>
				<td>№ ${order.getId()} ${order.getName()}
				<td>${order.getDays()}
				<td>${order.getPrice()}
				<td><a
					href="${pageContext.servletContext.contextPath}/Controller?command=get_order&orderId=${order.getId()}&userId=${order.getUserId()}">
						Детали заказа</a>
				<td><a href="${pageContext.servletContext.contextPath}"><img
						src="${pageContext.servletContext.contextPath}/img/button.jpg"
						width="60" height="60"></a>
			</tr>
		</c:forEach>
	</table>
</body>
</html>