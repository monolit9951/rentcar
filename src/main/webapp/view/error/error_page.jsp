<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/directive/page.jspf"%>
<%@ include file="/view/directive/taglib.jspf"%>

<head>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/style.css"
	type="text/css">
</head>
<html>

<c:set var="title" value="Error" scope="page" />

<body>

	<table id="main-container">
		<tr>
			<td class="content">
				<%-- CONTENT --%>

				<h2 align="center" class="error">Похоже, что произошла ошибка</h2> <c:set
					var="code"
					value="${requestScope['javax.servlet.error.status_code']}" /> <c:set
					var="message"
					value="${requestScope['javax.servlet.error.message']}" /> <c:set
					var="exception"
					value="${requestScope['javax.servlet.error.exception']}" /> <c:if
					test="${not empty code}">
					<h3>Error code: ${code}</h3>
				</c:if> <c:if test="${not empty message}">
					<h3>${message}</h3>
				</c:if> <%-- if we get this page using forward --%> <c:if
					test="${not empty errorMessage}">
					<h3 align="center">${errorMessage}</h3>
				</c:if> <%-- CONTENT --%>
			</td>
		</tr>
		<tr align="center">
			<td><img
				src="${pageContext.servletContext.contextPath}/img/error.jpg" alt="">
		</tr>


		<%@include file="/view/error/footer.jspf"%>

	</table>
</body>
</html>