package account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import account.dbOperation.DBOperation;
import model.User;

@WebServlet("/updateUser")
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int guest_id = Integer.parseInt(request.getParameter("id"));
		
		DBOperation dbOperation = new DBOperation();
   		dbOperation.listSelectedUser(request, response, guest_id);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int guest_id = Integer.parseInt(request.getParameter("guest_id"));
		String guest_name = request.getParameter("guest_name");
		String guest_email = request.getParameter("guest_email");
		String guest_phone_number = request.getParameter("guest_ph_no");
		String guest_gender = request.getParameter("gender");
		String guest_location = request.getParameter("address");
		String guest_password = request.getParameter("guest_password");
		
		User user = new User();
		user.setGuest_id(guest_id);
		user.setGuest_name(guest_name);
		user.setGuest_email(guest_email);
		user.setGuest_gender(guest_gender);
		user.setGuest_location(guest_location);
		user.setGuest_phone_number(guest_phone_number);
		user.setGuest_password(guest_password);
		
		DBOperation dbOperation = new DBOperation();
		dbOperation.updateSelectedUser(request, response,user);
	}
}
