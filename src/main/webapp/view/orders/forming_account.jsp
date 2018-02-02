<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/directive/page.jspf"%>
<%@ include file="/view/directive/taglib.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
</head>
<body>
	<center>
		<h1>Почти все!</h1>
		<br>
		<h1>Счет на имя: ${order.surname} ${order.name} ${order.patronymic}</h1>
		<p align="center" style="font-size: 20px">
			Количество дней проката : ${order.getDays()}<br> Номер
			автомобиля : №${car.getId()} <br>
			Наличие водителя : <c:if test="${order.withDriver}">Да</c:if>
			<c:if test="${!order.withDriver}">Нет</c:if>
			 <br> Сумма к оплате :
			${order.getPrice()}$ <br> Получение :${order.getDateStart()}<br>
			Возврат: ${order.getDateEnd()} (включительно) <br>
			<br>
		</p>
		<form action="${pageContext.servletContext.contextPath}/Controller"
			method="POST">
			<input type="hidden" name="command" value="paste_account">
			<button class="my_button">Оплатить</button>
		</form>
	</center>
</body>
</html>