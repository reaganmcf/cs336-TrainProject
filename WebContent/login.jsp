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
</body>
</html>