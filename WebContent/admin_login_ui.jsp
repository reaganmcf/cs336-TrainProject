<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include.jsp" />
<meta charset="ISO-8859-1">
<title>Admin Login</title>
</head>
<body style="padding: 50px">

<h2>Admin Login</h2>

<div>
<%
if(request.getParameter("failed") != null) {
%>
<div class="alert alert-danger"> Failed Login. Please try again. </div>
<%
}
%>
</div>
<form method="post" action="login_account_logic.jsp?type=admin">
	<div class="form-group">
		<input type="text" name="username" placeholder="username" required>	
	</div>
	<div class="form-group">
		<input type="text" name="password" placeholder="password" required>
	</div>
	<input type="submit" value="Login!" class="btn btn-primary">

</form>

<br>

</body>
</html>