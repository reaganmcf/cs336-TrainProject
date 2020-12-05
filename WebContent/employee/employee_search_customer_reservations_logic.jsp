<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customers by Transit Line and Date</title>
</head>
<body>

<%
String lineName = request.getParameter("lineName");
String date_year = request.getParameter("date_year");
String date_month = request.getParameter("date_month");
String date_day = request.getParameter("date_day");
if(lineName == null || date_year == null || date_month == null || date_day == null) {//redirect to dispatcher
	System.out.println("[employee_search_schedules_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

Date d = new Date(Integer.parseInt(date_year) - 1900, Integer.parseInt(date_month)-1, Integer.parseInt(date_day));
%>
<table style="border: 1px solid black">
<%
ArrayList<String> ret = ApplicationDB.getInstance().SearchCustomersByTrainLineAndDate(lineName, d);

out.print("<th>Username</th>");
for(int i = 0; i < ret.size(); i++) {
	out.print("<tr><td>" + ret.get(i) + "</td></tr>");
}
%>

</body>
</html>