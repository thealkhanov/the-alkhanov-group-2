package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCLearnInsertData {

	public static void main(String[] args) {
		//System.out.println("Axtarmaq istədiyinizi yazın:");
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/a","root","1234");
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate("insert into authors (name,phone) values \r\n"
					+ "('George Orwell','030'),\r\n"
					+ "('Fyodor Dostoyevski','033'),\r\n"
					+ "('Agatha Christie','035'),\r\n"
					+ "('Carl Sagan','042'),\r\n"
					+ "('Elon Mask','041'),\r\n"
					+ "('Con Steynbek','043'),\r\n"
					+ "('Ceyn Ostin','044'),\r\n"
					+ "('Rey Bredberi','045'),\r\n"
					+ "('Dan Brawn','046');\r\n"
					+ "");
			
			
			
			
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
