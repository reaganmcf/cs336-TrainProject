<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.Date" %>

<%
Customer customer = (Customer) request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER);

if(customer == null) {
	//shouldn't be here. Redirect
	System.out.println("[customer_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
} else {


//load some data
ArrayList<Station> stations = ApplicationDB.getInstance().GetStations();
request.getSession().setAttribute(Constants.HTTP_SESSION_STATION_LIST, stations);

ArrayList<TrainLine> trainlines = ApplicationDB.getInstance().GetTrainLines();
request.getSession().setAttribute(Constants.HTTP_SESSION_TRAIN_LINE_LIST, trainlines);

ArrayList<Reservation> currentReservations = ApplicationDB.getInstance().GetReservationsByUsername(customer.getUsername());
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Customer Main Page</title>
</head>
<body style="padding: 50px">

<!--  ALERT MESSAGES FROM REDIRECTED PAGES  -->
<div>
<%
if(request.getParameter("failed_send") != null) {
	%><div class="alert alert-danger">Failed to send question. Please try again</div><%
} else if(request.getParameter("success_send") != null) {
	%><div class="alert alert-success">Successfully sent question</div><%
} else if(request.getParameter("reservation_success") != null) {
	%><div class="alert alert-success">Successfully Made Reservation</div><%
} else if(request.getParameter("reservation_failed") != null) {
	%><div class="alert alert-danger">Failed to make Reservation. Please try again</div><%
} else if(request.getParameter("cancel_reservation_failed") != null) {
	%><div class="alert alert-danger">Failed to cancel Reservation. Please try again</div><%
} else if(request.getParameter("cancel_reservation_success") != null) {
	%><div class="alert alert-success">Successfully canceled Reservation</div><%
}
%>
</div>

<h1>Customer Menu Page</h1>
<p>Welcome Back, <%out.print(customer.getFirstName() + customer.getLastName()); %></p>


<!--  BROWSE ALL QUESTIONS  -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header"> Browse All Questions</div>
	<div style="padding: 10px">
		<form method="post" action="./../qa/browse.jsp">
			<input class="btn btn-primary" type="submit" name="sub" value="Browse All Questions"/>
		</form>
	</div>
</div>

<!--  BROWSE ALL QUESTIONS BY KEYWORD  -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Browse Questions by Keyword</div>
	<div style="padding: 10px">
		<form method="post" action="./../qa/browse.jsp">
			<div class="form-group">
				<label for="keyword">Keyword or Phrase to search</label><br/>
				<input class="form-control" id="keyword" type="text" name="keyword" required/>
			</div>
			<input class="btn btn-primary" type="submit" value="Browse All Questions"/>
		</form>
	</div>
</div>

<!--  SEND QUESTION TO CUSTOMER SERVICE  -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Submit a new question to Customer Service</div>
	<div style="padding: 10px">
		<form id="sendQstnForm" method="post" action="./../qa/send_question_logic.jsp">
			<div class="form-group">
				<label for="question">New Question</label><br/>
				<textarea class="form-control" name="question" required></textarea>
			</div>
			<input class="btn btn-primary" type="submit" value="Submit New Question"/>	
		</form>
	</div>
</div>

<!-- Current Reservations -->
<div class="card" style="margin: 20px; width:30%">
	<div class="card-header">Reservations</div>
	<div style="padding: 10px">
		
		<form method="post" action="current_reservations_ui.jsp">
			<input type="submit" class="btn btn-primary" value="View Current Reservations"/>
		</form>
		<br/>
		<form method="post" action="current_reservations_ui.jsp">
			<input type="submit" class="btn btn-danger" value="Cancel Current Reservations"/>
		</form>
		<br/>
		<form method="post" action="past_reservations_ui.jsp">
			<input type="submit" class="btn btn-primary" value="View Past Reservations"/>
		</form>
	</div>
</div>


<!--  SEARCH TRAIN SCHEDULES  -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Search for Schedules</div>
	<div style="padding: 10px">
		<form method="post" action="./search_schedules_ui.jsp">
			<div class="form-group">
				<label for="origin">Origin</label><br/>
				<select class="form-control" id="origin" name="origin" required>
					<%
					for(Station s : stations) {
						%><option value="<% out.print(s.getName());%>"><%out.print(s.getName());%></option>
					<%}%>
				</select>
			</div>
			<div class="form-group">
				<label for="destination">Destination</label><br/>
				<select class="form-control" id="destination" name="destination" required>
					 <%
					for(Station s : stations) {
						%><option value="<% out.print(s.getName());%>"><%out.print(s.getName());%></option>
					<%}%> 
				</select>
			</div>
	
			<div class="form-group">
				<label>Date Year</label><br/>
				<input class="form-control" type="text" name="date_year" minlength=4 maxlength=4 required/>
			</div>
			
			<div class="form-group">
				<label>Date Month (1-12)</label><br/>
				<input class="form-control" type="text" name="date_month" minlength=1 maxlength=2 required/>
			</div>
			
			<div class="form-group">
				<label>Date Day (1-31)</label><br/>
				<input class="form-control" type="text" name="date_day" minlength=1 maxlength=2 required/>
			</div>
			
			<div class="form-group">
				<label>Sort By:</label><br/>
				<select class="form-control type="text" name="sort_index" required>
					<option class="form-control" value="0" selected>Sort By Fare </option>
					<option class="form-control" value="1">Sort By Date Ascending</option>
					<option class="form-control" value="2">Sort By Date Descending</option>
				</select>
				
			</div>
			
			<input class="btn btn-primary" type="submit" value="Search for Schedules"/>
		</form>
	</div>
</div>

<%} %>
<br/><br/>
<form method="post" action="./../logout_logic.jsp">
	<div class="form-group">
		<input class="btn btn-danger" type="submit" name="logout" value="Log Out">
	</div>
</form>

</body>
</html>