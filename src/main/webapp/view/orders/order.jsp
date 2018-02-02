<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/directive/page.jspf"%>
<%@ include file="/view/directive/taglib.jspf"%>
<%@page import="ua.nure.bei.SummaryTask4.util.Util"%>
<%@page import="ua.nure.bei.SummaryTask4.models.Order"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/table.css"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/view/orders/menu.jspf"%>
	<center>
		<h1>Заказ №${order.id}</h1>

		<br>
		<h2>${car.mark}-${car.model}</h2>
		<h2>Авто №${car.id}</h2>
		<img alt=""
			src="${pageContext.servletContext.contextPath}/img/cars/${car.imageURL}"
			width="340" height="200"> <br>
	</center>
	<h3 align="center">
		Количество дней проката: ${order.days}<br> Прокат с
		${order.dateStart} по ${order.dateEnd} (включительно)<br> Общая
		сумма: ${order.price}$<br> Статус:<%
		Order o = (Order) request.getAttribute("order");
	%>
		<%=Util.getStatusMessage(o.getStatusId(), o.getReason())%>
		<br>
		<c:if test="${order.statusId==3}">
			<form action="${pageContext.servletContext.contextPath}/Controller"
				method="POST">
				<input type="hidden" name="command" value="return_money"> <input
					type="hidden" name="id" value="${order.id}">
				<button class="my_button">Возрат средств</button>
			</form>

		</c:if>
		<br> <a
			href="${pageContext.servletContext.contextPath}/Controller?command=orders_wait">Назад</a>
	</h3>
	<br>
</body>
</html>