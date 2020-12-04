<%@page import="com.cs336.pkg.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Main Page</title>
</head>
<body>

<%
Employee employee = (Employee) request.getSession().getAttribute(Constants.HTTP_SESSION_EMPLOYEE);

if(employee == null) {
	//shouldn't be here. Redirect
	System.out.println("[employee_index.jsp] unauthorized access - redirecting to dispatch");
	response.sendRedirect(Constants.INDEX_PATH_REDIRECT_URL);
}

out.print("Logged in as: " + employee.getUsername() + " " + employee.getLastName());

ArrayList<QA> questions = ApplicationDB.getInstance().GetQuestions();
%>

<!--  ALERT MESSAGES FROM REDIRECTED PAGES  -->
<%
if(request.getParameter("failed_answer") != null) {
	%><p style="color:red">Failed to answer question. Please try again</p><%
} else if(request.getParameter("success_answer") != null) {
	%><p style="color:green">Successfully answered question</p><%
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
	<input type="text" name="keywords" required/><br/><br/>
	<input type="submit" value="Browse All Questions"/>
</form>

<!--  ANSWER QUESTION  -->
<form method="post" action="./../qa/answer_question_logic.jsp">
	<h6>Answer Question</h6>
	<select name="question" required>
	<%
    for(QA q : questions) {
    	if(q.getAnswer().length() != 0) continue;
    %><option value="<% out.print(q.getQuestion()); %>"><% out.print(q.getQuestion());%></option>
    
    <%}%>
    </select><br/><br/>
    <input type="text" name="answer" required/><br/><br/>
	<input type="submit" value="Answer Selected Question"/>
</form>

<form method="post" action="./../logout_logic.jsp">
	<h6>Log Out</h6>
	<input type="submit" name="logout" value="Log Out">
</form>

</body>
</html>