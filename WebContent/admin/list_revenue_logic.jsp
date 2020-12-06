<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>List of Revenue</title>
</head>
<body style="padding: 50px">
<%
String customer_username = request.getParameter("customer_username");
String transit_line = request.getParameter("transit_line");
if(customer_username == null && transit_line == null) {
	System.out.println("[list_revenue_logic.jsp] must not be an admin, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

ArrayList<ArrayList<String>> revenues = new ArrayList<ArrayList<String>>();
if(customer_username != null) {
	//group by customer name
	revenues = ApplicationDB.getInstance().TotalRevenueByCustomer();
	%><table class="table table-bordered table-striped"><%
	out.print(Constants.TOTAL_REVENUE_BY_CUSTOMER_TABLE_HEADERS);
	for(ArrayList<String> row : revenues) {
		String t = "";
		for(String s : row) {
			t += "<td>" + s + "</td>";
		}
		out.println("<tr>" + t + "</tr>");
	}
	%></table><%
} else {
	//group by transit line
	revenues = ApplicationDB.getInstance().TotalRevenueByTransitLine();
	%><table class="table table-bordered table-striped"><%
	out.print(Constants.TOTAL_REVENUE_BY_TRAINLINE_TABLE_HEADERS);
	for(ArrayList<String> row : revenues) {
		String t = "";
		for(String s : row) {
			t += "<td>" + s + "</td>";
		}
		out.println("<tr>" + t + "</tr>");
	}
	out.print("</table>");
}
%>

</body>
</html>