<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include.jsp" />
<meta charset="ISO-8859-1">
<title>Customer Login</title>
</head>
<body style="padding: 50px">

<h2>Customer Login</h2>
<div>
<%if(request.getParameter("failed") != null) {%>
<div class="alert alert-danger"> Failed Login. Please try again. </div>
<%}%>

<%if(request.getParameter("just_created") != null) {%>
<div class="alert alert-success" > Successfully created Customer. You may now log in.</div>
<%}%>

</div>

<form method="post" action="login_account_logic.jsp?type=customer">
	<div class="form-group">
		<input type="text" name="username" placeholder="username" required>
	</div>
		
	<div class="form-group">
		<input type="text" name="password" placeholder="password" required>
	</div>
	<input type="submit" value="Login!" class="btn btn-primary">
</form>

<br>

<form method="post" action="create_customer_ui.jsp">
	<input type="submit" name="createAccount" value="Create Customer" class="btn btn-primary">
</form>

</body>
</html>