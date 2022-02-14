package com.connectionmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {
	// define the database properties
		private static final String URL = "jdbc:mysql://localhost:3306/recipewebapp?serverTimezone=UTC";
		private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "Hamzaid#4877";
		
		private static Connection connection = null ; 
		
		// Define the static method
		
		public static Connection openConnnection() {
			// check the connection
			if(connection != null) {
				return connection;
			}else {
				try {
					// load the driver
					Class.forName(DRIVER);
					
					// Get the connection
					connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				// return connection
				return connection;
				
			}
		}

}
