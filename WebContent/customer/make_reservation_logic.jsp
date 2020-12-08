<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%
if(request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER) == null) {
	//redirect to dispatcher
	System.out.println("[make_reservation_logic.jsp] not logged in - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}
String username = ((Customer) request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER)).getUsername();

String tschedID = request.getParameter("schedID");
String toriginID = request.getParameter("originID");
String tdestinationID = request.getParameter("destinationID");
String tFare = request.getParameter("fare");
String lineName = request.getParameter("lineName");
String startDate = request.getParameter("startTime");
int isDisabled = request.getParameter("isDisabled") == null ? 0 : 1;
int isChild = request.getParameter("isChild") == null ? 0 : 1;
int isSenior = request.getParameter("isSenior") == null ? 0 : 1;
int isRoundTrip = request.getParameter("isRoundTrip") == null ? 0 : 1;
String passengerName = request.getParameter("passengerName");

if(tschedID == null || toriginID == null || tdestinationID == null ||
	lineName == null || startDate == null || passengerName == null || tFare == null) {
	//redirect to dispatcher
	System.out.println("[make_reservation_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}

int originID = Integer.parseInt(toriginID);
int destinationID = Integer.parseInt(tdestinationID);
int schedID = Integer.parseInt(tschedID);
float fare = Float.parseFloat(tFare);

boolean res = ApplicationDB.getInstance().MakeReservation(fare, isRoundTrip, isDisabled, isSenior, isChild, passengerName, startDate, originID, destinationID, lineName, username, schedID);
if(res) {
	System.out.println("[make_reservation_logic.jsp] successfully edited schedule - redirecting to employee index");
	response.sendRedirect("./customer_index.jsp" + "?reservation_success=true");
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