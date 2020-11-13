<%@page import="com.cs336.pkg.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checking Login</title>
</head>
<body>
	<%
	try {
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		boolean result = ApplicationDB.getInstance().CheckLogin(request.getSession(),username,password);		
	%>
	<%
	if(result) {
		out.println("Successfully logged in");
		CustomerMakes user = (CustomerMakes)request.getSession().getAttribute(Constants.HTTP_SESSION_USER_KEY);
		out.println("<table>");
		out.println(Constants.USER_TABLE_HEADERS);
		out.println(user.toTableString());
		out.println("</table>");
	} else {
		out.println("Failed to log in");
	}
	
	%>
	
	<%} catch(Exception e) {
		out.print(e);
	}
	%>
</body>
</html>