package libraryManApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class libraryConnection {
	private  String DB_URL="jdbc:postgresql://localhost:5432/Library";
	private  String userName="postgres";
	private  String password="database";
	
	public  Connection libraryCon(){
		Connection connection=null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, userName, password);
			System.out.println("connected successfully");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
	

}
