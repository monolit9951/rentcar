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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%@ include file="/view/orders/menu.jspf"%>
	<br>
	<center>
		<h1>Заказ: №${order.getId()}</h1>
	</center>
	<h2>Паспортные данные:</h2>
	Имя : ${order.getName()}
	<br> Фамилия : ${order.getSurname()}
	<br> Отчество : ${order.getPatronymic()}
	<br> Место проживания : ${order.getCity()}
	<br> Дата рождения : ${order.getBorningDate()}
	<br> Пол : ${order.getSex()}


	<h2>Данные заказа</h2>
	Номер автомобиля: ${order.getCarId()}
	<br> Дата начала проката : ${order.getDateStart()}
	<br> Дата окончания проката : ${order.getDateEnd()}
	<br> Присутствие водителя :
	<c:if test="${order.isWithDriver()}"> Да</c:if>
	<c:if test="${order.isWithDriver()==false}"> Нет</c:if>
	<br> Ид пользователя : ${order.getUserId()}
	<br>
	<div>
		<form action="${pageContext.servletContext.contextPath}/Controller"
			method="post">
			<input type="submit" value="Подтвердить"> <input
				type="hidden" name="command" value="confirm_order"> <input
				type="hidden" name="id" value="${order.getId()}">
		</form>
	</div>
	<br>
	<div>
		<form action="${pageContext.servletContext.contextPath}/Controller"
			method="post">
			<input type="submit" value="Отклонить"> <input type="text">
			<input type="hidden" name="command" value="reject_order"> <input
				type="hidden" name="id" value="${order.getId()}">
		</form>
	</div>
</body>
</html>