<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing Representative...</title>
</head>
<body>
<%
String SSN = request.getParameter("SSN");
String username = request.getParameter("username");
String password = request.getParameter("password");
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
boolean res = ApplicationDB.getInstance().EditEmployee(SSN, username, password, firstName, lastName);
if(res) {
	System.out.println("[edit_employee_logic.jsp] successfully created new employee - redirecting to admin index");
	response.sendRedirect(Constants.ADMIN_INDEX_REDIRECT_URL + "?employee_editied=true");
} else {
	//redirect back to create screen
	System.out.println("[edit_employee_logic.jsp] failed to create - redirecting to create screen");
	response.sendRedirect(Constants.EDIT_EMPLOYEE_UI_PAGE + "?failed=true&employee_ssn=" + SSN);
}
%>
</body>
</html>