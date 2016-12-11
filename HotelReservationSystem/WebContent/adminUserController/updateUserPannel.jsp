<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="model.DBConnectModel" %>
<%@ page import="model.User" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</head>
<body>

<%@ include file="../navbar.html" %>
 <a href="/HotelReservationSystem/listUsers" style="color:#111;display:inline-block;">
<span class="glyphicon glyphicon-backward" style="margin-right:0px;"></span>Back</a>                        
    
<%
User user = (User)request.getAttribute("userModel");
%>    
 <form class="form-horizontal" action="/HotelReservationSystem/updateUser" method="post">
<fieldset>

<!-- Form Name -->
<legend>User Register Form</legend>

<!-- hidden text -->
 <input name="guest_id" type="hidden" value="<%=user.getGuest_id() %>"  required="">

<!-- Text input-->
<div class="form-group">

  <label class="col-md-4 control-label" for="guest_name">User Name:</label>  
  <div class="col-md-6">
  <input id="guest_name" name="guest_name" type="text" value="<%=user.getGuest_name() %>" placeholder="Enter your full name" class="form-control input-md" required="">
  <span class="help-block">username must be at least 15 character </span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="guest_email">Email Address:</label>  
  <div class="col-md-6">
  <input id="guest_email" name="guest_email" type="text" value="<%=user.getGuest_email() %>" placeholder="Enter your Email" class="form-control input-md" required="">
  <span class="help-block">email format  is 'name@gmail.com' </span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="guest_ph_no">Phone Number:</label>  
  <div class="col-md-6">
  <input id="guest_ph_no" name="guest_ph_no" type="text" value="<%=user.getGuest_phone_number() %>" placeholder="Enter Phone Number" class="form-control input-md" required="">
  <span class="help-block">enter with your country code </span>  
  </div>
</div>

<!-- Multiple Radios (inline) -->
<div class="form-group">
  <label class="col-md-4 control-label" for="guest_gender">Gender:</label>
  <div class="col-md-4"> 
    <label class="radio-inline" for="guest_gender-0">
    <%if(user.getGuest_gender().equals("male")){ %>
      <input type="radio" name="gender" id="guest_gender-0" value="male" checked="checked">
      <%}else{ %>
      <input type="radio" name="gender" id="guest_gender-0" value="male">
      <%} %>
      male
    </label> 
    <label class="radio-inline" for="guest_gender-1">
    <%if(user.getGuest_gender().equals("female")){ %>
      <input type="radio" name="gender" id="guest_gender-1" value="female" checked="checked">
      <%}else{ %>
      <input type="radio" name="gender" id="guest_gender-1" value="female">
      <%} %>
      female
    </label>
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="address">Address:</label>
  <div class="col-md-6">
    <select id="address" name="address" class="form-control">
      <% if(user.getGuest_location()!=null){%>
      <option value="<%=user.getGuest_location() %>" selected="selected"><%=user.getGuest_location() %></option>
      <%} %>
      <option value="Nepal">nepal</option>
      <option value="China">china</option>
    </select>

  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="guest_password">Password:</label>
  <div class="col-md-6">
    <input id="guest_password" name="guest_password" type="password" value="<%=user.getGuest_password() %>" placeholder="Enter Password" class="form-control input-md" required="">
    <span class="help-block">password must be at least 10 character</span>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit">Register User</label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-inverse ">Confirm</button>
  </div>
</div>

</fieldset>
</form>

</div>
</div>
</div>

</body>
</html>