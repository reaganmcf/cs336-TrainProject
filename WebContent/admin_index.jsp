<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Main Page</title>
</head>
<body>

<!--  ALERT MESSAGES FROM REDIRECTED PAGS  -->
<%
if(request.getParameter("deleted_employee") != null) {
	if(request.getParameter("deleted_employee").equals("false")) {
		%><p style="color:red">Failed to delete Representative. Please try again</p><%
	} else {
		%><p style="color:green">Successfully deleted Representative</p><%
	}
} else if(request.getParameter("edited_employee") != null) {
	%><p style="color:green">Successfully Edited Employee</p><%
}
%>

<%
Admin admin = (Admin) request.getSession().getAttribute(Constants.HTTP_SESSION_ADMIN);

if(admin == null) {
	//shouldn't be here. Redirect
	System.out.println("[admin_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

//Now that we are authenticated, start pulling data we need
ArrayList<Employee> employees = ApplicationDB.getInstance().getEmployees();
request.getSession().setAttribute(Constants.HTTP_SESSION_EMPLOYEE_LIST, employees);

for(Employee e : employees) {
	out.println(e);
}

out.print(admin.getUsername());
%>



<!--  EDIT EMPLOYEE  --> 
<form method="post" action="edit_employee_ui.jsp">
<h4> Edit or Delete Employee</h4>
	<select name="employee_ssn">
	<%
    for(Employee e : employees) {
    %><option value="<% out.print(e.getSSN()); %>"><% out.print(e.getSSN() + " - " + e.getFirstName() + " " + e.getLastName()); %></option>
    
    <%}%>
    </select><br/><br/>
	<input type="submit" value="Edit or Delete Employee"/>
</form>




<!--  LOGOUT  -->
<form method="post" action="logout_logic.jsp">
	<h4>Logout</h4>
	<input type="submit" name="logout" value="Log Out">
</form>

</body>
</html>