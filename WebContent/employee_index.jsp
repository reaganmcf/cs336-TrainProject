<%@page import="com.cs336.pkg.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Main Page</title>
</head>
<body>

<%
Employee employee = (Employee) request.getSession().getAttribute(Constants.HTTP_SESSION_EMPLOYEE);

if(employee == null) {
	//shouldn't be here. Redirect
	System.out.println("[employee_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

out.print(employee.getUsername());
%>

<form method="post" action="logout_logic.jsp">
	<input type="submit" name="logout" value="Log Out">
</form>

</body>
</html>