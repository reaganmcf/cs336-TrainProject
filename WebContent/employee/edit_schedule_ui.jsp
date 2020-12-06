<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Edit Schedule</title>
</head>
<body style="padding: 50px">

<%
if(request.getParameter("failed") != null) {
	%><p style="color:red">Failed to Edit Schedule. Please try again</p><%
} else if(request.getParameter("failed_delete") != null) {
	%><p style="color:red">Failed to Delete Schedule. Please try again</p><%
}
%>
<%
String selected_schedID = request.getParameter("schedID");
if(selected_schedID == null) {
	System.out.println("[edit_schedule_ui.jsp] missing param, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

int target_schedID = Integer.parseInt(selected_schedID);

ArrayList<Schedule> schedules = ApplicationDB.getInstance().GetSchedules();

Schedule curr_sched = null;
for(Schedule s : schedules) {
	if(s.getSchedID() == target_schedID) {
		curr_sched = s;
		break;
	}
}

ArrayList<Train> trains = ApplicationDB.getInstance().GetTrains();

//curr_sched can't be null here since its a param from the list
%>

<h2>Edit Schedule</h2>
<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">Schedule Details</div>
	<div style="padding: 10px">
		<form method="post" action="edit_schedule_logic.jsp">
			<label>Schedule ID (locked)</label>
			<input class="form-control" type="text" name="schedID" value="<%out.print(curr_sched.getSchedID());%>" readonly/>
			<br/>
			<label>Origin ID (locked)</label>
			<input class="form-control" type="text" name="originID" value="<%out.print(curr_sched.getOriginID());%>" readonly/>
			<br/>
			<label>Destination ID (locked)</label>
			<input class="form-control" type="text" name="destinationID" value="<%out.print(curr_sched.getDestinationID());%>" readonly/>
			<br/>
			<label>Line Name (locked)</label>
			<input class="form-control" type="text" name="lineName" value="<%out.print(curr_sched.getLineName());%>" readonly/>
			<br/>
			<div class="form-group">
				<label>Start Time (yyyy-MM-dd HH:mm:ss)</label>
				<input class="form-control" type="text" name="startTime" value="<%out.print(curr_sched.getStartTime());%>"/>
			</div>
			<div class="form-group">
				<label>Train ID (current = <% out.print(curr_sched.getTID());%>)</label>
				<select class="form-control" name="tID" required>
				<%
			    for(Train t: trains) {
			    %><option value="<% out.print(t.gettId()); %>"><% out.print(t.gettId() + " - Seats=" + t.getNumSeats() + " , Cars=" + t.getNumCars());%></option>
			    
			    <%}%>
			    </select>
			</div>	
			<br/>
			<input class="btn btn-primary" type="submit" value="Edit Schedule"/>
		</form>
	</div>
</div>
<form method="post" action="delete_schedule_logic.jsp?schedID=<%out.print(curr_sched.getSchedID()); %>">
	<input class="btn btn-danger" type="submit" value="Delete Schedule"/>
</form>

</body>
</html>