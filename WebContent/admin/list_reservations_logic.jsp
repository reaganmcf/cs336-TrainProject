<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>List of Reservations</title>
</head>
<body style="padding: 50px">

<%
String customer_username = request.getParameter("customer_username");
String transit_line = request.getParameter("transit_line");
if(customer_username == null && transit_line == null) {
	System.out.println("[list_reservations_ui.jsp] must not be an admin, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

ArrayList<Reservation> reservations = new ArrayList<Reservation>();
if(customer_username != null) {
	//search by customer name
	reservations = ApplicationDB.getInstance().SearchReservationsByUsername(customer_username);
} else {
	//search by transit line
	reservations = ApplicationDB.getInstance().SearchReservationsByTransitLine(transit_line);
}

%>

<table class="table table-bordered table-striped">
<%out.print(Constants.RESERVATION_TABLE_HEADERS);%>
<%
for(Reservation q : reservations) {
	out.println(q.toTableString());
}	
%>
</table>

</body>
</html>