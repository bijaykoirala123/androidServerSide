package account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.dbOperation.DBOperation;
import model.Admin;
import model.Hotel;

@WebServlet("/addAdmin")
public class AddAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
		Hotel hotel = new Hotel();
		hotel.setHotel_id(hotel_id);
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setEmail(email);
		admin.setPassword(password);
		
		DBOperation dbOperation = new DBOperation();
		dbOperation.insertAdmin(request,response,admin,hotel);
	}

}
