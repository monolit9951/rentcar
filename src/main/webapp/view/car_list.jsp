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
	<%@ include file="/view/menu.jspf"%>
	<br>
	<center>
		<form action="${pageContext.servletContext.contextPath}/Controller"
			method="POST">
			<input type="submit" value="<fmt:message
					key="carL_reset" />">
			<input type="hidden" name="command" value="car_list_clear">
		</form>
		<form action="" class="search">
			<select name="waySort">
				<option value="asc">По возрастанию</option>
				<option value="desc">По убыванию</option>
			</select>
			<fmt:message key="carL_sort" />
			<select name="sortBy">
				<option value="price"><fmt:message key="carL_sort_price" />
					<option value="alphabet"
					<c:if test="${sortBy!=null && sortBy.equals('alphabet')}">selected="selected"</c:if>>
			<fmt:message key="carL_sort_alphabet" /></select> <input type="submit" value="<fmt:message
					key="carL_sort" />">
			<input type="hidden" name="command" value="car_list"> <br>
			<input type="search" name="search"
				placeholder="<fmt:message
					key="carL_search" />"
				<c:if test="${search!=null}">value="${search}"</c:if> class="input" />
			<select name="findBy">
				<option value="name"><fmt:message key="carL_by_auto_name" />
				<option value="quility"
					<c:if test="${findBy!=null && findBy.equals('quility')}">selected="selected"</c:if>><fmt:message
						key="carL_by_quality" />
			</select> <br> <input type="submit"
				value="<fmt:message
					key="go" />" /> <input type="hidden"
				name="command" value="car_list">
		</form>
	</center>
	<br>
	<center>
		<h2>
			<c:if test="${carList.isEmpty()}">Простите , но по Вашему запросу <br>не удалось найти автомобили</c:if>
		</h2>
	</center>
	<c:forEach items="${carList}" var="car" varStatus="status">
		<li class="product-wrapper"><a
			href="${pageContext.servletContext.contextPath}/Controller?command=choose_car&id=${car.getId()}"
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












