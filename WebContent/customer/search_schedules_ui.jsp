<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String origin = request.getParameter("origin");
String destination = request.getParameter("destination");
String date_year = request.getParameter("date_year");
String date_month = request.getParameter("date_month");
String date_day = request.getParameter("date_day");
if(origin == null || destination == null || date_year == null || date_month == null || date_day == null) {
	//redirect to dispatcher
	System.out.println("[search_schedules_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Schedules</title>
</head>
<body style="padding: 50px">
<%
Date d = new Date(Integer.parseInt(date_year) - 1900, Integer.parseInt(date_month)-1, Integer.parseInt(date_day));
%>
<div class="container">
	<div class="row">
		<div class="col">
			<div class="card">
				<div class="card-header">Schedules</div>
				<table class="table table-bordered table-striped">
				<%
				ArrayList<SpecialSchedule> ret = ApplicationDB.getInstance().SearchSchedules(origin, destination, d);
				
				
				out.print(Constants.SPECIAL_SCHEDULE_TABLE_HEADERS + "<th>Reserve</th>");
				for(SpecialSchedule sc : ret) {
					out.print("<tr>");
					out.print(sc.toTableStringRaw());
					%>
					<td>
					<form type="post" action="make_reservation_ui.jsp">
						<input type="text" style="display: none" name="fare" value="<% out.print(sc.getFare()); %>"/>
						<input type="text" style="display: none" name="originID" value="<% out.print(sc.getOriginID()); %>"/>
						<input type="text" style="display: none" name="originName" value="<% out.print(origin); %>"/>
						<input type="text" style="display: none" name="destinationID" value="<% out.print(sc.getDestinationID()); %>"/>
						<input type="text" style="display: none" name="destinationName" value="<% out.print(destination); %>"/>
						<input type="text" style="display: none" name="lineName" value="<% out.print(sc.getLineName()); %>"/>
						<input type="text" style="display: none" name="schedID" value="<% out.print(sc.getSchedID()); %>"/>
						<input type="text" style="display: none" name="startTime" value="<% out.print(sc.getStart()); %>"/>
						<input class="btn btn-primary" type="submit" value="Make Reservation"/>
					</form>
					</td>
					<%
					out.print("</tr>");
				}
				%>
				</table>
			</div>
		</div>
		<div class="card">
			<div class="card-header">Stops on this Schedule</div>
			<table class="table-sm table-bordered table-striped">
			<%
			ArrayList<String> stops = ApplicationDB.getInstance().GetStopsOnSchedule(origin, destination);
			out.print("<th>Stop #</th><th>Station Name</th>");
			for(int i = 0; i < stops.size(); i++) {
				out.print("<tr><td>" + i + "</td><td>" + stops.get(i) + "</td></tr>");
			}
			
			%>
			</table>
		</div>
	</div>
</div>
</body>
</html>