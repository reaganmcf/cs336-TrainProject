<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>

<%
String stationName = request.getParameter("station_name");
if(stationName == null) {//redirect to dispatcher
	System.out.println("[employee_search_schedules_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Schedule List</title>
</head>
<body style="padding: 50px">

%>
<table class="table table-striped table-bordered">
<%
ArrayList<Schedule> schedules = ApplicationDB.getInstance().GetSchedulesOnStationName(stationName);
out.print(Constants.SCHEDULE_TABLE_HEADERS);
for(int i = 0; i < schedules.size(); i++) {
	out.print(schedules.get(i).toTableString());
}
%>
</table>

</body>
</html>