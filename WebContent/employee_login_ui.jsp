<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="include.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Representative Login</title>
</head>
<body style="padding: 50px">

<h2>Representative Login</h2>
<div>
<%if(request.getParameter("failed") != null) {%>
<div class="alert alert-danger"> Failed Login. Please try again. </div>
<%}%>
</div>
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Login</div>
	<div style="padding: 10px">
		<form method="post" action="login_account_logic.jsp?type=employee">
			<div class="form-group">
				<label>Username</label><br/>
				<input class="form-control" type="text" name="username" placeholder="username" required>
			</div>
			<div class="form-group">
				<label>Password</label><br/>
				<input class="form-control" type="text" name="password" placeholder="password" required>
			</div>
			
			<input type="submit" value="Login!" class="btn btn-primary">
		</form>
	</div>
</div>

<br>

</body>
</html>