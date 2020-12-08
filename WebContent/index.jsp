<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<jsp:include page="include.jsp" />
<meta charset="ISO-8859-1">
<title>Login Dispatcher</title>
</head>
<body style="text-align: center; padding: 50px">
<h1>Welcome to CS336 Train Project</h1>
<p><i>Reagan McFarland, Toshanraju Vysaraju, Alay Shah, Kuber Sood, Zuhaib Azam</i></p> 
<br/><br/>
<h2>Select Account Type</h2>
<form type="post" action="admin_login_ui.jsp">
	<div style="margin-top: 50px;">
		<input value="Admin" type="submit" class="btn btn-primary"/>
	</div>
</form>
<br/>
<div>
	<form type="post" action="customer_login_ui.jsp">
		<div style="margin-top: 50px">
			<input value="Customer" type="submit" class="btn btn-primary"/>
		</div>
	</form>
</div>
<br/>
<form type="post" action="employee_login_ui.jsp">
	<div style="margin-top: 50px">
		<input value="Customer Representative" type="submit" class="btn btn-primary"/>
	</div>
</form>

</body>
</html>