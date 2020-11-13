<%@page import="com.cs336.pkg.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
Welcome to CS336 Train Project

<br>

<%
if(request.getSession().getAttribute("user") != null) {
	//We are already logged in, so bypass this page
	String redirect = Constants.INDEX_PATH_REDIRECT_URL;
	System.out.println("[login.jsp] Already logged in; Redirecting to " + redirect);
	response.sendRedirect(redirect);
}

if(request.getParameter("failure") != null) {
	//1 means its a login failure, and 2 means it was a create new user failure
	if(request.getParameter("failure").equals("1")) {
		out.println("<b><p style=\"color:red\"> Failed to login. Please try again </p></b>");
	} else {
		%>
		<script type="text/javascript">
			alert("Failed to create new user. Make sure you have a unique username")
		</script>
	<%
	}
}
%>

<h2>Login</h2>
<form method="post" action="check_login.jsp">
	<table>
		<tr>
			<td>	
				<input type="text" name="username" placeholder="username">
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="password" placeholder="password">
			</td>
		</tr>
	</table>
	<input type="submit" value="Login!">
</form>

<br>

<form method="post" action="create_account.jsp">
	<input type="submit" name="createAccount" value="Create Account">
</form>


</body>
</html>