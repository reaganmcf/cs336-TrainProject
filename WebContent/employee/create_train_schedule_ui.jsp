<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Create New Schedule</title>
</head>
<body style="padding: 50px">

<%
if(request.getParameter("failed") != null) {
	%><div class="alert alert-danger">Failed to Create Schedule. Please try again</div><%
}
%>

<%
ArrayList<TrainLine> lines = ApplicationDB.getInstance().GetTrainLines();
ArrayList<Train> trains = ApplicationDB.getInstance().GetTrains();

%>

<h2>Create New Schedule</h2>

<div class="card" style="margin: 20px; width: 30%">
	<div class="card-header">New Schedule</div>
	<div style="padding: 10px">
		<form method="post" action="./create_train_schedule_logic.jsp">
			
			<div class="form-group">
				<label>Train Line</label>
				<select class="form-control" name="trainLine" required>
					 <%
					for(TrainLine line: lines) {
						%><option value="<% out.print(line.getLineName());%>"><%out.print(line.getLineName());%></option>
					<%}%>
				</select>
			</div>
			<div class="form-group">
				<label>Date (yyyy-MM-dd HH:mm:ss)</label>
				<input class="form-control" type="text" name="date" placeholder="2020-12-22 16:20:00" required/>
			</div>
			<div class="form-group">
				<label>Train</label>
				<select class="form-control" name="tID" required>
				<%
			    for(Train t: trains) {
				    %><option value="<% out.print(t.gettId()); %>"><% out.print(t.gettId() + " - Seats=" + t.getNumSeats() + " , Cars=" + t.getNumCars());%></option>
			    <%}%>
			    </select>
			</div>
			<br/>
			<input class="btn btn-primary" type="submit" value="Create New Schedule"/>
		</form>
	</div>
</div>
</body>
</html>