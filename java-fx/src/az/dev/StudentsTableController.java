package az.dev;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StudentsTableController implements Initializable{
	
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
	private ImageView myImage;
	
	@FXML
	private ListView<String> myListView;
	
	@FXML
	private Label rowCountLabel;
	
	@FXML
	private ComboBox<String>studentNationality;
	
	@FXML
	TableView<Student> studentsTable;
	
	@FXML
	TableColumn<Student, Integer> idColumn;
	
	@FXML
	TableColumn<Student, String> nameColumn;
	
	@FXML
	TableColumn<Student, String> surnameColumn;
	
	@FXML
	TableColumn<Student, String> phoneColumn;
	
	@FXML
	TableColumn<Student, String> addressColumn;
	
	@FXML
	TableColumn<Student, LocalDate> birthdayColumn;
	
	@FXML
	TableColumn<Student, String> nationalityColumn;
	
	
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
			
			String milliyyət = studentNationality.getSelectionModel().getSelectedItem();
			
			LocalDate birthday = studentBirthday.getValue();
			LocalDate indikiTarix=LocalDate.now();
			if(birthday.isAfter(indikiTarix)) {
				Utility.showMessage("Xəbərdarlıq", "Doğum tarixi gələcək tarix seçilə bilməz !", 10, Pos.BASELINE_RIGHT);
				return;
			}
			
			stmt.executeUpdate("insert into students (name,surname,phone,address,birthday,nationality) values ('"+ad+"','"+soyad+"','"+telefon+"','"+ünvan+"','"+birthday+"','"+milliyyət+"');");
			
			ResultSet rs = stmt.executeQuery("select * from students order by id desc");
			
			ArrayList<Student> students = new ArrayList<Student>();
			
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String ad1 = rs.getString("name");
				String soyad1 = rs.getString("surname");
				String tel1 = rs.getString("phone");
				String unvan1 = rs.getString("address");
				String milliyyet1 = rs.getString("nationality");
				Date d=rs.getDate("birthday");
				LocalDate tevellud=null; 
				if(d==null) {
					
				}else {
					tevellud = d.toLocalDate();
				}
				
				Student s = new Student(id, ad1, soyad1, tel1, unvan1, tevellud, milliyyet1);
				students.add(s);
			}
			
				ObservableList<Student> list = FXCollections.observableArrayList();
				list.addAll(students);
				studentsTable.setItems(list);
			
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
		
			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
			phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
			addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
			birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
			nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
			
		}
		
	}
	
	
	@FXML
	private void onAddNameToListView() {
		
		String name1 = studentRegisterName.getText();
		myListView.getItems().add(name1);
		rowCountLabel.setText("Tələbə sayı = "+ myListView.getItems().size());
		
	}
	
	@FXML
	private void deleteName() {
		int selectedIndex=myListView.getSelectionModel().getSelectedIndex();
		if(selectedIndex==-1) {
			Utility.showMessage("Xəbərdarlıq", "Siyahıdan seçim et !", 5, Pos.BASELINE_RIGHT);
			return;
		}
		myListView.getItems().remove(selectedIndex);
		rowCountLabel.setText("Tələbə sayı = "+ myListView.getItems().size());
	}

	@FXML
	private void selectName() {
		String gosterilecekAd = myListView.getSelectionModel().getSelectedItem();
		Utility.showMessage("Bunu cədvəldən seçdin: ", gosterilecekAd, 6, Pos.BASELINE_RIGHT);
	}
	
	@FXML
	private void editName() {
		String name1=studentRegisterName.getText();
		int selectedIndex=myListView.getSelectionModel().getSelectedIndex();
		if(selectedIndex<0) {
			Utility.showMessage("Xəbərdarlıq", "Siyahıdan redaktə üçün seçim et !", 5, Pos.BASELINE_RIGHT);
			return;
		}
		myListView.getItems().set(selectedIndex, name1);
		
	}
	
	String[] imagesMassivi = {"Java-Logo.png","jva.jpg","jdk-jre-jvm.png"};
	int i=0;
	
	@FXML  
	private void showImage() {
		File file = new File("images/"+imagesMassivi[i++]);
		if(i==imagesMassivi.length) {
			i=0;
		}
		Image image = new Image(file.toURI().toString());
		myImage.setImage(image);
	}
	
}
