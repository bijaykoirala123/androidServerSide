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

import model.Admin;
import model.DBConnectModel;
import model.Hotel;
import model.User;

public class DBOperation {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private ArrayList<User> userArrayList = null;
	private ArrayList<Admin> adminArrayList = null;
	private ArrayList<Hotel> hotelArrayList;

	public DBOperation() {
		DBConnectModel dbconnection = new DBConnectModel();
		this.con = dbconnection.getConnection();

		try {
			this.stmt = this.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.rs = this.stmt.executeQuery("SELECT * FROM guest");
		// PreparedStatement pst2 = this.con.prepareStatement("delete from
		// practice where id =?");
		// pst2.setString(1, userId);
		// rowAffected = pst2.executeUpdate();
	}

	public void disconnect() {
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addUserOperation(HttpServletRequest request, HttpServletResponse response, User user) {
		try {
		
			this.ps = this.con.prepareStatement(
					"INSERT INTO guest(guest_name,guest_email,guest_password,guest_ph_no,guest_location,guest_gender)"
							+ " VALUES" + "(?,?,?,?,?,?)");

			ps.setString(1, user.getGuest_name());
			ps.setString(2, user.getGuest_email());
			ps.setString(3, user.getGuest_password());
			ps.setString(4, user.getGuest_phone_number());
			ps.setString(5, user.getGuest_location());
			ps.setString(6, user.getGuest_gender());
			int affectedRows = ps.executeUpdate();

			////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////// after adding user, user is
			//////////////////////////////////////////////////////////////////////////////////////////// listed/////////////////////////////////////
			listUserOperation();
			///////////////////////////////////////////////////////////////////////////////////////////

			disconnect();

			if (affectedRows >= 1) {
				request.setAttribute("userArrayList", this.userArrayList);
				request.setAttribute("message", "user has been added successfully");
				request.getRequestDispatcher("adminUserController/editUserPannel.jsp").forward(request, response);
			} else {
				request.setAttribute("userArrayList", this.userArrayList);
				request.setAttribute("message", "user registration failed !!");
				request.getRequestDispatcher("adminUserController/addUserPannel.jsp").forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listUserOperation() {
		try {
			this.userArrayList = new ArrayList<User>();

			this.rs = this.stmt.executeQuery("SELECT * FROM guest");
			while (this.rs.next()) {
				User user1 = new User();
				user1.setGuest_id(rs.getInt("guest_id"));
				user1.setGuest_name(rs.getString("guest_name"));
				user1.setGuest_email(rs.getString("guest_email"));
				user1.setGuest_location(rs.getString("guest_location"));
				user1.setGuest_gender(rs.getString("guest_gender"));
				user1.setGuest_phone_number(rs.getString("guest_ph_no"));
				this.userArrayList.add(user1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listUsers(HttpServletRequest request, HttpServletResponse response) {
		listUserOperation();
		disconnect();
		try {
			request.setAttribute("userArrayList", this.userArrayList);
			request.getRequestDispatcher("adminUserController/editUserPannel.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response, int guest) {

		try {
			this.ps = this.con.prepareStatement("DELETE FROM guest WHERE guest_id=?");

			this.ps.setInt(1, guest);
			int affectedrows = this.ps.executeUpdate();

			////////////////////////////////////////////////////////////////////////////////////////////
			///////////////////// after adding user, user
			//////////////////////////////////////////////////////////////////////////////////////////// islisted/////////////////////////////////////
			listUserOperation();
			///////////////////////////////////////////////////////////////////////////////////////////
			disconnect();
			if (affectedrows >= 1) {
				request.setAttribute("userArrayList", this.userArrayList);
				request.setAttribute("message", "user has been deleted");
				request.getRequestDispatcher("adminUserController/editUserPannel.jsp").forward(request, response);
			} else {
				request.setAttribute("userArrayList", this.userArrayList);
				request.setAttribute("message", "user not deleted !!");
				request.getRequestDispatcher("adminUserController/editUserPannel.jsp").forward(request, response);
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

	public void listSelectedUser(HttpServletRequest request, HttpServletResponse response, int guest_id) {

		try {
			this.ps = this.con.prepareStatement("SELECT * FROM guest WHERE guest_id=?");
			this.ps.setInt(1, guest_id);
			this.rs = this.ps.executeQuery();

			User user = new User();
			while (rs.next()) {
				user.setGuest_id(rs.getInt("guest_id"));
				user.setGuest_name(rs.getString("guest_name"));
				user.setGuest_email(rs.getString("guest_email"));
				user.setGuest_location(rs.getString("guest_location"));
				user.setGuest_phone_number(rs.getString("guest_ph_no"));
				user.setGuest_gender(rs.getString("guest_gender"));
			}

			disconnect();
			request.setAttribute("userModel", user);
			request.getRequestDispatcher("adminUserController/updateUserPannel.jsp").forward(request, response);

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

	public void updateSelectedUser(HttpServletRequest request, HttpServletResponse response, User user) {
		try {
			this.ps = this.con
					.prepareStatement("UPDATE guest SET " + "guest_name=?," + "guest_email=?," + "guest_password=?,"
							+ "guest_ph_no=?," + "guest_location=?," + "guest_gender=?" + " WHERE guest_id=?");
			this.ps.setString(1, user.getGuest_name());
			this.ps.setString(2, user.getGuest_email());
			this.ps.setString(3, user.getGuest_password());
			this.ps.setString(4, user.getGuest_phone_number());
			this.ps.setString(5, user.getGuest_location());
			this.ps.setString(6, user.getGuest_gender());
			this.ps.setInt(7, user.getGuest_id());
			int affectedRows = this.ps.executeUpdate();

			if (affectedRows > 0) {
				request.setAttribute("message", "user updated successfully");
				listUsers(request, response);
				disconnect();
			} else {
				request.setAttribute("message", "user update failed!!! ");
				listUsers(request, response);
				disconnect();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listAdmin() {
		try {
			this.adminArrayList = new ArrayList<Admin>();

			this.rs = this.stmt.executeQuery("SELECT * FROM admin");
			while (this.rs.next()) {
				Admin admin = new Admin();
				admin.setUserId(rs.getInt("admin_id"));
				admin.setUsername(rs.getString("admin_name"));
				admin.setEmail(rs.getString("admin_email"));
				admin.setPassword(rs.getString("admin_password"));
				admin.setHotel_id(rs.getInt("hotel_id"));
				this.adminArrayList.add(admin);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listAllAdmin(HttpServletRequest request, HttpServletResponse response) {

		adminJoinsHotel();
		disconnect();
		try {		
				request.setAttribute("adminArrayList", adminArrayList);
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.getRequestDispatcher("adminUserController/editAdminPanel.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertAdmin(HttpServletRequest request, HttpServletResponse response, Admin admin, Hotel hotel) {
		try {

			this.ps = this.con.prepareStatement(
					"INSERT INTO admin(admin_name,admin_email,admin_password,hotel_id) VALUES(?,?,?,?)");
			this.ps.setString(1, admin.getUsername());
			this.ps.setString(2, admin.getEmail());
			this.ps.setString(3, admin.getPassword());

			this.ps.setInt(4, hotel.getHotel_id());
			int affected_rows = this.ps.executeUpdate();
			
			///////////////////////////////////////////////////////////////
			adminJoinsHotel();
			////////////////////////////////////////////////////////////////
			disconnect();
			if (affected_rows > 0) {
				request.setAttribute("adminArrayList", adminArrayList);
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.setAttribute("message", "admin added successfully");
				request.getRequestDispatcher("adminUserController/editAdminPanel.jsp").forward(request, response);
			} else {
				request.setAttribute("adminArrayList", adminArrayList);
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.setAttribute("message", "admin insertion failed !!!");
				request.getRequestDispatcher("adminUserController/editAdminPanel.jsp").forward(request, response);
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

	public void adminJoinsHotel() {
		try {
//			this.ps = this.con.prepareStatement(
//					"SELECT admin_id,admin_name,admin_email,admin_password,hotels_name"
//							+ " FROM admin Left JOIN hotels" + " ON admin.hotel_id = hotels.hotel_id");
//			
			this.ps = this.con.prepareStatement("SELECT admin_id,admin_name,admin_email,admin_password,hotels.hotel_id,hotels.hotels_name from admin "
      +"inner join hotels on admin.hotel_id = hotels.hotel_id");
			this.rs = this.ps.executeQuery();
	
			adminArrayList = new ArrayList<Admin>();
			hotelArrayList = new ArrayList<Hotel>();
			
			while(this.rs.next()){
				
				Admin admin = new Admin();
				admin.setUserId(this.rs.getInt("admin_id"));
				admin.setUsername(this.rs.getString("admin_name"));
				admin.setEmail(this.rs.getString("admin_email"));
				admin.setPassword(this.rs.getString("admin_password"));
				admin.setHotel_id(rs.getInt("hotel_id"));
				
				Hotel hotel = new Hotel();
				hotel.setHotel_id(rs.getInt("hotel_id"));
				hotel.setHotels_name(rs.getString("hotels_name"));;
			
			adminArrayList.add(admin);
			hotelArrayList.add(hotel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAdmin(HttpServletRequest request, HttpServletResponse response, int admin_id) {

		try {
			this.ps = this.con.prepareStatement("DELETE FROM admin WHERE admin_id=?");

			this.ps.setInt(1, admin_id);
			int affectedrows = this.ps.executeUpdate();

			////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////// admin is listed/////////////////////////////////////
			adminJoinsHotel();
			disconnect();
				if (affectedrows >= 1) {
					request.setAttribute("adminArrayList", adminArrayList);
					request.setAttribute("hotelArrayList", hotelArrayList);
				request.setAttribute("message", "admin has been deleted");
				request.getRequestDispatcher("adminUserController/editAdminPanel.jsp").forward(request, response);
			} else {
				request.setAttribute("adminArrayList", adminArrayList);
				request.setAttribute("hotelArrayList", hotelArrayList);
			request.setAttribute("message", "admin not deleted !!");
				request.getRequestDispatcher("adminUserController/editAdminPanel.jsp").forward(request, response);
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

	public void updateAdminForm(HttpServletRequest request, HttpServletResponse response, int admin_id,int hotel_id) {

		try {
			this.ps = this.con.prepareStatement("SELECT * FROM admin WHERE admin_id=? AND hotel_id=?");
			this.ps.setInt(1, admin_id);
			this.ps.setInt(2, hotel_id);
			this.rs = this.ps.executeQuery();

			Admin admin = new Admin();
			while (rs.next()) {
				admin.setUserId(rs.getInt("admin_id"));
				admin.setUsername(rs.getString("admin_name"));
				admin.setEmail(rs.getString("admin_email"));
				admin.setPassword(rs.getString("admin_password"));
				admin.setHotel_id(rs.getInt("hotel_id"));
			}
			
			listHotels();
			disconnect();
			request.setAttribute("hotelArrayList", this.hotelArrayList);
			request.setAttribute("adminModel", admin);
			request.getRequestDispatcher("adminUserController/updateAdminPanel.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateAdmin(HttpServletRequest request, HttpServletResponse response, Admin admin) {
		try {
			this.ps = this.con.prepareStatement("UPDATE admin SET " + "admin_name=?," + "admin_email=?,"
					+ "admin_password=?," + "hotel_id=?"+ " WHERE admin_id=?");
			this.ps.setString(1, admin.getUsername());
			this.ps.setString(2, admin.getEmail());
			this.ps.setString(3, admin.getPassword());
			this.ps.setInt(4, admin.getHotel_id());
			this.ps.setInt(5, admin.getUserId());
			int affectedRows = this.ps.executeUpdate();
			
			adminJoinsHotel();
			disconnect();
			
			if (affectedRows > 0) {
				request.setAttribute("adminArrayList", adminArrayList);
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.setAttribute("message", "admin updated successfully");
				request.getRequestDispatcher("adminUserController/editAdminPanel.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "admin update failed!!! ");
				request.setAttribute("adminArrayList", adminArrayList);
				request.setAttribute("hotelArrayList", hotelArrayList);
				request.getRequestDispatcher("adminUserController/editAdminPanel.jsp").forward(request, response);
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

	public void listHotels() {

		try {
			this.hotelArrayList = new ArrayList<Hotel>();

			this.rs = this.stmt.executeQuery("SELECT * FROM hotels");
			while (this.rs.next()) {
				Hotel hotel = new Hotel();
				hotel.setHotel_id(rs.getInt("hotel_id"));
				hotel.setHotels_name(rs.getString("hotels_name"));
				this.hotelArrayList.add(hotel);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listAllHotels(HttpServletRequest request, HttpServletResponse response) {
		listHotels();
		try {
			disconnect();
			request.setAttribute("hotelArrayList", this.hotelArrayList);
			request.getRequestDispatcher("adminUserController/addAdminPanel.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}