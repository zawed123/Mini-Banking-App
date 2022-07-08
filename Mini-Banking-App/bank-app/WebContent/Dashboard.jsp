<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.monocept.model.Account"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body>
<center>
  <div class="container" style="width:100%;">
		<div class="row">
			<div class="col m6 offset-m3">
				<div class="card">
					<div class="card-content">
					<%
					  out.println("<a href=\"logout\" class='btn btn-danger'>Logout</a>");
					%>
						<h3 class="center-align">DashBoard!!</h3>
						<%
						
						   Account account=(Account)session.getAttribute("currentUser");
						   out.println("<h5 class='center-align'>User Name-"+account.getName()+"| Balance-"+account.getBalance()+"</h5>");
						   
						   out.println("<a href=\"passbook\" class=\"btn btn-primary\">View Passwbook</a>");
						   out.println("<a href=\"transactions\" class=\"btn btn-primary\">Transection</a>");
						%>
						<h5 id="msg" class="center-align"></h5>
						<div class="form center-align">
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</center>
</body>
</html>