package account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.dbOperation.DBOperation;

@WebServlet("/deleteAdmin")
public class DeleteAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteAdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int admin_id = Integer.parseInt(request.getParameter("id"));
		
		DBOperation dbOpreation = new DBOperation();
		dbOpreation.deleteAdmin(request,response,admin_id);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
