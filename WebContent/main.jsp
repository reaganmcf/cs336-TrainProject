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
CustomerMakes user = (CustomerMakes) request.getSession().getAttribute(Constants.HTTP_SESSION_USER_KEY);
// check if we are logged in just to be extra secure
if(user == null) {
	System.out.println("[main.jsp] Unauthenticated user - redirecting to login");
	response.sendRedirect(Constants.LOGIN_PAGE_REDIRECT_URL);
} else {

//from here on out we know we are logged in
%>
<h1>Main Page</h1>
<h6>Welcome back, <%user.getFirstName();%></h6>


<form method="post" action="logout.jsp">
	<input type="submit" name="logout" value="Log Out">
</form>
<%}%>
</body>
</html>