<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<div class="container">
		<div class="row">
			<div class="col m6 offset-m3">
				<div class="card">
					<div class="card-content">
						<h3 class="center-align">Register Here !!</h3>
						<h5 id="msg" class="center-align"></h5>
						<div class="form center-align">
							<form action="register" method="POST" id="myform">
								<input type="text" name="user_name"
									placeholder="Enter user name"> <input type="number"
									name="user_amt" placeholder="Enter user amount"> <input
									type="password" name="user_password"
									placeholder="Enter user password">
								<button type="submit" class="btn">Submit</button>
							</form>
						</div>
						
						
			
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	 $(document).ready(function(){
		 console.log("Page is ready");
		 
		 $('#myform').on('submit',function(event){
			 event.preventDefault();
			 
			 var f=$(this).serialize()
			 
			 //console.log(f);
			 
			 $(".loader").show();
			 $(".form").hide();
			 $.ajax({
				 url:"register",
				 data:f,
				 type:'POST',
				 success:function(data,textStatus,jqXHR){
					 console.log(data);
					 console.log("success...");
					 $(".loader").hide();
					 $(".form").show();
					 if(data.trim()==='done')
					  {
						 alert("Successfully Registered!"); 
						 window.location="login";
					  }
					 else 
					  {
						 $('#msg').html("Somthing went wrong on server");
						 $('#msg').addClass('red-text');
					  }
				 },
				 error:function(jqXHR,textStatus,errorThrown){
					 console.log(data);
					 console.log("error...");
					 $(".loader").hide();
					 $(".form").show();
					 $('#msg').html=("Somthing went wrong on server");
					 $('#msg').addClass('red-text');
				 }
			 })
		 })
	 })
	</script>
</body>
</html>