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

<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">New Customer</div>
	<div style="padding: 10px">
		<form method="post" action="create_account_logic.jsp?type=customer">
			<div class="form-group">
				<label for="uname">Username</label><br/>
				<input class="form-control" type="text" name="username" id="uname"  placeholder="super_duper_1234" required/>
			</div>
			<div class="form-group">
				<label for="pass">Password</label><br/>
				<input class="form-control" type="text" name="password" placeholder="noonewillguessthis" required/>
			</div>
			<div class="form-group">
				<label for="email">Email</label><br/>
				<input class="form-control"class="form-control"  id="email" type="text" name="email" placeholder="john.doe@gmail.com" required/>
			</div>
			<div class="form-group">
				<label for="firstName">First Name</label><br/>
				<input class="form-control" id="firstName" type="text" name="firstName" placeholder="John" required/>
			</div>
			<div class="form-group">
				<label for="lastName">Last Name</label><br/>
				<input class="form-control" id="lastName" type="text" name="lastName" placeholder="Doe" required/>
			</div>
			
			<input class="btn btn-primary" type="submit" value="Create New Representative"/>
		</form>
	</div>
</div>
</body>
</html>