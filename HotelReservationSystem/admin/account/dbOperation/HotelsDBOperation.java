package account.dbOperation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Countries;
import model.DBConnectModel;
import model.Hotel;

public class HotelsDBOperation {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private ArrayList<Countries> countriesArrayList;
	private ArrayList<Hotel> hotelArrayList;

	public HotelsDBOperation() {
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
	public void listCountry(){
		countriesArrayList = new ArrayList<Countries>();
			try {
				this.ps = this.con.prepareStatement("select country_name from countries");
				this.rs = this.ps.executeQuery();
				
				while(this.rs.next()){
					Countries countries = new Countries();
					countries.setCountry_name(this.rs.getString("country_name"));
					countriesArrayList.add(countries);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void listCountries(HttpServletRequest request,HttpServletResponse response){
		listCountry();
		try{	
			request.setAttribute("countriesArrayList", countriesArrayList);
			request.getRequestDispatcher("/adminHotels/addHotels.jsp").forward(request, response);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Hotel> listHotels() {
		
		hotelArrayList = new ArrayList<Hotel>();
		try {
			this.ps = this.con.prepareStatement("SELECT * FROM hotels");
			this.rs = this.ps.executeQuery();
		
			while(this.rs.next()){
				Hotel hotel = new Hotel();
				hotel.setHotel_id(this.rs.getInt("hotel_id"));
				hotel.setHotels_name(this.rs.getString("hotels_name"));
				hotel.setLocation(this.rs.getString("location"));
				hotel.setBooking_status(this.rs.getString("booking_status"));
				hotelArrayList.add(hotel);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotelArrayList;
	}


	public void insertHotels(HttpServletRequest request, HttpServletResponse response, Hotel hotels) {
		
		try {
			this.ps = this.con.prepareStatement("INSERT INTO hotels(hotels_name,location,booking_status) VALUES(?,?,?)");
			this.ps.setString(1, hotels.getHotels_name());
			this.ps.setString(2, hotels.getLocation());
			this.ps.setString(3, hotels.getBooking_status());
			int affected_rows = this.ps.executeUpdate();
			
			listHotels();
			if(affected_rows>0){
				request.setAttribute("message","hotel has been inserted successfully.");
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.getRequestDispatcher("/adminHotels/editHotels.jsp").forward(request, response);
			}else{
				request.setAttribute("message","insertion failed");
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.getRequestDispatcher("/adminHotels/editHotels.jsp").forward(request, response);
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

	public void hotelUpdateForm(HttpServletRequest request, HttpServletResponse response, int hotel_id) {
		
		hotelArrayList = new ArrayList<Hotel>();
		listCountry();
		try {
					this.ps = this.con.prepareStatement("SELECT * FROM hotels WHERE hotel_id=?");
					this.ps.setInt(1, hotel_id);
					this.rs = this.ps.executeQuery();
					
					while(this.rs.next()){
						Hotel hotel = new Hotel();
						hotel.setHotel_id(this.rs.getInt("hotel_id"));
						hotel.setHotels_name(this.rs.getString("hotels_name"));
						hotel.setLocation(this.rs.getString("location"));
						hotel.setBooking_status(this.rs.getString("booking_status"));
						hotelArrayList.add(hotel);
					}
					
					request.setAttribute("hotelArrayList", hotelArrayList);
					request.setAttribute("countriesArrayList", countriesArrayList);
					request.getRequestDispatcher("/adminHotels/updateHotels.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateHotel(HttpServletRequest request, HttpServletResponse response, Hotel hotel) {
		
		try {
			this.ps = this.con.prepareStatement(
							"UPDATE hotels SET " + "hotels_name=?," + "location=?," + "booking_status=?"
							+" WHERE hotel_id=?"
									);
			this.ps.setString(1,hotel.getHotels_name());
			this.ps.setString(2,hotel.getLocation());
			this.ps.setString(3,hotel.getBooking_status());
			this.ps.setInt(4,hotel.getHotel_id());
			int affectedRows = this.ps.executeUpdate();
			
			listHotels();
			
			if(affectedRows>0){
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.setAttribute("message","hotel updated successfully.");
				request.getRequestDispatcher("/adminHotels/editHotels.jsp").forward(request, response);;
			}else{
				request.setAttribute("message","Couldn't update Hotel.");
				request.getRequestDispatcher("/adminHotels/editHotels.jsp");
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

	public void listAllHotels(HttpServletRequest request, HttpServletResponse response) {
		listHotels();
		try {
			request.setAttribute("hotelArrayList", hotelArrayList);
			request.getRequestDispatcher("/adminHotels/editHotels.jsp").forward(request, response);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};		
	}

	public void deleteHotel(HttpServletRequest request, HttpServletResponse response, int hotel_id) {
	
		try {
			this.ps = this.con.prepareStatement("DELETE FROM hotels WHERE hotel_id=?");
			this.ps.setInt(1, hotel_id);
			int affected_rows = this.ps.executeUpdate();

			this.ps = this.con.prepareStatement("DELETE FROM admin WHERE hotel_id=?");
			this.ps.setInt(1, hotel_id);
			this.ps.executeUpdate();
			
			listHotels();
			
			if(affected_rows>0){
			request.setAttribute("message", "Hotel deleted successfully.");	
			request.setAttribute("hotelArrayList", hotelArrayList);
			request.getRequestDispatcher("/adminHotels/editHotels.jsp").forward(request, response);
			}else{
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.setAttribute("message", "Unable to delete Hotel.");
				request.getRequestDispatcher("/adminHotels/editHotels.jsp").forward(request, response);
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
}
