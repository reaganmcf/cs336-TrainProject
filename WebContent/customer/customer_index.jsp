<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Main Page</title>
</head>
<body>

<%
Customer customer = (Customer) request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER);

if(customer == null) {
	//shouldn't be here. Redirect
	System.out.println("[customer_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

out.print(customer.getEmail());


//load some data
ArrayList<Station> stations = ApplicationDB.getInstance().GetStations();
request.getSession().setAttribute(Constants.HTTP_SESSION_STATION_LIST, stations);

ArrayList<TrainLine> trainlines = ApplicationDB.getInstance().GetTrainLines();
request.getSession().setAttribute(Constants.HTTP_SESSION_TRAIN_LINE_LIST, trainlines);
%>

<!--  ALERT MESSAGES FROM REDIRECTED PAGES  -->
<%
if(request.getParameter("failed_send") != null) {
	%><p style="color:red">Failed to send question. Please try again</p><%
} else if(request.getParameter("success_send") != null) {
	%><p style="color:green">Successfully sent question</p><%
}
%>

<!--  BROWSE ALL QUESTIONS  -->
<form method="post" action="./../qa/browse.jsp">
	<h6>Browse All Questions</h6>
	<input type="submit" name="sub" value="Browse All Questions"/>
</form>

<!--  BROWSE ALL QUESTIONS BY KEYWORD  -->
<form method="post" action="./../qa/browse.jsp">
	<h6>Browse Questions by Keyword</h6>
	<input type="text" name="keyword" required/>
	<input type="submit" value="Browse All Questions"/>
</form>

<!--  SEND QUESTION TO CUSTOMER SERVICE  -->
<form method="post" action="./../qa/send_question_logic.jsp">
	<h6>Submit a new question to Customer Service</h6>
	<input type="text" name="question" required/>
	<input type="submit" value="Submit New Question"/>	
</form>



<!--  SEARCH TRAIN SCHEDULES  -->
<form method="post" action="./search_schedules_logic.jsp">
	<h4>Search for Schedules</h4>
	<p>Origin</p>
	<select name="origin" required>
		<%
		for(Station s : stations) {
			%><option value="<% out.print(s.getName());%>"><%out.print(s.getName());%></option>
		<%}%>
	</select>
	
	<p>Destination</p>
	<select name="destination" required>
		 <%
		for(Station s : stations) {
			%><option value="<% out.print(s.getName());%>"><%out.print(s.getName());%></option>
		<%}%> 
	</select>
	
	<p>Date Year</p>
	<input type="text" name="date_year" minlength=4 maxlength=4 required/>
	
	<p>Date Month (1-12)</p>
	<input type="text" name="date_month" minlength=1 maxlength=2 required/>
	
	<p>Date Day (1-31)</p>
	<input type="text" name="date_day" minlength=1 maxlength=2 required/>
	
	<br/>
	<br/>
	
	<input type="submit" value="Search for Schedules"/>
</form>



<form method="post" action="./../logout_logic.jsp">
	<h6>Logout</h6>
	<input type="submit" name="logout" value="Log Out">
</form>

</body>
</html>