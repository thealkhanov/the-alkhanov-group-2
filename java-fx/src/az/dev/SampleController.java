package az.dev;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {
	
	@FXML
	private TextField studentNameText;
	
	@FXML
	private Label studentNameLabel;
	
	@FXML
	private void saveStudent() {
		studentNameLabel.setText("Salam, adiniz: "+studentNameText.getText());
		
		
	}
	
}
