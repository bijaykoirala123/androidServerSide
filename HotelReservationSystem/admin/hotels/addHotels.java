package hotels;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.dbOperation.HotelsDBOperation;
import model.Hotel;

@WebServlet("/addHotels")
public class addHotels extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addHotels() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hotelname = request.getParameter("hotelname");
		String location  = request.getParameter("location");
		String booking_status	 = request.getParameter("status");
		
		Hotel hotels = new Hotel();
		hotels.setHotels_name(hotelname);
		hotels.setLocation(location);
		hotels.setBooking_status(booking_status);
		
		HotelsDBOperation rms = new HotelsDBOperation();
		rms.insertHotels(request,response,hotels);
			
	}

}
