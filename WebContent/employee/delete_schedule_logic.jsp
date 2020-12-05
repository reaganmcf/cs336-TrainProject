<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deleting Schedule...</title>
</head>
<body>

<%
String schedID = request.getParameter("schedID");
if(schedID == null) {
	System.out.println("[delete_schedule_logic.jsp] missing param, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
} else {
	boolean res = ApplicationDB.getInstance().DeleteSchedule(schedID);
	if(res) {
		System.out.println("[delete_schedule_logic.jsp] successfully edited schedule - redirecting to employee index");
		response.sendRedirect(Constants.EMPLOYEE_INDEX_REDIRECT_URL + "?schedule_deleted=true");
	} else {
		//redirect back to create screen
		System.out.println("[delete_schedule_logic.jsp] failed to create - redirecting to create screen");
		response.sendRedirect("./edit_schedule_ui.jsp" + "?failed_delete=true&schedID=" + schedID);
	}
}
%>

</body>
</html>