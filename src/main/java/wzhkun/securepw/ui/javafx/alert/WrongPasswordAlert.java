package wzhkun.securepw.ui.javafx.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WrongPasswordAlert {
	public void showAndWait(){
		new Alert(AlertType.ERROR,"Wrong Password").showAndWait();
	}
}
