<%@page import="com.cs336.pkg.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Main Page</title>
</head>
<body>

<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.HTTP_SESSION_ADMIN);

if(admin == null) {
	//shouldn't be here. Redirect
	System.out.println("[admin_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

out.print(admin.getUsername());
%>

<form method="post" action="logout_logic.jsp">
	<input type="submit" name="logout" value="Log Out">
</form>

</body>
</html>