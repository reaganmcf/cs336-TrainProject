<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sales</title>
</head>
<body>

<%
String year = request.getParameter("year");
String month = request.getParameter("month");
if(year == null && month == null) {
	System.out.println("[obtain_monthly_sales_logic.jsp] must not be an admin, redirect to login");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

float totalSales = ApplicationDB.getInstance().GetMonthlySales(year, month);
%>
<h4>Sales for <%out.print(year);%>-<%out.print(month);%> is <br/> <%out.print("<h1>" + totalSales + "</h1>");%></h4>

</body>
</html>