package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCLearnSelectData {

	public static void main(String[] args) {
		System.out.println("Axtarmaq istədiyinizi yazın:");
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/a","root","1234");
			Statement stmt = conn.createStatement();
			String searchText=sc.nextLine();
			ResultSet rs = stmt.executeQuery("select * from authors where concat(name,id,phone) like '%"+searchText+"%';");
			//ResultSet rs = stmt.executeQuery("select * from authors");
			
			while (rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String phone=rs.getString("phone");
				System.out.println("id: "+id+", name: "+name+", phone:"+phone);
			}
			
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
