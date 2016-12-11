package hotels;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.dbOperation.DBOperation;
import account.dbOperation.HotelsDBOperation;

@WebServlet("/deleteHotels")
public class DeleteHotel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteHotel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hotel_id = Integer.parseInt(request.getParameter("id"));
		HotelsDBOperation rms = new HotelsDBOperation();
		rms.deleteHotel(request,response,hotel_id);
		
	}
}
