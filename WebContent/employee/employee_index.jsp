<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
Employee employee = (Employee) request.getSession().getAttribute(Constants.HTTP_SESSION_EMPLOYEE);
if(employee == null) {
	//shouldn't be here. Redirect
	System.out.println("[employee_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Employee Main Page</title>
</head>
<body style="padding:50px">
<%

ArrayList<QA> questions = ApplicationDB.getInstance().GetQuestions();
ArrayList<Station> stations = ApplicationDB.getInstance().GetStations();
ArrayList<TrainLine> lines = ApplicationDB.getInstance().GetTrainLines();
ArrayList<Schedule> schedules = ApplicationDB.getInstance().GetSchedules();
%>

<!--  ALERT MESSAGES FROM REDIRECTED PAGES  -->
<%
if(request.getParameter("failed_answer") != null) {
	%><div class="alert alert-danger">Failed to answer question. Please try again</div><%
} else if(request.getParameter("success_answer") != null) {
	%><div class="alert alert-success">Successfully answered question</div><%
} else if(request.getParameter("schedule_edited") != null) {
	%><div class="alert alert-success">Successfully Edited Schedule</div><%
} else if(request.getParameter("schedule_deleted") != null) {
	%><div class="alert alert-success">Successfully Deleted Schedule</div><%
} else if(request.getParameter("schedule_created") != null) {
	%><div class="alert alert-success">Successfully Created Schedule</div><%
}
%>

<h1>Customer Representative Menu Page</h1>
<p>Welcome Back, <% out.print(employee.getFirstName() + " " + employee.getLastName()); %> </p>


<!-- CREATE NEW TRAIN SCHEDULE -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Create New Train Schedule</div>
	<div style="padding: 10px">
		<form method="post" action="create_train_schedule_ui.jsp">
			<input class="btn btn-primary" type="submit" value="Create New Train Schedule"/>
		</form>
	</div>
</div>


<!--  EDIT OR DELETE TRAIN SCHEDULE -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Edit or Delete Train Schedule</div>
	<div style="padding: 10px">
		<form method="post" action="edit_schedule_ui.jsp">
			<select class="form-control" name="schedID" required>
			<%
		    for(Schedule sched : schedules) {
		    %><option value="<% out.print(sched.getSchedID()); %>"><% out.print(sched.getSchedID() + " - " + sched.getLineName() + " (" + sched.getOriginID() + " TO " + sched.getDestinationID() + ")");%></option>
		    
		    <%}%>
		    </select><br/>
		    <input class="btn btn-primary" type="submit" value="Edit Selected Train Schedule"/>
		</form>
	</div>
</div>





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
				<input id="keyword" type="text" name="keyword" required/>
			</div>
			<input class="btn btn-primary" type="submit" value="Browse All Questions"/>
		</form>
	</div>
</div>

<!--  ANSWER QUESTION  -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Answer Question</div>
	<div style="padding: 10px">
		<form method="post" action="./../qa/reply_logic.jsp">
			<label>Selected Question</label>
			<select class="form-control" name="question" required>
			<%
		    for(QA q : questions) {
		    	if(q.getAnswer().length() != 0) continue;
		    %><option value="<% out.print(q.getQuestion()); %>"><% out.print(q.getQuestion());%></option>
		    
		    <%}%>
		    </select><br/>
		    <label>Response</label>
		    <textarea class="form-control" name="answer" required></textarea><br/>
			<input class="btn btn-primary" type="submit" value="Answer Selected Question"/>
		</form>
	</div>
</div>





<!--  Schedules Base on Train Station   -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Schedules Base on Train Station</div>
	<div style="padding: 10px">
		<form method="post" action="employee_search_schedules_logic.jsp">
			<label>Train Station: </label>
			<select class="form-control" name="station_name" required>
			<%
		    for(Station station : stations) {
		    %><option value="<% out.print(station.getName()); %>"><% out.print(station.getName());%></option>
		    
		    <%}%>
		    </select><br/>
			<input class="btn btn-primary" type="submit" value="View Schedules"/>
		</form>
	</div>
</div>


<!-- Customers by transit line and date -->
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Schedules Base on Train Station</div>
	<div style="padding: 10px">
		<form method="post" action="employee_search_customer_reservations_logic.jsp">

			<label>Train Line</label>
			<select class="form-control" name="lineName" required>
				 <%
				for(TrainLine line: lines) {
					%><option value="<% out.print(line.getLineName());%>"><%out.print(line.getLineName());%></option>
				<%}%>
			</select>
			<div class="form-group">
				<label>Date Year</label>
				<input class="form-control" type="text" name="date_year" minlength=4 maxlength=4 required/>
			</div>
			<div class="form-group">
				<label>Date Month (1-12)</label>
				<input class="form-control" type="text" name="date_month" minlength=1 maxlength=2 required/>
			</div>
			<div class="form-group">
				<label>Date Day (1-31)</label>
				<input class="form-control" type="text" name="date_day" minlength=1 maxlength=2 required/>
			</div>
			<br/>
			
			<input class="btn btn-primary" type="submit" value="Search for Customers"/>
		</form>
	</div>
</div>

<form method="post" action="./../logout_logic.jsp">
	<input class="btn btn-danger" type="submit" name="logout" value="Log Out">
</form>

</body>
</html>