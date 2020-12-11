<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>

<%
String resNum = request.getParameter("resNum");
if(resNum == null) {
	//redirect to dispatcher
	System.out.println("[cancel_reservation_logic.jsp] not logged in - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}

boolean res = ApplicationDB.getInstance().CancelReservation(Integer.parseInt(resNum));
if(res) {
	System.out.println("[cancel_reservation_logic.jsp] successfully edited schedule - redirecting to employee index");
	response.sendRedirect("./customer_index.jsp" + "?cancel_reservation_success=true");
} else {
	//redirect back to create screen
	System.out.println("[cancel_reservation_logic.jsp] failed to create - redirecting to create screen");
	response.sendRedirect("./customer_index.jsp" + "?cancel_reservation_failed=true");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancelling Reservation...</title>
</head>
<body>

</body>
</html>