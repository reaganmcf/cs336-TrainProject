<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Representative Login</title>
</head>
<body>

<h2>Representative Login</h2>

<%if(request.getParameter("failed") != null) {%>
<p style="color:red"> Failed Login. Please try again. </p>
<%}%>


<%if(request.getParameter("just_created") != null) {%>
<p style="color:green"> Successfully created user. You may now log in.</p>
<%}%>


<form method="post" action="login_account_logic.jsp?type=employee">
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

<form method="post" action="create_employee_ui.jsp?type=employee">
	<input type="submit" name="createAccount" value="Create Customer Representative">
</form>

</body>
</html>