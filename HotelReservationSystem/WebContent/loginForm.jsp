<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</head>
<body>


 <%String message = (String)request.getAttribute("message"); 
if(message!= null){
%>

<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-warning pull-right" role="alert">
<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>
<span data-notify="icon"></span>
<span data-notify="title">
<%=message %>
<%} %>
</span>
</div>


	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					<form id="loginform" class="form-horizontal" method="post" action="/HotelReservationSystem/adminLogin">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="text" class="form-control" name="username" 
								placeholder="Admin Account">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="password"
								placeholder="Admin Password">
						</div>
						
						
						<!-- Button -->
						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-12 controls">
								<input type="submit" name="submit" value="login" id="btn-login" class="btn btn-success pull-right"/>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>