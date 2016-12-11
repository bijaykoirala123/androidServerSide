<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Hotel"%>
<%@page import="account.dbOperation.DBOperation"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>update Admin Panel</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</head>
<body>

<%@ include file="../navbar.html" %>    
<a href="/HotelReservationSystem/listAdmin" style="color:#111;display:inline-block;">
<span class="glyphicon glyphicon-backward" style="margin-right:0px;"></span>Back</a>                        

  <%ArrayList<Hotel> hotelArrayList =(ArrayList<Hotel>)request.getAttribute("hotelArrayList");  %>
  <%
	Admin admin = (Admin)request.getAttribute("adminModel");
	%>    
  <% if(hotelArrayList!=null && admin!=null){ %>

	
 <form class="form-horizontal" action="/HotelReservationSystem/updateAdmin" method="post">
<fieldset>
<!-- Form Name -->
<legend>Admin Update Form</legend>

<input type="hidden" value="<%=admin.getUserId()%>" name="admin_id"/>
  

  <!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="hotels">Select Hotel:</label>
  	<div class="col-md-6">
    <select id="hotels" name="hotel_id" class="form-control"> 
 		<% for(int i=0; i<hotelArrayList.size();i++){
 			int comparisonId = admin.getHotel_id();
 			
 			if (comparisonId == hotelArrayList.get(i).getHotel_id()){ %>
 				<option value="<%=admin.getHotel_id() %>" selected="selected"><%=hotelArrayList.get(i).getHotels_name() %></option>
 		<%	} %>
 		
      	<option value="<%=hotelArrayList.get(i).getHotel_id() %>"><%=hotelArrayList.get(i).getHotels_name() %></option>
 		<% }} %>  
    </select>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="username">Username:</label>  
  <div class="col-md-6">
  <input id="username" name="username" type="text" value="<%=admin.getUsername() %>" placeholder="Enter username" class="form-control input-md" required="">
  <span class="help-block">username must be at least 15 character </span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email Address:</label>  
  <div class="col-md-6">
  <input id="email" name="email" type="text" value="<%=admin.getEmail() %>" placeholder="Enter your Email" class="form-control input-md" required="">
  <span class="help-block">email format  is 'name123@gmail.com' </span>  
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password:</label>
  <div class="col-md-6">
    <input id="password" name="password" type="password" value="<%=admin.getPassword() %>" placeholder="Enter Password" class="form-control input-md" required="">
    <span class="help-block">password must be at least 10 character</span>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit">Register Admin</label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-inverse ">Confirm</button>
  </div>
</div>

</fieldset>
</form>

</body>
</html>