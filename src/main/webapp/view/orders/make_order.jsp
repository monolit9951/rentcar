<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/directive/page.jspf"%>
<%@ include file="/view/directive/taglib.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/reg.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
</head>
<body>
	<form action="${pageContext.servletContext.contextPath}/Controller"
		method="POST">
		<table align="center">
			<tr>
				<td>
					<h1>Данные о заказе :</h1>
				</td>
			</tr>
			<tr>
				<td><label>Дата начала</label> <input type="date"
					name="dateBegin"></td>
			</tr>
			<tr>
				<td><label>Дата конца</label> <input type="date" name="dateEnd"></td>
			</tr>
			<tr>
				<td><select name="driver">
						<option value="true">С водителем(20$)</option>
						<option value=false select>Без водителя</option>
				</select></td>
			</tr>
			<tr>
				<td>
					<h1>Паспортные данные :</h1>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" name="command" value="paste_order">
					<label>Имя</label> <input type="text" name="name"
					value="${user.getName()}"></td>
			</tr>
			<tr>
				<td><label>Фамилия</label> <input type="text" name="surname"
					value="${user.getSurname()}"></td>
			</tr>
			<tr>
				<td><label>Отчество</label> <input type="text"
					name="patronymic"></td>
			</tr>
			<td><label>Место рождения </label> <input type="text"
				name="city" value="${user.getCity()}"></td>
			</tr>
			<tr>
				<td><label>Пол</label> <select name="sex">
						<option value="male">Мужской</option>
						<option value=female>Женский</option>
				</select></td>
			</tr>
			<tr>
				<td><label>Дата рождения</label> <input type="date"
					name="dateBirth"></td>
			</tr>
			<tr>
				<td><input type="button" onclick="validate_order(this.form)"
					value="Заказать"></td>
			</tr>

		</table>
		<input type="hidden" name="carId" value="${car.getId()}"> <input
			type="hidden" name="carPrice" value="${car.getPrice()}">
	</form>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/js/make_order_validate.js">
		
	</script>

</body>
</html>