<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
    
<%
Customer customer = (Customer) request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER);

if(customer == null) {
	//shouldn't be here. Redirect
	System.out.println("[past_reservations_ui.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}

ArrayList<Reservation> pastReservations = ApplicationDB.getInstance().GetPastReservationsByUsername(customer.getUsername());
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Past Reservations</title>
</head>
<body style="padding: 50px">

<div style="padding: 10px">
	<table class="table table-striped table-bordered">

	<%
	out.print(Constants.RESERVATION_TABLE_HEADERS);
	for(Reservation r : pastReservations) {
		out.print(r.toTableString());
	}
	%>
	</table>
</div>

</body>
</html>