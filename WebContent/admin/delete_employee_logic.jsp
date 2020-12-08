<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%
String SSN = request.getParameter("SSN");
if(SSN == null || SSN.length() != 11) {
	//redirect to dispatcher
	System.out.println("[delete_employee_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deleting Employee...</title>
</head>
<body>
<%
boolean res = ApplicationDB.getInstance().DeleteEmployee(SSN);
if(res == true) {
	System.out.println("[delete_employee_logic.jsp] successfully deleted " + SSN);
	response.sendRedirect(Constants.ADMIN_INDEX_REDIRECT_URL + "?deleted_employee=true");
} else {
	System.out.println("[delete_employee_logic.jsp] failed to delete" + SSN);
	response.sendRedirect(Constants.ADMIN_INDEX_REDIRECT_URL + "?deleted_employee=false");
}
%>

</body>
</html>