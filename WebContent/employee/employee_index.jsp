<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Main Page</title>
</head>
<body>

<%
Employee employee = (Employee) request.getSession().getAttribute(Constants.HTTP_SESSION_EMPLOYEE);

if(employee == null) {
	//shouldn't be here. Redirect
	System.out.println("[employee_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

out.print("Logged in as: " + employee.getUsername() + " " + employee.getLastName());

ArrayList<QA> questions = ApplicationDB.getInstance().GetQuestions();
ArrayList<Station> stations = ApplicationDB.getInstance().GetStations();
ArrayList<TrainLine> lines = ApplicationDB.getInstance().GetTrainLines();
ArrayList<Schedule> schedules = ApplicationDB.getInstance().GetSchedules();
%>

<!--  ALERT MESSAGES FROM REDIRECTED PAGES  -->
<%
if(request.getParameter("failed_answer") != null) {
	%><p style="color:red">Failed to answer question. Please try again</p><%
} else if(request.getParameter("success_answer") != null) {
	%><p style="color:green">Successfully answered question</p><%
} else if(request.getParameter("schedule_edited") != null) {
	%><p style="color:green">Successfully Edited Schedule</p><%
} else if(request.getParameter("schedule_deleted") != null) {
	%><p style="color:green">Successfully Deleted Schedule</p><%
} else if(request.getParameter("schedule_created") != null) {
	%><p style="color:green">Successfully Created Schedule</p><%
}
%>



<!-- CREATE NEW TRAIN SCHEDULE -->
<form method="post" action="create_train_schedule_ui.jsp">
	<h4>Create New Train Schedule</h4>
	<br/>
	<input type="submit" value="Create New Train Schedule"/>
</form>


<!--  EDIT OR DELETE TRAIN SCHEDULE -->
<form method="post" action="edit_schedule_ui.jsp">
	<h4>Edit or Delete Train Schedule</h4>
	<select name="schedID" required>
	<%
    for(Schedule sched : schedules) {
    %><option value="<% out.print(sched.getSchedID()); %>"><% out.print(sched.getSchedID() + " - " + sched.getLineName() + " (" + sched.getOriginID() + " TO " + sched.getDestinationID() + ")");%></option>
    
    <%}%>
    </select><br/><br/>
    <input type="submit" value="Edit Selected Train Schedule"/>
</form>





<!--  BROWSE ALL QUESTIONS  -->
<form method="post" action="./../qa/browse.jsp">
	<h6>Browse All Questions</h6>
	<input type="submit" name="sub" value="Browse All Questions"/>
</form>

<!--  BROWSE ALL QUESTIONS BY KEYWORD  -->
<form method="post" action="./../qa/browse.jsp">
	<h6>Browse Questions by Keyword (comma separated)</h6>
	<input type="text" name="keywords" required/><br/><br/>
	<input type="submit" value="Browse All Questions"/>
</form>

<!--  ANSWER QUESTION  -->
<form method="post" action="./../qa/answer_question_logic.jsp">
	<h6>Answer Question</h6>
	<select name="question" required>
	<%
    for(QA q : questions) {
    	if(q.getAnswer().length() != 0) continue;
    %><option value="<% out.print(q.getQuestion()); %>"><% out.print(q.getQuestion());%></option>
    
    <%}%>
    </select><br/><br/>
    <input type="text" name="answer" required/><br/><br/>
	<input type="submit" value="Answer Selected Question"/>
</form>


<!--  Schedules Base on Train Station   -->
<form method="post" action="employee_search_schedules_logic.jsp">
	<h6>Schedules Base on Train Station</h6>
	<select name="station_name" required>
	<%
    for(Station station : stations) {
    %><option value="<% out.print(station.getName()); %>"><% out.print(station.getName());%></option>
    
    <%}%>
    </select><br/><br/>
	<input type="submit" value="View Schedules"/>
</form>



<!-- Customers by transit line and date -->
<form method="post" action="employee_search_customer_reservations_logic.jsp">
	<h4>Search for Customers who have Reservations on Train Line and Date</h4>
	
	<p>Train Line</p>
	<select name="lineName" required>
		 <%
		for(TrainLine line: lines) {
			%><option value="<% out.print(line.getLineName());%>"><%out.print(line.getLineName());%></option>
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
	
	<input type="submit" value="Search for Customers"/>
</form>


<form method="post" action="./../logout_logic.jsp">
	<h6>Log Out</h6>
	<input type="submit" name="logout" value="Log Out">
</form>

</body>
</html>