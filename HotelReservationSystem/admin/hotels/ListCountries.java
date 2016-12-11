package hotels;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.dbOperation.HotelsDBOperation;

@WebServlet("/countries")
public class ListCountries extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListCountries() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HotelsDBOperation ros = new HotelsDBOperation();
	  ros.listCountries(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
