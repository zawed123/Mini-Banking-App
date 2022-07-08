<%@page import="com.monocept.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!doctype
html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>Passbook</title>
</head>
<body>
	<%
	  Account account=(Account) session.getAttribute("currentUser");
	  out.println("<center>"+
	  "<h3>Passbook - &nbsp&nbsp"+account.getName()+"</h3>"+
			  "</center>");
	%>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Amount</th>
				<th scope="col">Transection Type</th>
				<th scope="col">Date & Time</th>
			</tr>
			<%
			List<Transaction> transactionList = (ArrayList) request.getAttribute("transactions");
			for (Transaction transection : transactionList) {
				out.println("<tr><td>" + transection.getUsername() + "</td><td>" + transection.getAmount() + "</td><td>" + transection.getType()
				+ "</td><td>" + transection.getDate() + "</tr>");
			}
			%>
		
	</table>
</body>
</html>