<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<% try {
	
			//Get the database connection
			ResultSet result = ApplicationDB.getInstance().BeerDrinkTestCommand();
			String entity = "beers";
		%>
			
		<!--  Make an HTML table to show the results in: -->
	<table>
		<tr>    
			<td>Name</td>
			<td>
				<%if (entity.equals("beers"))
					out.print("Manufacturer");
				else
					out.print("Address");
				%>
			</td>
		</tr>
			<%
			//parse out the results
			while (result.next()) { %>
				<tr>    
					<td><%= result.getString("name") %></td>
					<td>
						<% if (entity.equals("beers")){ %>
							<%= result.getString("manf")%>
						<% }else{ %>
							<%= result.getString("addr")%>
						<% } %>
					</td>
				</tr>
				

			<% }
			//close the connection.
			ApplicationDB.getInstance().closeConnection();
			%>
		</table>

			
		<%} catch (Exception e) {
			out.print(e);
		}%>
	

	</body>
</html>