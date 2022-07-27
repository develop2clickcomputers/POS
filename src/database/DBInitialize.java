package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitialize {

	public static Statement statement;
	public void DBInitialize() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		
		
	 // Load the JDBC driver
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	System.out.println("Driver loaded");
	// Connect to a database
	//Connection connection = DriverManager.getConnection ("jdbc:mysql://127.0.0.1:3306/ucsmpos","root","");
	/*Personal MySQL*/
	Connection connection = DriverManager.getConnection ("jdbc:mysql://127.0.0.1:3306/ucsmpos","root","SanMar@2021");
	System.out.println("Database connected"); // Create a statement
	   statement = connection.createStatement();
	  // Execute a statement
	}
	  
}
