<%@page import="javax.servlet.jsp.jstl.core.Config"%>
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
<body>
	<form action="changeLocale.jsp" method="post">
		<fmt:message key="settings_jsp.label.set_locale" />
		: <select name="locale">
			<c:forEach items="${applicationScope.locales}" var="locale">
				<c:set var="selected"
					value="${locale.key == currentLocale ? 'selected' : '' }" />
				<option value="${locale.key}" ${selected}>${locale.value}</option>
			</c:forEach>
		</select> <input type="submit"
			value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">

	</form>
	<a href="index.jsp"><fmt:message
			key="settings_jsp.link.back_to_main_page"></fmt:message></a>
</body>
</html>