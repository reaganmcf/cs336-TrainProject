<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Representative</title>
</head>
<body>

<h2>Create New Representative</h2>

<form method="post" action="./../create_account_logic.jsp?type=employee">
	<p>SSN</p>
	<input type="text" name="SSN" placeholder="12-345-6789"/>
	<br/>
	<p>Username</p>
	<input type="text" name="username" placeholder="super_duper_1234"/>
	<br/>
	<p>Password</p>
	<input type="text" name="password" placeholder="noonewillguessthis"/>
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