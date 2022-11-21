package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCLearnDeleteDataAndOptimization {

	public static void main(String[] args) {
		
		myDeleteMethod("delete from authors where id=19","a","root","1234");
	}
	
		private static void myDeleteMethod(String query, String database,String username,String password) {
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
