<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Schedule</title>
</head>
<body>

<%
if(request.getParameter("failed") != null) {
	%><p style="color:red">Failed to Create Schedule. Please try again</p><%
}
%>

<%
ArrayList<TrainLine> lines = ApplicationDB.getInstance().GetTrainLines();
ArrayList<Train> trains = ApplicationDB.getInstance().GetTrains();

%>

<h2>Create New Schedule</h2>

<form method="post" action="./create_train_schedule_logic.jsp">
	<p>Train Line</p>
	<select name="trainLine" required>
		 <%
		for(TrainLine line: lines) {
			%><option value="<% out.print(line.getLineName());%>"><%out.print(line.getLineName());%></option>
		<%}%>
	</select>
	<p>Date (yyyy-MM-dd HH:mm:ss)</p>
	<input type="text" name="date" placeholder="2020-12-22 16:20:00" required/>
	<br/>
	<p>Train</p>
	<select name="tID" required>
	<%
    for(Train t: trains) {
	    %><option value="<% out.print(t.gettId()); %>"><% out.print(t.gettId() + " - Seats=" + t.getNumSeats() + " , Cars=" + t.getNumCars());%></option>
    <%}%>
    </select><br/><br/>
	<br/>
	<br/>
	<input type="submit" value="Create New Schedule"/>
</form>

</body>
</html>