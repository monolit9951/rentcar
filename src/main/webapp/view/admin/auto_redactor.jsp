<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/directive/page.jspf"%>
<%@ include file="/view/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/font-awesome.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/car_list.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%@ include file="/view/admin/menu_redactor.jspf"%>
	<br>
	<br>
	<c:forEach items="${carList}" var="car" varStatus="status">
		<li class="product-wrapper"><a
			href="${pageContext.servletContext.contextPath}/Controller?command=edit_car&id=${car.getId()}"
			class="product">
				<div class="product-main">
					<div class="product-photo">
						<img
							src="${pageContext.servletContext.contextPath}/img/cars/${car.getImageURL()}"
							alt="">
					</div>
					<center>
						<div class="product-text">
							<h2 class="produvt-name">${car.getMark()} ${car.getModel()}</h2>
							<p>
								Класс Авто: ${car.getAutoClass()}<br> Тип кузова :
								${car.getBody()}<br> Объем двигателя : ${car.getEngine()}<br>
								Тип топлива : ${car.getFuel()}
							</p>
						</div>
				</div>
				<div class="product-details-wrap">
					<div class="product-details">
						<center>
							<span class="product-price"> <b>${car.getPrice()}$ </b>
							</span>
					</div>
				</div>
		</a></li>
	</c:forEach>
</body>
</html>
