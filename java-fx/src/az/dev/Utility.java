package az.dev;

import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;

public class Utility {

	public static void showMessage(String title,String message, int duraction, Pos p) {
		Notifications.create()
		.title(title)
		.text(message)
		.position(p)
		.hideAfter(javafx.util.Duration.seconds(duraction))
		.showInformation();
	}
	
}
