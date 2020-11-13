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
	if(request.getParameter("create") != null) {
		System.out.println("[check_login.jsp] creating user");
		try {
			String username = request.getParameter("username").toString();
			String password = request.getParameter("password").toString();
			boolean result = ApplicationDB.getInstance().CreateAccount(request.getSession(),username,password);
			if(result) {
				String redirect = Constants.MAIN_PAGE_REDIRECT_URL;
				System.out.println("[check_login.jsp] Successfully created user; Redirecting to " + redirect);
				response.sendRedirect(redirect);
			} else {
				String redirect = Constants.LOGIN_PAGE_REDIRECT_URL + "?failure=2";
				System.out.println("[check_login.jsp] Failed to create user; Redirecting to " + redirect);
				response.sendRedirect(redirect);	
			}
		} catch(Exception e) {
			out.print(e);
		}
	} else {
		System.out.println("[check_login.jsp] loggin in user");
		try {
			String username = request.getParameter("username").toString();
			String password = request.getParameter("password").toString();
			boolean result = ApplicationDB.getInstance().CheckLogin(request.getSession(),username,password);		
		%>
		<%
		if(result) {
			//CustomerMakes user = (CustomerMakes)request.getSession().getAttribute(Constants.HTTP_SESSION_USER_KEY);
			//out.println("<table>");
			//out.println(Constants.USER_TABLE_HEADERS);
			//out.println(user.toTableString());
			//out.println("</table>");
			String redirect = Constants.MAIN_PAGE_REDIRECT_URL;
			System.out.println("[check_login.jsp] Successfully logged in; Redirecting to " + redirect);
			response.sendRedirect(redirect);
		} else {

			String redirect = Constants.LOGIN_PAGE_REDIRECT_URL + "?failure=1";
			System.out.println("[check_login.jsp] Failed to log in; Redirecting to " + redirect);
			response.sendRedirect(redirect);
		}
		
		%>
		
		<%
		} catch(Exception e) {
			out.print(e);
		}
	}
	%>
</body>
</html>