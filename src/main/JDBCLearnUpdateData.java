package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCLearnUpdateData {

	public static void main(String[] args) {
		
		myUpdateMethod("update authors set phone=741 where id=15;","a","root","1234");
	}
	
		private static void myUpdateMethod(String query, String database,String username,String password) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn=DriverManager.getConnection
						("jdbc:mysql://localhost:3306/"+database+"",username,password);
				Statement stmt = conn.createStatement();
				
				stmt.executeUpdate(query);
				
				stmt.close();
				conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
