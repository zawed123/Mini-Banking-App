<%@page import="com.monocept.model.Account"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>Transaction</title>
</head>
<body>
  <center>
    <h8 id="msg"></h8>
	<%
	  Account account=(Account)session.getAttribute("currentUser");
	  out.println("<h3>Transaction Page of "+account.getName()+"</h3>");
	%>
	<h5 id="type"></h5>
	<form action="transactions" method="post" id="myform">
		Amount:<input type="number" name="amount" placeholder="Enter amount" required> <br>
		<input type="radio" name="type" value="deposite"> Deposite <br>
		<input type="radio" name="type" value="withdraw"> Withdraw <br><br>
		<input type="submit" value="Update" class="btn btn-success"><br>
	</form>
  </center>
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	 $(document).ready(function(){
		 console.log("Page is ready");
		 
		 $('#myform').on('submit',function(event){
			 event.preventDefault();
			 
			 var f=$(this).serialize()
			 
			 //console.log(f);

			 $.ajax({
				 url:"transactions",
				 data:f,
				 type:'POST',
				 success:function(data,textStatus,jqXHR){
					 console.log(data);
					 if(data.trim()==='Diposit' || data.trim()==='Withdraw')
					  {
						 $('#msg').html("Transection Success");
						 $('#msg').addClass('text-success');
						 $('#type').html(data);
					  }
					 else
					  {
						 $('#msg').html("Insufficient balance!");
						 $('#msg').addClass('text-danger'); 
					  }
				 },
				 error:function(jqXHR,textStatus,errorThrown){
					 console.log(data);
					 console.log("error...");
				 }
			 })
		 })
	 })
	</script>
</body>
</html>