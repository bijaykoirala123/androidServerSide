<%@page import="java.util.ArrayList"%>
<%@page import="model.Hotel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Hotels</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</head>
<body>

<style>
.trash { color:rgb(209, 91, 71); }
.flag { color:rgb(248, 148, 6); }
.panel-body { padding:0px; }
.panel-footer .pagination { margin: 0; }
.panel .glyphicon,.list-group-item .glyphicon { margin-right:5px; }
.panel-body .radio, .checkbox { display:inline-block;margin:0px; }
.panel-body input[type=checkbox]:checked + label { text-decoration: line-through;color: rgb(128, 144, 160); }
.list-group-item:hover, a.list-group-item:focus {text-decoration: none;background-color: rgb(245, 245, 245);}
.list-group { margin-bottom:0px; }
</style>

<%@ include file="../navbar.html" %>

<a href="/HotelReservationSystem/countries" style="color:#111;display:inline-block;">
<span class="glyphicon glyphicon-plus" style="margin-right:0px;"></span>Add Hotels</a></span>

<script type="text/javascript">$(document).ready(function(){

	 $(".close").click(function(){
	        $(".alert-warning").hide();
	    });

	});
</script>

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
                               
   <table class="table table-striped">
     <thead class="thead-inverse">
      <tr class="warning">
      	<th>SN</th>
        <th>Hotels Name:</th>
        <th>Hotels Location:</th>
        <th>Booking Status:</th>
        <th class="pull-right">Edit/Delete</th>
      </tr>
    </thead>
 
<%
ArrayList<Hotel> hotelList = (ArrayList<Hotel>)request.getAttribute("hotelArrayList");

if(hotelList!=null){
	for(int i=0; i<hotelList.size();i++){
%>

    <tbody>
      <tr class="success">
      	<td><%=i+1 %></td>
        <td><%=hotelList.get(i).getHotels_name() %></td>
        <td><%=hotelList.get(i).getLocation() %></td>
        <td><%=hotelList.get(i).getBooking_status() %></td>
        <td>
      <div class="pull-right action-buttons">
        <a href="/HotelReservationSystem/updateHotels?id=<%=hotelList.get(i).getHotel_id() %>">
        <span class="glyphicon glyphicon-pencil"></span></a>
        <a href="/HotelReservationSystem/deleteHotels?id=<%=hotelList.get(i).getHotel_id() %>" class="trash">
        <span class="glyphicon glyphicon-trash"></span></a>
      </div>
        </td>
       </tr>
    </tbody>                      
<%}}%>         
		 </table> 
            
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-md-6">
                            <h6>
                                Total Count <span class="label label-info">25</span></h6>
                        </div>
                        <div class="col-md-6">
                            <ul class="pagination pagination-sm pull-right">
                                <li class="disabled"><a href="javascript:void(0)">«</a></li>
                                <li class="active"><a href="javascript:void(0)">1 <span class="sr-only">(current)</span></a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="javascript:void(0)">»</a></li>
                            </ul>
                        </div>
                    </div>
                   </div>
</body>
</html>