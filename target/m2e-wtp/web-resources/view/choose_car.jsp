<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/car_list.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%@include file="/view/menu.jspf"%>
	<br>
	<center>
		<img width="510" height="300"
			src="${pageContext.servletContext.contextPath}/img/cars/${car.getImageURL()}"
			alt="">
		<h2>${car.getMark()}-${car.getModel()}</h2>
		Класс Авто: ${car.getAutoClass()} <br> Тип кузова :
		${car.getBody()} <br> Объем двигателя : ${car.getEngine()} <br>
		Тип топлива : ${car.getFuel()} <br> <a
			href="${pageContext.servletContext.contextPath}/Controller?command=car_list">Назад</a>
		<br>
		<h1>
			<a href="Controller?command=make_order&id=${car.getId()}">сделать
				заказ</a>
		</h1>
	</center>
</body>
</html>