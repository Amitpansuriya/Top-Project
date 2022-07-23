package SDBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class StallionDBC {
	
	public static Connection DataConnection() {
		Connection conn= null;
		try {
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stallion","root","");
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return conn;
	}
}
