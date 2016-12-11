package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectModel {
	
	private String dbURL = "jdbc:mysql://localhost:3306/hotelreservationsystem";
    private String dbUserName = "root";
    private String dbPassword = "";
	
    public String getDbURL() {
		return this.dbURL;
	}

	public String getDbUserName() {
		return this.dbUserName;
	}
	public String getDbPassword() {
		return this.dbPassword;
	}
	
	
	 public Connection getConnection() 
	    {
	        Connection conn = null;
	        try {
	        	Class.forName("com.mysql.jdbc.Driver").newInstance();
	          conn = DriverManager.getConnection(getDbURL(), getDbUserName(),
	              getDbPassword());
	          }
	        catch (SQLException e) {
	          System.out.println("Could not connect to DB");
	          e.printStackTrace();
	          } catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return conn;
	    }
	    /*
	     * Close open database connection
	     */
	    public void closeConnection(Connection conn) 
	    {
	        if (conn != null) 
	        {
	            try 
	            { 
	                conn.close(); 
	            }
	          catch (SQLException e) {
	            System.out.println("Enable to close DB connection");
	            e.printStackTrace(); }
	        }
	 
	
	    }
}
