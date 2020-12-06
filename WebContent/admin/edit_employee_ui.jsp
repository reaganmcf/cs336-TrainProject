<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="./../include.jsp" />
<title>Edit Customer Representative</title>
</head>
<body style="padding: 50px">

<%
String selected_ssn = request.getParameter("employee_ssn");
if(selected_ssn == null) {
	System.out.println("[edit_employee_ui.jsp] missing param, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

ArrayList<Employee> employees = (ArrayList<Employee>) request.getSession().getAttribute(Constants.HTTP_SESSION_EMPLOYEE_LIST);
Employee curr_employee = null;
for(Employee e : employees) {
	if(e.getSSN().equals(selected_ssn)) {
		curr_employee = e;
		break;
	}
}

%>


<!--  ALERT MESSAGES FROM REDIRECTED PAGS  -->
<%
if(request.getParameter("failed") != null) {		
	%><div class="alert alert-danger">Failed to delete Representative. Please try again</div><%
}
%>


<h1>Edit Customer Representative</h1>
<div class="card" style="margin:20px; width: 30%">
	<div class="card-header">New Representative</div>
	<div style="padding: 10px">
		<form method="post" action="edit_employee_logic.jsp">
			<div class="form-group">
				<label>SSN (locked)</label><br/>
				<input class="form-control" type="text" name="SSN" value="<%out.print(curr_employee.getSSN());%>" readonly/>
			</div>
			<div class="form-group">
				<label>Username</label><br/>
				<input class="form-control" type="text" name="username" value="<%out.print(curr_employee.getUsername());%>"/>
			</div>
			<div class="form-group">
				<label>Password</label><br/>
				<input class="form-control" type="text" name="password" value="<%out.print(curr_employee.getPassword());%>"/>
			</div>
			<div class="form-group">
				<label>First Name</label><br/>
				<input class="form-control" type="text" name="firstName" value="<%out.print(curr_employee.getFirstName());%>"/>
			</div>
			<div class="form-group">
				<label>Last Name</label><br/>
				<input class="form-control" type="text" name="lastName" value="<%out.print(curr_employee.getLastName());%>"/>
			</div>
			<br/>
			<input class="btn btn-primary" type="submit" value="Edit Representative"/>
		</form>
	</div>
</div>
<br/>
<form method="post" action="delete_employee_logic.jsp?SSN=<%out.print(selected_ssn);%>">
	<input class="btn btn-danger" type="submit" value="Delete Representative"/>
</form>

</body>
</html>