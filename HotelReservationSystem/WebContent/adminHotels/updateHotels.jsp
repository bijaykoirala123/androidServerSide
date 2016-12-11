<%@page import="model.Hotel"%>
<%@page import="model.Countries"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<title>Insert title here</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</head>
<body>
<%@ include file="../navbar.html" %>    

<a href="/HotelReservationSystem/listAllHotels" style="color:#111;display:inline-block;">
<span class="glyphicon glyphicon-backward" style="margin-right:0px;"></span>Back</a>                        


 <form class="form-horizontal" action="/HotelReservationSystem/updateHotels" method="post">
<fieldset>

<!-- Form Name -->
<legend>Insert Hotels</legend>
 
  <%
  ArrayList<Hotel> hotelArrayList = (ArrayList<Hotel>)request.getAttribute("hotelArrayList");
  ArrayList<Countries> countriesArrayList =(ArrayList<Countries>)request.getAttribute("countriesArrayList"); 
  %>
  
  <% 
  if(countriesArrayList!=null && hotelArrayList!=null){
  %>

<% for(int j = 0; j<hotelArrayList.size();j++){ %>

<input name="hotel_id" type="hidden" value="<%=hotelArrayList.get(j).getHotel_id() %>" required="">

<!-- Text input-->
<div class="form-group form-inline">  
  <label class="col-md-4 control-label" for="hotelname">Hotel Name:</label>  
  <div class="col-md-4">
  <input id="hotelname" name="hotelname" type="text" value="<%=hotelArrayList.get(j).getHotels_name() %>" placeholder="Enter Hotel Name" class="form-control input-md" required="">
  </div>

<!-- button -->  
   <div class="col-md-offset-9">
    <button id="submit" name="submit" class="btn btn-inverse ">Save</button>  
   </div>
</div>

  <!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="location">Select countries:</label>
  	<div class="col-md-6">
    <select id="location" name="location" class="form-control"> 
 		<option value="<%=hotelArrayList.get(j).getLocation()%> selected='selected'"><%=hotelArrayList.get(j).getLocation()%></option>
 	
 		<% for(int i=0; i<countriesArrayList.size();i++){ %>
      	<option value="<%=countriesArrayList.get(i).getCountry_name()%>" selected="selected"><%=countriesArrayList.get(i).getCountry_name() %></option>
 		<% } %>
 	    </select>
  </div>
</div>

  <!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="status">Status:</label>
  	<div class="col-md-6">
    <select id="status" name="status" class="form-control"> 
      	<option value="<%=hotelArrayList.get(j).getBooking_status() %>" selected="selected"><%=hotelArrayList.get(j).getBooking_status() %></option>
      	<option value="available">Available</option>
      	<option value="not available">Not Available</option>
 	</select>
  </div>
</div>
<% }} %>
</fieldset>
</form>

</body>
</html>