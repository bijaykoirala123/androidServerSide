package account.dbOperation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.controller.ListHotels;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import model.Countries;
import model.DBConnectModel;
import model.Hotel;
import model.RoomCat;

public class RoomsDBOperation {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private ArrayList<Countries> countriesArrayList;
	private ArrayList<Hotel> hotelArrayList;
	ArrayList<RoomCat> roomCategoryList;
	
	public RoomsDBOperation() {
		DBConnectModel dbconnection = new DBConnectModel();
		this.con = dbconnection.getConnection();

		try {
			this.stmt = this.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hotels(){
		hotelArrayList = new ArrayList<Hotel>();
		HotelsDBOperation db = new HotelsDBOperation();
		hotelArrayList = db.listHotels();
	}
	public  void listHotels(HttpServletRequest request,HttpServletResponse response){
		hotels();
		try {
			request.setAttribute("hotelArrayList", hotelArrayList);
			request.getRequestDispatcher("adminRoomsCategory/addRoomsCat.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void RoomCategory(){
		
		try {
			this.ps = this.con.prepareStatement("SELECT * FROM rooms_category");
			this.rs = this.ps.executeQuery();
			roomCategoryList = new ArrayList<RoomCat>();
			while(this.rs.next()){
				RoomCat roomCat = new RoomCat();
				roomCat.setRooms_category_id(this.rs.getInt("rooms_category_id"));
				roomCat.setHotel_id(this.rs.getInt("hotel_id"));
				roomCat.setCategory_name(this.rs.getString("category_name"));
				roomCat.setCategory_image(this.rs.getString("category_image"));
				roomCat.setCategory_description(this.rs.getString("category_description"));
				roomCategoryList.add(roomCat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	public void uploadRoomCategory(RoomCat roomCat,HttpServletRequest request,HttpServletResponse response) {
		
			try {
				this.ps = this.con.prepareStatement("INSERT INTO rooms_category(hotel_id,category_name,category_image,category_description)"
						+ " VALUES(?,?,?,?)");
				this.ps.setInt(1, 	roomCat.getHotel_id());
				this.ps.setString(2, roomCat.getCategory_name());
				this.ps.setString(3, roomCat.getCategory_image());
				this.ps.setString(4, roomCat.getCategory_description());
				int result = this.ps.executeUpdate();
				
				RoomCategory();
				
				 if(result>=1){
					 request.setAttribute("message", "Category Uploaded Successfully!!!");
					 request.setAttribute("roomCategoryList", roomCategoryList);
					 request.getRequestDispatcher("/adminRoomsCategory/listRoomCategory.jsp").forward(request, response);
				  }else{
					request.setAttribute("message", "Category Upload Failed!!!");
					request.setAttribute("roomCategoryList", roomCategoryList);
					request.getRequestDispatcher("/adminRoomsCategory/listRoomCategory.jsp").forward(request, response);
				  }
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void listRoomCategory(HttpServletRequest request, HttpServletResponse response) {
		
		RoomCategory();
	
		 try {
			 request.setAttribute("roomCategoryList", roomCategoryList);
			 request.getRequestDispatcher("/adminRoomsCategory/listRoomCategory.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
