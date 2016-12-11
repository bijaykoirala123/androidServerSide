package account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import account.dbOperation.DBOperation;
import model.Admin;

@WebServlet("/updateAdmin")
public class updateAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public updateAdminController() {
        super();
   }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int admin_id = Integer.parseInt(request.getParameter("admin_id"));
		int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
		DBOperation dbOperation = new DBOperation();
		dbOperation.updateAdminForm(request,response,admin_id, hotel_id);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
		int admin_id = Integer.parseInt(request.getParameter("admin_id"));
				
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Admin admin = new Admin();
		admin.setUserId(admin_id);
		admin.setUsername(username);
		admin.setEmail(email);
		admin.setPassword(password);
		admin.setHotel_id(hotel_id);

		DBOperation dbOperation = new DBOperation();
		dbOperation.updateAdmin(request, response, admin);
	}

}
