<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Schedules</title>
</head>
<body>
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
}

Date d = new Date(Integer.parseInt(date_year) - 1900, Integer.parseInt(date_month)-1, Integer.parseInt(date_day));
%>
<table style="border: 1px solid black">
<%
ArrayList<SpecialSchedule> ret = ApplicationDB.getInstance().SearchSchedules(origin, destination, d);
out.print(Constants.SPECIAL_SCHEDULE_TABLE_HEADERS);
for(int i = 0; i < ret.size(); i++) {
	out.print(ret.get(i).toTableString());
}
%>
</table>
</body>
</html>