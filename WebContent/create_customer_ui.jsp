<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Customer</title>
</head>
<body>

<h2>Create New Customer</h2>

<%
if(request.getParameter("failed") != null) {
%>
<p style="color:red"> Failed to create Customer. Please ensure all inputs are valid. </p>
<%
}
%>

<form method="post" action="create_account_logic.jsp?type=customer">
	<p>Username</p>
	<input type="text" name="username" placeholder="super_duper_1234"/>
	<br/>
	<p>Password</p>
	<input type="text" name="password" placeholder="noonewillguessthis"/>
	<br/>
	<p>Email</p>
	<input type="text" name="email" placeholder="john.doe@gmail.com"/>
	<br/>
	<p>First Name</p>
	<input type="text" name="firstName" placeholder="John"/>
	<br/>
	<p>Last Name</p>
	<input type="text" name="lastName" placeholder="Doe"/>
	<br/>
	<br/>
	<br/>
	<input type="submit" value="Create New Representative"/>
</form>

</body>
</html>