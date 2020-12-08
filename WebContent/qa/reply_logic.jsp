<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String question = request.getParameter("question");
String answer = request.getParameter("answer");
if(request.getSession().getAttribute("employee") == null || question == null || answer == null || answer.length() == 0) {
	//redirect to dispatcher
	System.out.println("[answer_question_logic.jsp] no param - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

boolean res = ApplicationDB.getInstance().AnswerQuestion(question, answer);
if(res == true) {
	System.out.println("[answer_question_logic.jsp] successfully sent question" + question);
	response.sendRedirect("./../employee/employee_index.jsp" + "?success_answer=true");
} else {
	System.out.println("[answer_question_logic.jsp] failed to send question" + question);
	response.sendRedirect("./../employee/employee_index.jsp" + "?failed_answer=true");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Answering...</title>
</head>
<body>
</body>
</html>