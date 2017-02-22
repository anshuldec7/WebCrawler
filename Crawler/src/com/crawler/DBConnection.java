package com.crawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	Connection connect = null;
	Statement stmt = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://aws.cbmuqc9mcupo.us-east-1.rds.amazonaws.com:3306/crawler";
	  
	// Database credentials
	static final String USER = "";
	static final String PASS = "";
	   
	
	public  DBConnection(){
		 try {
	         Class.forName(JDBC_DRIVER);
	         this.connect = DriverManager.getConnection(DB_URL, USER, PASS);
	         
		 } 
		 catch (Exception e) {
	       
		 }
	}
	public void insertData(String url, int count) {
		try {
			stmt = connect.createStatement();
			String sql = "INSERT INTO LinksDetails " +
	                   "VALUES ('" + url + "','" + count +"')";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void connectionClose() {

		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 connect.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(connect!=null)
		            connect.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try

	}
}
