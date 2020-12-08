<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
Admin admin = (Admin) request.getSession().getAttribute(Constants.HTTP_SESSION_ADMIN);
if(admin == null) {
	//shouldn't be here. Redirect
	System.out.println("[admin_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Admin Main Page</title>
</head>
<body style="padding: 50px">

<!--  ALERT MESSAGES FROM REDIRECTED PAGES  -->
<%
if(request.getParameter("deleted_employee") != null) {
	if(request.getParameter("deleted_employee").equals("false")) {
		%><div class="alert alert-danger">Failed to delete Representative. Please try again</div><%
	} else {
		%><div class="alert alert-success">Successfully deleted Representative</div><%
	}
} else if(request.getParameter("employee_editied") != null) {
	%><div class="alert alert-success">Successfully Edited Employee</div><%
} else if(request.getParameter("failed_create_employee") != null) {
	%><div class="alert alert-danger">Failed to create Representative. Please try again</div><%
} else if(request.getParameter("success_create_employee") != null) {
	%><div class="alert alert-success">Successfully created Representative</div><%
}
%>


<%
//Now that we are authenticated, start pulling data we need
ArrayList<Employee> employees = ApplicationDB.getInstance().getEmployees();
request.getSession().setAttribute(Constants.HTTP_SESSION_EMPLOYEE_LIST, employees);

ArrayList<TrainLine> train_line_list = ApplicationDB.getInstance().GetTrainLines();
request.getSession().setAttribute(Constants.HTTP_SESSION_TRAIN_LINE_LIST, train_line_list);

ArrayList<Customer> customer_list = ApplicationDB.getInstance().GetCustomers();
request.getSession().setAttribute(Constants.HTTP_SESSION_CUSTOMER_LIST, customer_list);

ArrayList<String> best_customer = ApplicationDB.getInstance().BestCustomer();

ArrayList<ArrayList<String>> top_active_lines = ApplicationDB.getInstance().getTopActiveLines();
%>

<h1>Admin Menu Page</h1>
<p>Welcome Back, <% out.print(admin.getUsername());%></p>

<!--  ADD EMPLOYEE  -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Create New Customer Representative</div>
	<div style="padding: 10px">
		<form method="post" action="create_employee_ui.jsp?type=employee">
			<input class="btn btn-primary" type="submit" name="createAccount" value="Create Customer Representative">
		
		</form>
	</div>
</div>

<!--  EDIT EMPLOYEE  --> 
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Edit or Delete Employee</div>
	<div style="padding: 10px">
		<form method="post" action="edit_employee_ui.jsp">
			<label>Employee: (SSN - First Name, Last Name)</labeL>
			<select class="form-control" name="employee_ssn" required>
			<%
		    for(Employee e : employees) {
		    %><option value="<% out.print(e.getSSN()); %>"><% out.print(e.getSSN() + " - " + e.getFirstName() + ", " + e.getLastName()); %></option>
		    
		    <%}%>
		    </select><br/>
			<input class="btn btn-primary" type="submit" value="Edit or Delete Employee"/>
		</form>
	</div>
</div>




<!-- LIST RESERVATIONS BY USERNAME -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">List Reservations</div>
	<div style="padding: 10px">
		<form method="post" action="list_reservations_logic.jsp">
			<label>By Username: </label>
			<select class="form-control" name="customer_username" required>
			<%
		    for(Customer c : customer_list) {
		    %><option value="<% out.print(c.getUsername()); %>"><% out.print(c.getUsername());%></option>
		    
		    <%}%>
		    </select><br/>
			<input class="btn btn-primary" type="submit" value="List By Username"/>
		</form>
	</div>
	
	<div style="padding: 10px">
		<form method="post" action="list_reservations_logic.jsp">
			<label>Train Line:</label>
			<select class="form-control"  name="transit_line" required>
			<%
			for(TrainLine t : train_line_list) {
		    %><option value="<% out.print(t.getLineName()); %>"><% out.print(t.getLineName());%></option>
		    <%}%>
		    </select><br/>
			<input class="btn btn-primary" type="submit" value="List By Train Line"/>
		</form>
	</div>
</div>




<!-- LIST REVENUE -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">List Revenues</div>
	<div style="padding: 10px">
		<form method="post" action="list_revenue_logic.jsp?customer_username">
			<input class="btn btn-primary" type="submit" value="By Customers"/>
		</form>
	</div>
	<div style="padding: 10px">
		<form method="post" action="list_revenue_logic.jsp?transit_line">
			<input class="btn btn-primary" type="submit" value="By Train Line"/>
		</form>
	</div>
	
</div>







<!--   Sales based on a given month   -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Sales based on year and month</div>
	<div style="padding: 10px">
		<form method="post" action="obtain_monthly_sales_logic.jsp">
			<label>Year</label><br/>
			<input class="form-control" type="text" name="year" maxlength=4 placeholder="2020" required/><br/>
			<label>Month (1-12)</label><br/>
			<input class="form-control" type="text" name="month" maxlength=2 placeholder="12" required/>
			<br/>
			<input class="btn btn-primary" type="submit" value="View Sales"/>
		</form>
	</div>
</div>



<!--   BEST CUSTOMER   -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Best Customer</div>
	<div style="padding: 10px">
		<%out.print(best_customer.get(0) + " : $" + best_customer.get(1)); %>
	</div>
</div>

<!--   Top 5 Most Active Transit Lines   -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Top 5 Most Active Transit Lines</div>
	<div style="padding: 10px">
		<table class="table table-bordered table-striped">
		<th>Line Name</th><th>Number of Reservations</th>
		<%
		for(ArrayList<String> row : top_active_lines) {
			String t = "";
			for(String s : row) {
				t += "<td>" + s + "</td>";
			}
			out.print("<tr>" + t + "</tr>");
		}%>
		</table>
	</div>
</div>
		



<!--  LOGOUT  -->
<form method="post" action="./../logout_logic.jsp">
	<input class="btn btn-danger" type="submit" name="logout" value="Log Out">
</form>

</body>
</html>