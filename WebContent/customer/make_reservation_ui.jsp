<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

if(request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER) == null) {
	//redirect to dispatcher
	System.out.println("[make_reservation_ui.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}
%>
<%
String fare = request.getParameter("fare");
String originID = request.getParameter("originID");
String originName = request.getParameter("originName");
String destinationID = request.getParameter("destinationID");
String destinationName = request.getParameter("destinationName");
String lineName = request.getParameter("lineName");
String schedID = request.getParameter("schedID");
String startTime = request.getParameter("startTime");
String username = ((Customer) request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER)).getUsername();

if(fare == null || originID == null || destinationID == null || lineName == null || schedID == null || username == null || lineName == null || startTime == null) {
	//redirect to dispatcher
	System.out.println("[make_reservation_ui.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
	return;
}


%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Make a Reservation</title>
</head>
<body style="padding: 50px">

<div class="card" style="margin: 20px; width: 40%">
	<div class="card-header">Reservation Confirmation</div>
	<div style="padding: 10px">
		<form type="post" action="make_reservation_logic.jsp">
			<input name="schedID" style="display: none" value="<% out.print(schedID); %>"/>
			<input name="originID" style="display: none" value="<% out.print(originID); %>"/>
			<input name="destinationID" style="display: none" value="<% out.print(destinationID); %>"/>
			<div class="form-group">
				<label>Origin</label>
				<input name="originName" class="form-control" readonly value="<%out.print(originName);%>"/>
			</div>
			<div class="form-group">
				<label>Destination</label>
				<input name="destinationName" class="form-control" readonly value="<%out.print(destinationName);%>"/>
			</div>
			<div class="form-group">
				<label>Baseline Fare <i>(Will change once finalized)</i></label>
				<input name="fare" type="text" class="form-control" readonly value="<%out.print(fare);%>"/>
				<div class="card" style="margin: 20px">
					<div class="card-header">Fare Breakdown</div>
					<div style="padding: 10px">
						<table class="table table-striped table-bordered">
						<tr><th>Modifiers</th><th>Final Cost</th></tr>
						<tr><td>Round Trip</td><td>2 * Fare</td></tr>
						<tr><td>Disabled</td><td>0.5 * Fare</td></tr>
						<tr><td>Senior</td><td>0.65 * Fare</td></tr>
						<tr><td>Child</td><td>0.75 * Fare</td></tr>
						</table>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label>Line Name</label>
				<input name="lineName" type="text" class="form-control" readonly value="<%out.print(lineName);%>"/>
			</div>
			<div class="form-group">
				<label>Start</label>
				<input name="startTime" type="text" class="form-control" readonly value="<%out.print(startTime);%>"/>
			</div>
			<div class="form-group">
				<label>Passenger Name</label>
				<input name="passengerName" type="text" class="form-control" required/>
			</div>
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>Disabled?</label>
							<input type="checkbox" value=0 name="isDisabled"/>
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Child?</label>
							<input type="checkbox" value=0 name="isChild"/>
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Senior?</label>
							<input type="checkbox" value=0 name="isSenior"/>
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Round Trip?</label>
							<input type="checkbox" name="isRoundTrip"/>
						</div>
					</div>
				</div>
			</div>
			<input type="submit" class="btn btn-primary" value="Finalize Reservation"/>
		</form>
	</div>
</div>

</body>
</html>