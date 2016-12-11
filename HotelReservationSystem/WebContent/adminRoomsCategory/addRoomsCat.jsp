<%@page import="model.Hotel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rooms Category</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</head>
<body>
<%@ include file="../navbar.html" %>    

<a href="/HotelReservationSystem/listRoomsCat" style="color:#111;display:inline-block;">
<span class="glyphicon glyphicon-backward" style="margin-right:0px;"></span>Back</a>                        

 <form class="form-horizontal" action="/HotelReservationSystem/addRoomsCategory" method="post" enctype="multipart/form-data">
<fieldset>

<!-- Form Name -->
<legend>Insert Rooms Category</legend>

  

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="hotels_name">Select Hotel:</label>
  	<div class="col-md-4">
    <select id="hotels_name" name="hotels_id" class="form-control"> 
 		<option disabled="disabled">select any</option>
 
		 <%ArrayList<Hotel> hotelArrayList =(ArrayList<Hotel>)request.getAttribute("hotelArrayList");  %>
		  <% 
		  if(hotelArrayList!=null){
		  %>
	 	 <% for(int i=0; i<hotelArrayList.size();i++){ %>
      	<option value="<%=hotelArrayList.get(i).getHotel_id()%>"><%=hotelArrayList.get(i).getHotels_name() %> </option>
 		<% } }%>
    </select>
  </div>
</div>


<!-- Text input-->
<div class="form-group form-inline">  
  <label class="col-md-4 control-label" for="category_name">Room Category Name:</label>  
  <div class="col-md-4">
  <input id="category_name" name="category_name" type="text" placeholder="Enter Room Category" class="form-control" required="">
  </div>
 </div>

<!-- textarea -->
 <div class="form-group">
    <label for="description" class="col-md-4 control-label">Description:</label>
    <div class="col-md-4">
    <textarea class="form-control" id="description" rows="5" cols="50" name="category_description"></textarea>
  	</div>
  </div>
  
<!-- browse file -->
<div class="form-group">
	<div class="col-md-offset-4 col-md-4">
	<input type="file" name="category_image"/>
	</div>
</div>
<!-- browse file -->  

<div class="form-group">
   <div class="col-md-offset-9">
    <button id="submit" name="submit" class="btn btn-inverse ">Save</button>  
   </div>
</div>
</fieldset>
</form>

</body>
</html>