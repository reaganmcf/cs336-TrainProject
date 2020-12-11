<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
    
<%
Customer customer = (Customer) request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER);

if(customer == null) {
	//shouldn't be here. Redirect
	System.out.println("[customer_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}

ArrayList<Reservation> currentReservations = ApplicationDB.getInstance().GetReservationsByUsername(customer.getUsername());
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Current Reservations</title>
</head>
<body style="padding: 50px">

<div style="padding: 10px">
	<table class="table table-striped table-bordered">

	<%
	out.print(Constants.RESERVATION_TABLE_HEADERS);
	out.print("<th>Cancel?</th>");
	for(Reservation r : currentReservations) {
		%><tr><%
		out.print(r.toRawTableString());
		%>
		<td>
		<form method="post" action="cancel_reservation_logic.jsp">
			<input type="text" name="resNum" value="<% out.print(r.getResNum());%>" style="display: none"/>
			<input type="submit" class="btn btn-danger" value="Cancel"/>
		</form>
		</td>
		</tr>
		<%
	}
	%>
	</table>
</div>

</body>
</html>