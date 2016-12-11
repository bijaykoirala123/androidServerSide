package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import DBOperation.ClientDBOperation;


@WebServlet("/ListHotels")
public class Hotels extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Hotels() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ClientDBOperation clientDBOperation = new ClientDBOperation();
		JSONObject result = clientDBOperation.listHotels();

		out.println(result.toString());
		
		out.flush();
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ClientDBOperation clientDBOperation = new ClientDBOperation();
		JSONObject result = clientDBOperation.listHotels();

		out.println(result.toString());
		
		out.flush();
		out.close();

	}
}