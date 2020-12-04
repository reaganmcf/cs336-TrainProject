<%@page import="com.cs336.pkg.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
//invalidate session and redirect to main dispatch
request.getSession().invalidate();
response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
%>

</body>
</html>