package az.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentsController {
	
	@FXML
	private TextField studentRegisterName;
	
	@FXML
	private TextField studentRegisterSurname;
	
	@FXML
	private TextField studentRegisterPhone;
	
	@FXML
	private TextField studentRegisterAddress;
	
	@FXML
	private void saveStudentsToDatabase() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/students_fx","root","1234");
			Statement stmt = conn.createStatement();
			
			String ad=studentRegisterName.getText();
			String soyad=studentRegisterSurname.getText();
			String telefon=studentRegisterPhone.getText();
			String ünvan=studentRegisterAddress.getText();
			
			stmt.executeUpdate("insert into students (name,surname,phone,address) values ('"+ad+"','"+soyad+"','"+telefon+"','"+ünvan+"');");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
