package az.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
			if(ad.trim().equals("")) {
				Utility.showMessage("Xəbərdarlıq", "Adı boş qoyma !", 3, Pos.BASELINE_RIGHT);
				return;
			}
			
			String soyad=studentRegisterSurname.getText();
			if(soyad.trim().equals("")) {
				Utility.showMessage("Xəbərdarlıq", "Soyadı boş qoyma !", 3, Pos.BASELINE_RIGHT);
				return;
			}
			
			String telefon=studentRegisterPhone.getText();
			if(telefon.trim().equals("")) {
				Utility.showMessage("Xəbərdarlıq", "Telefonu boş qoyma !", 3, Pos.BASELINE_RIGHT);
				return;
			}
			
			String ünvan=studentRegisterAddress.getText();
			if(ünvan.trim().equals("")) {
				Utility.showMessage("Xəbərdarlıq", "Ünvanı boş qoyma !", 3, Pos.BASELINE_RIGHT);
				return;
			}
			
			stmt.executeUpdate("insert into students (name,surname,phone,address) values ('"+ad+"','"+soyad+"','"+telefon+"','"+ünvan+"');");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
