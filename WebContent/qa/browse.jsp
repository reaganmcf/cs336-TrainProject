<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./../include.jsp" />
<meta charset="ISO-8859-1">
<title>Browse Questions and Answers</title>
</head>
<body style="padding: 50px">

<%
//Now that we are authenticated, start pulling data we need
ArrayList<QA> questions = new ArrayList<QA>();

String keyword = request.getParameter("keyword");
if(keyword == null) {
	questions = ApplicationDB.getInstance().GetQuestions();	
} else {
	questions = ApplicationDB.getInstance().GetQuestionsByKeyword(keyword);
}
request.getSession().setAttribute(Constants.HTTP_SESSION_QA_LIST, questions);
%>

<table class="table table-striped table-bordered">
<%out.print(Constants.QUESTIONS_ANSWER_TABLE_HEADERS); %>
<%
for(QA q : questions) {
	out.println(q.toTableString());
}	
%>
</table>
</body>
</html>