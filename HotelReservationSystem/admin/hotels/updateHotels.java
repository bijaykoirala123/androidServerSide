package hotels;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.dbOperation.HotelsDBOperation;
import model.Hotel;

@WebServlet("/updateHotels")
public class updateHotels extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateHotels() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int hotel_id = Integer.parseInt(request.getParameter("id"));
			
		HotelsDBOperation roomsOperation = new HotelsDBOperation();
		roomsOperation.hotelUpdateForm(request,response,hotel_id);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Hotel hotel = new Hotel();
		hotel.setHotel_id( Integer.parseInt(request.getParameter("hotel_id")));
		hotel.setHotels_name(request.getParameter("hotelname"));
		hotel.setLocation(request.getParameter("location"));
		hotel.setBooking_status( request.getParameter("status"));
		
		HotelsDBOperation roomsOperation = new HotelsDBOperation();
		roomsOperation.updateHotel(request,response,hotel);
	}

}
