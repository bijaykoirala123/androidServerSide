package DBOperation;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.DBConnectModel;
import model.Hotel;

public class ClientDBOperation {

	private ArrayList<Hotel> hotelArrayList;
	private Connection con;
	private Statement stmt;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ClientDBOperation() {
		DBConnectModel dbconnection = new DBConnectModel();
		this.con = dbconnection.getConnection();
		try {
			this.stmt = this.con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public JSONObject listHotels() {

		try {
				this.rs = this.stmt.executeQuery("SELECT * FROM hotels");
			
//			JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
//			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
//			
//			while (this.rs.next()) {
//				JsonObjectBuilder jsonChildObject = Json.createObjectBuilder();
//				jsonChildObject.add("hotel_id", rs.getInt("hotel_id"))
//				.add("hotels_name", rs.getString("hotels_name"))
//				.add("location", rs.getString("location"))
//				.add("booking_status", rs.getString("booking_status"))
//				.build();
//				//out.println(rs.getInt("hotel_id"));
//				//out.println(rs.getString("hotels_name"));
//				
//				jsonArrayBuilder.add(jsonChildObject);
//			}
//			
//			return jsonObjectBuilder.add("hotels", jsonArrayBuilder).build();
//	
				JSONObject jsonObject = new JSONObject();
				JSONArray jsonArray = new JSONArray();
				
				while(this.rs.next()){
				JSONObject childObject = new JSONObject();
				childObject.put("hotel_id",rs.getInt("hotel_id"));
				childObject.put("hotels_name",rs.getString("hotels_name"));
				childObject.put("location", rs.getString("location"));
				childObject.put("booking_status", rs.getString("booking_status"));
				jsonArray.put(childObject);
				}
				jsonObject.put("hotels", jsonArray);
				
				this.disconnect();
				return jsonObject;
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
