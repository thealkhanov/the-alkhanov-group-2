package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCLearnPreparedStatementSelectData {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a", "root", "1234");
			String searchText = "Paulo Coelho";

			PreparedStatement stmt = conn.prepareStatement("select * from authors where name =?");

			stmt.setString(1, searchText);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				System.out.println("id: " + id + ", name: " + name + ", phone:" + phone);
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
