<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer Representative</title>
</head>
<body>

<%
ArrayList<Employee> employees = (ArrayList<Employee>) request.getSession().getAttribute(Constants.HTTP_SESSION_EMPLOYEE_LIST);
if(employees == null) {
	System.out.println("[edit_employee_ui.jsp] must not be an admin, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

String selected_ssn = request.getParameter("employee_ssn");

Employee curr_employee = null;
for(Employee e : employees) {
	if(e.getSSN().equals(selected_ssn)) {
		curr_employee = e;
		break;
	}
}

//curr_employee can't be null here because its a post param
out.println(curr_employee);

%>


<!--  ALERT MESSAGES FROM REDIRECTED PAGS  -->
<%
if(request.getParameter("failed") != null) {		
	%><p style="color:red">Failed to delete Representative. Please try again</p><%
}
%>


<h1>Edit Customer Representative</h1>
<form method="post" action="edit_employee_logic.jsp">
	<p>SSN (locked)</p>
	<input type="text" name="SSN" value="<%out.print(curr_employee.getSSN());%>" readonly/>
	<br/>
	<p>Username</p>
	<input type="text" name="username" value="<%out.print(curr_employee.getUsername());%>"/>
	<br/>
	<p>Password</p>
	<input type="text" name="password" value="<%out.print(curr_employee.getPassword());%>"/>
	<br/>
	<p>First Name</p>
	<input type="text" name="firstName" value="<%out.print(curr_employee.getFirstName());%>"/>
	<br/>
	<p>Last Name</p>
	<input type="text" name="lastName" value="<%out.print(curr_employee.getLastName());%>"/>
	<br/>
	<br/>
	<br/>
	<input type="submit" value="Edit Representative"/>
</form> 
<br/><br/>
<form method="post" action="delete_employee_logic.jsp?SSN=<%out.print(selected_ssn);%>">
	<input type="submit" value="Delete Representative"/>
</form>

</body>
</html>