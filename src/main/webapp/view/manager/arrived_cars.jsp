<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/directive/page.jspf"%>
<%@ include file="/view/directive/taglib.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/table.css"
	type="text/css">
</head>
<body>
	<%@ include file="/view/manager/menu.jspf"%>
	<br>
	<%@ include file="/view/manager/check_content.jspf"%>
	<c:if test="${not empty orders}">
	<table border="1">
		<tr>
			<th>Номер заказа
			<th>Начало проката
			<th>Окончание проката
			<th>Возравт авто
		</tr>
		<c:forEach items="${orders}" var="order" varStatus="varstatus">
			<tr>
				<td>№ ${order.getId()}
				<td>${order.getDateStart()}
				<td>${order.getDateEnd()}
				<td><a
					href="${pageContext.servletContext.contextPath}/Controller?command=arrived_car&id=${order.getId()}">
						Зарегестрировать возравт автомобиля</a>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>