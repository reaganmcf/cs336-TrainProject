<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Create New Account</h1>
<form method="post" action="check_login.jsp?create=1">
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