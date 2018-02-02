<%@page import="javax.servlet.jsp.jstl.core.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/view/directive/page.jspf"%>
<%@ include file="/view/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ukrainian Rentcar Service </title>
</head>
<body>
	<%@ include file="/view/menu.jspf"%>
</body>
<center>
<a href="${pageContext.servletContext.contextPath}/view/settings.jsp"><fmt:message
		key="settings" /></a>
		</center>
</html>