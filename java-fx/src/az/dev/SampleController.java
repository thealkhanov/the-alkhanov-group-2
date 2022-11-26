package az.dev;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController {
	
	
	@FXML
	private TextField studentNameText;
	
	@FXML
	private Label studentNameLabel;
	
	@FXML
	private void saveStudent() {
		studentNameLabel.setText("Salam, "+studentNameText.getText());
	
	}
	
	@FXML
	private void openLoginPage() {
		
		try {
			
			Stage s=new Stage();
			s.initModality(Modality.APPLICATION_MODAL);
			s.setTitle("Mənim giriş səhifəm");
			AnchorPane a=FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene=new Scene(a);
			s.setScene(scene);
			s.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
