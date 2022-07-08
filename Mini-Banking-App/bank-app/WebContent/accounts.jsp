<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.monocept.model.Account"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Account Details</h1>
	<%
		ArrayList<Account> accountList = (ArrayList) request.getAttribute("accountList");
		if(accountList!=null) {
			out.print("<table border='2px'>");
			out.print("<tr><th>USERNAME</th><th>BALANCE</th><th>PASSWORD</th></tr>");
			for (Account account : accountList) {
				out.print("<tr><td>" + account.getName() + "</td><td>" + account.getBalance() + "</td><td>"
						+ account.getPassword() + "</td></tr>");
			}
			out.print("</table>");
		} else {
			out.print("No records found!");
		}
	%>
</body>
</html>