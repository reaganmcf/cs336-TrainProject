<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include.jsp" />
<meta charset="ISO-8859-1">
<title>Create New Customer</title>
</head>
<body style="padding: 50px">

<h2>Create New Customer</h2>

<div>
<%if(request.getParameter("failed") != null) {%>
<div class="alert alert-danger"> Failed to create Customer. Please ensure all inputs are valid. </div>
<%}%>
</div>

<form method="post" action="create_account_logic.jsp?type=customer">
	<div class="form-group">
		<label for="uname">Username</label><br/>
		<input type="text" name="username" id="uname"  placeholder="super_duper_1234" required/>
	</div>
	<div class="form-group">
		<label for="pass">Password</label><br/>
		<input type="text" name="password" placeholder="noonewillguessthis" required/>
	</div>
	<div class="form-group">
		<label for="email">Email</label><br/>
		<input id="email" type="text" name="email" placeholder="john.doe@gmail.com" required/>
	</div>
	<div class="form-group">
		<label for="firstName">First Name</label><br/>
		<input id="firstName" type="text" name="firstName" placeholder="John" required/>
	</div>
	<div class="form-group">
		<label for="lastName">Last Name</label><br/>
		<input id="lastName" type="text" name="lastName" placeholder="Doe" required/>
	</div>
	<br/>
	<br/>
	<input class="btn btn-primary" type="submit" value="Create New Representative"/>
</form>

</body>
</html>