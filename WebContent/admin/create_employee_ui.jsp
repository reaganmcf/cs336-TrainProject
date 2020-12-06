<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Create New Representative</title>
</head>
<body style="padding: 50px">

<h2>Create New Representative</h2>

<div class="card" style="margin:20px; width: 30%">
	<div class="card-header">New Representative</div>
	<div style="padding: 10px">
		<form method="post" action="./../create_account_logic.jsp?type=employee">
			<div class="form-group">
				<label>SSN</label><br/>
				<input class="form-control" type="text" name="SSN" placeholder="12-345-6789" required/>
			</div>
			<div class="form-group">
				<label>Username</label><br/>
				<input class="form-control" type="text" name="username" placeholder="super_duper_1234" required/>
			</div>
			<div class="form-group">
				<label>Password</label><br/>
				<input class="form-control"  type="text" name="password" placeholder="noonewillguessthis" required/>
			</div>
			<div class="form-group">
				<label>First Name</label><br/>
				<input class="form-control"  type="text" name="firstName" placeholder="John" required/>
			</div>
			<div class="form-group">
				<label>Last Name</label><br/>
				<input class="form-control"  type="text" name="lastName" placeholder="Doe" required/>
			</div>
			<br/>
			<input class="btn btn-primary" type="submit" value="Create New Representative"/>
		</form>
	</div>
</div>

</body>
</html>