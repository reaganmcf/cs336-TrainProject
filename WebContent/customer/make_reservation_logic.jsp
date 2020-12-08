<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%
if(request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER) == null) {
	//redirect to dispatcher
	System.out.println("[search_schedules_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}

String schedID = request.getParameter("schedID");
String originID = request.getParameter("originID");
String destinationID = request.getParameter("destinationID");
String lineName = request.getParameter("lineName");
String startDate = request.getParameter("startTime");
String isDisabled = request.getParameter("isDisabled");
String isChild = request.getParameter("isChild");
String isSenior = request.getParameter("isSenior");
String isRoundTrip = request.getParameter("isRoundTrip");

if(schedID == null || originID == null || destinationID == null || lineName == null || startDate == null || isDisabled == null || isChild == null || isSenior == null || isRoundTrip == null) {
	//redirect to dispatcher
	System.out.println("[make_reservation_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}

boolean res = ApplicationDB.getInstance().MakeReservation(schedID);
if(res) {
	System.out.println("[make_reservation_logic.jsp] successfully edited schedule - redirecting to employee index");
	response.sendRedirect("./customer_index" + "?reservation_success=true");
} else {
	//redirect back to create screen
	System.out.println("[make_reservation_logic.jsp] failed to create - redirecting to create screen");
	response.sendRedirect("./customer_index.jsp" + "?reservation_failed=true");
}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>