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

<!--  ALERT MESSAGES FROM REDIRECTED PAGES  -->
<%
if(request.getParameter("deleted_employee") != null) {
	if(request.getParameter("deleted_employee").equals("false")) {
		%><p style="color:red">Failed to delete Representative. Please try again</p><%
	} else {
		%><p style="color:green">Successfully deleted Representative</p><%
	}
} else if(request.getParameter("employee_editied") != null) {
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

ArrayList<TrainLine> train_line_list = ApplicationDB.getInstance().GetTrainLines();
request.getSession().setAttribute(Constants.HTTP_SESSION_TRAIN_LINE_LIST, train_line_list);

ArrayList<Customer> customer_list = ApplicationDB.getInstance().GetCustomers();
request.getSession().setAttribute(Constants.HTTP_SESSION_CUSTOMER_LIST, customer_list);
out.print(admin.getUsername());
%>



<!--  EDIT EMPLOYEE  --> 
<form method="post" action="edit_employee_ui.jsp">
<h4> Edit or Delete Employee</h4>
	<select name="employee_ssn" required>
	<%
    for(Employee e : employees) {
    %><option value="<% out.print(e.getSSN()); %>"><% out.print(e.getSSN() + " - " + e.getFirstName() + " " + e.getLastName()); %></option>
    
    <%}%>
    </select><br/><br/>
	<input type="submit" value="Edit or Delete Employee"/>
</form>





<!-- LIST RESERVATIONS BY USERNAME -->
<form method="post" action="list_reservations_logic.jsp">
	<h4>List Reservations By Username</h4>
	<select name="customer_username" required>
	<%
    for(Customer c : customer_list) {
    %><option value="<% out.print(c.getUsername()); %>"><% out.print(c.getUsername());%></option>
    
    <%}%>
    </select><br/><br/>
	<input type="submit" value="List Reservations"/>
</form>

<!-- LIST RESERVATIONS BY Train Line -->
<form method="post" action="list_reservations_logic.jsp">
	<h4>List Reservations By Train Line</h4>
	<select name="transit_line" required>
	<%
	for(TrainLine t : train_line_list) {
    %><option value="<% out.print(t.getLineName()); %>"><% out.print(t.getLineName());%></option>
    <%}%>
    </select><br/><br/>
	<input type="submit" value="List Reservations"/>
</form>







<!-- LIST REVENUE BY USERNAME -->
<form method="post" action="list_revenue_logic.jsp?customer_username">
	<h4>List Revenue By Username</h4>
	<input type="submit" value="List Revenue"/>
</form>

<!-- LIST REVENUE BY Train Line -->
<form method="post" action="list_revenue_logic.jsp?transit_line">
	<h4>List Revenue By Train Line</h4>
	<input type="submit" value="List Revenue"/>
</form>











<!--   Sales based on a given month   -->
<form method="post" action="obtain_monthly_sales_logic.jsp">
	<h4>Sales for a given year and month</h4>
	<p>Year</p>
	<input type="text" name="year" maxlength=4 placeholder="2020" required/>
	<p>Month (1-12)</p>
	<input type="text" name="month" maxlength=2 placeholder="12" required/>
	<br/><br/>
	<input type="submit" value="View Sales"/>
</form>




<!--  LOGOUT  -->
<form method="post" action="./../logout_logic.jsp">
	<h4>Logout</h4>
	<input type="submit" name="logout" value="Log Out">
</form>

</body>
</html>