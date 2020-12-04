<%@page import="com.cs336.pkg.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logging In User...</title>
</head>
<body>

<%
String type = request.getParameter(Constants.LOGIN_ACCOUNT_PARAMETER);
if(type == null) {
	//redirect to dispatcher
	System.out.println("[login_account_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
} else if(type.equals("customer")) {
	System.out.println("[login_account_logic.jsp::customer] attempting to log in");
	try {
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		boolean result = ApplicationDB.getInstance().LoginCustomer(request.getSession(), username, password);
		if(result) {
			System.out.println("[login_account_logic.jsp::customer] Successfully logged in user; Redirecting to customer index");
			response.sendRedirect(Constants.CUSTOMER_INDEX_REDIRECT_URL);
		} else {
			System.out.println("[login_account_logic.jsp::customer] login failed. Redirecting to login customer");
			response.sendRedirect(Constants.CUSTOMER_LOGIN_PAGE_REDIRECT_URL + "?failed=true");
		}
	} catch (Exception e) {
		System.out.println(e);
		System.out.println("[login_account_logic.jsp::customer] login failed. Redirecting to login customer");
		response.sendRedirect(Constants.CUSTOMER_LOGIN_PAGE_REDIRECT_URL + "?failed=true");
	}
} else if(type.equals("admin")) {
	System.out.println("[login_account_logic.jsp::admin] attempting to log in");
	try {
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		boolean result = ApplicationDB.getInstance().LoginAdmin(request.getSession(), username, password);
		if(result) {
			System.out.println("[login_account_logic.jsp::admin] Successfully logged in user; Redirecting to admin index");
			response.sendRedirect(Constants.ADMIN_INDEX_REDIRECT_URL);
		} else {
			System.out.println("[login_account_logic.jsp::admin] login failed. Redirecting to login admin");
			response.sendRedirect(Constants.ADMIN_LOGIN_PAGE_REDIRECT_URL + "?failed=true");
		}
	} catch (Exception e) {
		System.out.println(e);
		System.out.println("[login_account_logic.jsp::admin] login failed. Redirecting to login admin");
		response.sendRedirect(Constants.ADMIN_LOGIN_PAGE_REDIRECT_URL + "?failed=true");
	}
} else if(type.equals("employee")) {
	
} else {
	//redirect to dispatcher
	System.out.println("[login_account_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}
%>

</body>
</html>