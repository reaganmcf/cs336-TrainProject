<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editing Schedule...</title>

<%
String schedID = request.getParameter("schedID");
String originID = request.getParameter("originID");
String destinationID = request.getParameter("destinationID");
String lineName = request.getParameter("lineName");
String startTime = request.getParameter("startTime");
String tID = request.getParameter("tID");

if(schedID == null || originID == null || destinationID == null || lineName == null || startTime == null || tID == null) {
	System.out.println("[edit_schedule_logic.jsp] missing param, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
} else {
	boolean res = ApplicationDB.getInstance().EditSchedule(schedID, startTime, tID);
	if(res) {
		System.out.println("[edit_schedule_logic.jsp] successfully edited schedule - redirecting to employee index");
		response.sendRedirect(Constants.EMPLOYEE_INDEX_REDIRECT_URL + "?schedule_edited=true");
	} else {
		//redirect back to create screen
		System.out.println("[edit_schedule_logic.jsp] failed to create - redirecting to create screen");
		response.sendRedirect("./edit_schedule_ui.jsp" + "?failed=true&schedID=" + schedID);
	}
}
%>
</head>
<body>

</body>
</html>