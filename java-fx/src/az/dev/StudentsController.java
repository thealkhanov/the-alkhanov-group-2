package az.dev;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class StudentsController implements Initializable{
	
	@FXML
	private TextField studentRegisterName;
	
	@FXML
	private TextField studentRegisterSurname;
	
	@FXML
	private TextField studentRegisterPhone;
	
	@FXML
	private TextField studentRegisterAddress;
	
	@FXML
	private DatePicker studentBirthday;
	
	@FXML
	private ComboBox<String>studentNationality;
	
	
	@FXML
	private void saveStudentsToDatabase() {
		
		Connection conn=null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection
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
			
			LocalDate birthday = studentBirthday.getValue();
			LocalDate indikiTarix=LocalDate.now();
			if(birthday.isAfter(indikiTarix)) {
				Utility.showMessage("Xəbərdarlıq", "Doğum tarixi gələcək tarix seçilə bilməz !", 10, Pos.BASELINE_RIGHT);
				return;
			}
			
			stmt.executeUpdate("insert into students (name,surname,phone,address,birthday) values ('"+ad+"','"+soyad+"','"+telefon+"','"+ünvan+"','"+birthday+"');");
			
			ResultSet rs = stmt.executeQuery("select * from students order by id desc");
			while(rs.next()) {
				String ad1 = rs.getString("name");
				String soyad1 = rs.getString("surname");
				String tel1 = rs.getString("phone");
				String unvan1 = rs.getString("address");
				Date d=rs.getDate("birthday");
				LocalDate tevellud=null; 
				if(d==null) {
					
				}else {
					tevellud = d.toLocalDate();
				}
				
				System.out.printf("ad: %s, soyad: %s, telefon: %s, adres: %s,təvəllüd: %s",ad1,soyad1,tel1,unvan1,tevellud);
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		{
			studentNationality.getItems().add("Azərbaycanlı");
			studentNationality.getItems().add("Türk");
			studentNationality.getItems().add("İngilis");
			studentNationality.getItems().add("Argentinalı");
			studentNationality.getItems().add("Alman");
			studentNationality.getItems().add("Yapon");
			studentNationality.getSelectionModel().select(0);  //proqram açılanda hansı milliyyət gəlsin
			studentNationality.getSelectionModel().getSelectedItem();  //comboBox-dan məlumatları götürmək
		}
		
	}
	

}
