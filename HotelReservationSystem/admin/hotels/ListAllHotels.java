package hotels;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.dbOperation.HotelsDBOperation;

@WebServlet("/listAllHotels")
public class ListAllHotels extends HttpServlet {

	private static final long serialVersionUID = 1L;
    public ListAllHotels() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
     HotelsDBOperation ros = new HotelsDBOperation();
   	  ros.listAllHotels(request, response);	
    }
	
}
