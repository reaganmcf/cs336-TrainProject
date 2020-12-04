<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Main Page</title>
</head>
<body>

<%
Customer customer = (Customer) request.getSession().getAttribute(Constants.HTTP_SESSION_CUSTOMER);

if(customer == null) {
	//shouldn't be here. Redirect
	System.out.println("[customer_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

out.print(customer.getEmail());
%>

<!--  ALERT MESSAGES FROM REDIRECTED PAGES  -->
<%
if(request.getParameter("failed_send") != null) {
	%><p style="color:red">Failed to send question. Please try again</p><%
} else if(request.getParameter("success_send") != null) {
	%><p style="color:green">Successfully sent question</p><%
}
%>

<!--  BROWSE ALL QUESTIONS  -->
<form method="post" action="./../qa/browse.jsp">
	<h6>Browse All Questions</h6>
	<input type="submit" name="sub" value="Browse All Questions"/>
</form>

<!--  BROWSE ALL QUESTIONS BY KEYWORD  -->
<form method="post" action="./../qa/browse.jsp">
	<h6>Browse Questions by Keyword (comma separated)</h6>
	<input type="text" name="keywords" required/>
	<input type="submit" value="Browse All Questions"/>
</form>

<!--  SEND QUESTION TO CUSTOMER SERVICE  -->
<form method="post" action="./../qa/send_question_logic.jsp">
	<h6>Submit a new question to Customer Service</h6>
	<input type="text" name="question" required/>
	<input type="submit" value="Submit New Question"/>	
</form>

<table style="border: 1px solid black">
<%
java.sql.Date d = new Date(2020 - 1900, 11, 21);
ArrayList<SpecialSchedule> ret = ApplicationDB.getInstance().SearchSchedules("Jersey Avenue Station", "Newark Airport", d, "North East Corridor N");
out.print(Constants.SPECIAL_SCHEDULE_TABLE_HEADERS);
for(int i = 0; i < ret.size(); i++) {
	out.print("<tr><checkbox value=" + i + "/>");
	out.print(ret.get(i).toTableString());
	out.print("</tr>");
}
%>
</table>

<form method="post" action="./../logout_logic.jsp">
	<input type="submit" name="logout" value="Log Out">
</form>

</body>
</html>