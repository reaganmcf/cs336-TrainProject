<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Schedule</title>
</head>
<body>

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

<h1>Edit Schedule</h1>
<form method="post" action="edit_schedule_logic.jsp">
	<p>Schedule ID (locked)</p>
	<input type="text" name="schedID" value="<%out.print(curr_sched.getSchedID());%>" readonly/>
	<br/>
	<p>Origin ID (locked)</p>
	<input type="text" name="originID" value="<%out.print(curr_sched.getOriginID());%>" readonly/>
	<br/>
	<p>Destination ID (locked)</p>
	<input type="text" name="destinationID" value="<%out.print(curr_sched.getDestinationID());%>" readonly/>
	<br/>
	<p>Line Name (locked)</p>
	<input type="text" name="lineName" value="<%out.print(curr_sched.getLineName());%>" readonly/>
	<br/>
	<p>Start Time (dd MMM YYYY hh:mm:ss GMT)</p>
	<input type="text" name="startTime" value="<%out.print(curr_sched.getStartTime().toGMTString());%>"/>
	<p>Train ID (current = <% out.print(curr_sched.getTID());%>)</p>
	
	<select name="tID" required>
	<%
    for(Train t: trains) {
    %><option value="<% out.print(t.gettId()); %>"><% out.print(t.gettId() + " - Seats=" + t.getNumSeats() + " , Cars=" + t.getNumCars());%></option>
    
    <%}%>
    </select><br/><br/>
	<br/>
	<input type="submit" value="Edit Schedule"/>
</form>
<br/><br/>
<form method="post" action="delete_schedule_logic.jsp">
	<input type="submit" value="Delete Schedule"/>
</form>

</body>
</html>