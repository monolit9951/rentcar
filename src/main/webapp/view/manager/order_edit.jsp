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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%@ include file="/view/orders/menu.jspf"%>
	<br>
	<center>
		<h1>Заказ: №${order.getId()}</h1>
		<h2>Паспортные данные:</h2>
		<p align="center" style="font-size: 20px">
			Имя : ${order.getName()} <br> Фамилия : ${order.getSurname()} <br>
			Отчество : ${order.getPatronymic()} <br> Место проживания :
			${order.getCity()} <br> Дата рождения :
			${order.getBorningDate()} <br> Пол : ${order.getSex()}
		</p>

		<h2>Данные заказа</h2>
		<p align="center" style="font-size: 20px">
			Номер автомобиля: ${car.getId()} <br> Получение:
			${order.getDateStart()} <br> Возврат: ${order.getDateEnd()} <br>
			Присутствие водителя :
			<c:if test="${order.isWithDriver()}"> Да</c:if>
			<c:if test="${order.isWithDriver()==false}"> Нет</c:if>
			<br> Ид пользователя : ${order.getUserId()} <br>
		</p>

		<form action="${pageContext.servletContext.contextPath}/Controller"
			method="post">
			<button class="my_button">Подтвердить</button>
			<input type="hidden" name="command" value="confirm_order"> <input
				type="hidden" name="id" value="${order.getId()}">
		</form>
		<div class="form-page">
			<div class="form">

				<form action="${pageContext.servletContext.contextPath}/Controller"
					method="post">
					<label>Причина</label> <input type="text" name="reason"> <input
						type="hidden" name="command" value="reject_order"> <input
						type="hidden" name="id" value="${order.getId()}">
					<button>Отклонить</button>
				</form>
			</div>
		</div>
	</center>
</body>
</html>