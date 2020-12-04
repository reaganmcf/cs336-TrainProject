<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Dispatcher</title>
</head>
<body style="text-align: center">

<h2>Select Account Type</h2>
<form type="post" action="admin_login_ui.jsp">
	<div style="float: left; width: 33%; height: 200px; margin-top: 50px">
		<input value="Admin" type="submit" style="width: 90%; height: 90%"/>
	</div>
</form>
<form type="post" action="customer_login_ui.jsp">
	<div style="float: left; width: 33%; height: 200px; margin-top: 50px">
		<input value="Customer" type="submit" style="width: 90%; height: 90%"/>
	</div>
</form>
<form type="post" action="employee_login_ui.jsp">
	<div style="float: left; width: 33%; height: 200px; margin-top: 50px">
		<input value="Customer Representative" type="submit" style="width: 90%; height: 90%"/>
	</div>
</form>

</body>
</html>