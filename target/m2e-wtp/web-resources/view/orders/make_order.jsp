<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/reg.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/tcal.css"
	type="text/css" />
<script type="text/javascript" src="view/js/tcal.js"></script>
</head>
<body>
	<center>
		${car.getMark()} ${car.getModel()} ${car.getId()}
		<form action="${pageContext.servletContext.contextPath}/Controller"
			method="POST">
			<div class="form_action">
				<h1>Данные о заказе :</h1>
				<br> <label>Дата начала</label> <input type="text"
					name="dateBegin"><br> <label>Дата конца</label> <input
					type="text" name="dateEnd"><br> <select name="driver">
					<option value="true">С водителем</option>
					<option value=false select>Без водителя</option>
				</select> <br>

				<h1>Ваши паспортные данные:</h1>
				<input type="hidden" name="command" value="paste_order"> <label>Имя</label><input
					type="text" name="name" value="${user.getName()}"> <br>
				<label>Фамилия</label><input type="text" name="surname"
					value="${user.getSurname()}"> <br> <label>Отчество</label><input
					type="text" name="patronymic"> <br> <label>Место
					рождения </label> <input type="text" name="city" value="${user.getCity()}">
				<br> <label>Пол</label><select name="sex">
					<option value="male">Мужской</option>
					<option value=female>Женский</option>
				</select> <br>
				<div>
					<label>Дата рождения</label> <input type="text" name="dateBirth" />
				</div>
				<input type="hidden" name="carId" value="${car.getId()}"> <input
					type="hidden" name="carPrice" value="${car.getPrice()}"> <br>
				<input type="submit" value="Подтвердить">
			</div>
		</form>
	</center>
</body>
</html>