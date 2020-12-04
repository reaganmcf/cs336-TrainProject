<%@page import="com.cs336.pkg.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creating Account...</title>
</head>
<body>

<%
String type = request.getParameter(Constants.CREATE_ACCOUNT_PARAMETER);
if(type == null) {
	//redirect to dispatcher
	System.out.println("[create_account_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
} else if(type.equals("customer")) {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	boolean res = ApplicationDB.getInstance().CreateCustomer(username, password, email, firstName, lastName);
	if(res) {
		System.out.println("[create_account_logic.jsp::customer] successfully created new customer - redirecting to customer login");
		response.sendRedirect(Constants.CUSTOMER_LOGIN_PAGE_REDIRECT_URL + "?just_created=true");
	} else {
		//redirect back to create screen
		System.out.println("[create_account_logic.jsp::customer] failed to create - redirecting to create screen");
		response.sendRedirect(Constants.CUSTOMER_CREATE_PAGE_REDIRECT_URL + "?failed=true");
	}
} else if(type.equals("employee")) {
	String SSN = request.getParameter("SSN");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	boolean res = ApplicationDB.getInstance().CreateEmployee(SSN, username, password, firstName, lastName);
	if(res) {
		System.out.println("[create_account_logic.jsp::employee] successfully created new employee - redirecting to employee login");
		response.sendRedirect(Constants.ADMIN_INDEX_REDIRECT_URL + "?success_create_employee=true");
	} else {
		//redirect back to create screen
		System.out.println("[create_account_logic.jsp::employee] failed to create - redirecting to create screen");
		response.sendRedirect(Constants.ADMIN_INDEX_REDIRECT_URL + "?failed_create_employee=true");
	}
} else {
	//redirect to dispatcher
	System.out.println("[create_account_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}
%>

</body>
</html>