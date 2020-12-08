<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%
String trainLine = request.getParameter("trainLine");
String date = request.getParameter("date");
String tID = request.getParameter("tID");

if(trainLine == null || date == null || tID == null) {
	System.out.println("[create_train_schedule_logic.jsp] missing param, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
} else {
	boolean res = ApplicationDB.getInstance().CreateSchedule(trainLine, date, tID);
	if(res) {
		System.out.println("[create_train_schedule_logic.jsp] successfully created schedule - redirecting to employee index");
		response.sendRedirect(Constants.EMPLOYEE_INDEX_REDIRECT_URL + "?schedule_created=true");
	} else {
		//redirect back to create screen
		System.out.println("[create_train_schedule_logic.jsp] failed to create - redirecting to create screen");
		response.sendRedirect("./create_train_schedule_ui.jsp" + "?failed=true");
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creating Schedule...</title>
</head>
<body>


</body>
</html>