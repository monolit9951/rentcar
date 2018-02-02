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
		<h1>Оплата счета №${account.getId()}</h1>
		<h1>${account.getMessage()}</h1>
		<br>
		<p align="center" style="font-size: 22px">
			Сумма для оплаты : ${account.getPrice()}$<br> Дата создания
			счета : ${account.getDateCreate()}
		</p>

		<br>
		<form action="${pageContext.servletContext.contextPath}/Controller"
			method="POST">
			<button class="my_button">Оплатить</button>
			<input type="hidden"> <input type="hidden" name="id"
				value="${account.getId()}"> <input type="hidden"
				name="command" value="pay_account">

		</form>
	</center>
</body>
</html>