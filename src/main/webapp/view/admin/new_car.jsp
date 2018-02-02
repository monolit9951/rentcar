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
	<center>

		<form action="${pageContext.servletContext.contextPath}/Controller"
			method="POST">

			<div class="form-page">
				<div class="form">

					<label>Марка</label> <input type="text" name="mark"
						value="${car.mark}" /> <label>Модель</label> <input type="text"
						name="model" value="${car.model}" /> <label>Класс
						качества </label> <input type="text" name="autoClass"
						value="${car.autoClass}" /> <label>Тип кузов</label> <input
						type="text" name="body" value="${car.body}" /> <label>Двигатель</label>
					<input type="text" name="engine" value="${car.engine}" /> <label>Трансмиссия</label><input
						type="text" name="transmission" value="${car.transmission}" /> <label>Тип
						топлива</label> <input type="text" name="fuel" value="${car.fuel}" /> <label>Цена</label>
					<input type="text" name="price" value="${car.price}" /> <label>Ссылка
						на картинку</label> <input type="text" name="imageURL"
						value="${car.imageURL}" />
					<button>Зарегистрировать автомобиль</button>
					<input type="hidden" name="command" value="create_car">
				</div>
			</div>
		</form>
	</center>
</body>
</html>