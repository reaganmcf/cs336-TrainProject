<%@page import="com.cs336.pkg.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>

<%
request.getSession().setAttribute(Constants.HTTP_SESSION_USER_KEY, null);
response.sendRedirect(Constants.LOGIN_PAGE_REDIRECT_URL);
%>

</body>
</html>