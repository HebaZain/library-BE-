package libraryManApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class getConnectionLibrary {
	
	private String DB_URL="jdbc:postgresql://localhost:5432/Library"; //Library
	private String userName="postgres";
	private String password="database";
	
	public Connection connect() throws SQLException{
			Connection con=DriverManager.getConnection(DB_URL,userName,password);
			System.out.println("connected successfully");
			return con;
			
	}
}
