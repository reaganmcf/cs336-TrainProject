<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Browse Questions and Answers</title>
</head>
<body>

<%
//Now that we are authenticated, start pulling data we need
ArrayList<QA> questions = ApplicationDB.getInstance().GetQuestions();
request.getSession().setAttribute(Constants.HTTP_SESSION_QA_LIST, questions);

String[] kw  = {};
if(request.getParameter("keywords") != null) {
	kw = request.getParameter("keywords").split("\\,");
	
	out.print("keywords:");
	for(String k : kw) {
		out.println(k);
	}
}
%>

<table>
<%out.print(Constants.QUESTIONS_ANSWER_TABLE_HEADERS); %>
<%
for(QA q : questions) {
	out.println(q.toTableString());
}	
%>
</table>
</body>
</html>