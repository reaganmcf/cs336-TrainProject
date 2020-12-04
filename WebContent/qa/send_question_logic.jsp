<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sending question...</title>
</head>
<body>
<%
String question = request.getParameter("question");
if(question == null || question.length() == 0) {
	//redirect to dispatcher
	System.out.println("[delete_employee_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

boolean res = ApplicationDB.getInstance().SendQuestion(question);
if(res == true) {
	System.out.println("[send_question_logic.jsp] successfully sent question" + question);
	response.sendRedirect("./../customer/customer_index.jsp" + "?success_send=true");
} else {
	System.out.println("[send_question_logic.jsp] failed to send question" + question);
	response.sendRedirect("./../customer/customer_index.jsp" + "?failed_send=true");
}
%>

</body>
</html>