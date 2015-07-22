package wzhkun.securepw.ui.javafx.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WrongSafeFileAlert {
	public void showAndWait(){
		new Alert(AlertType.ERROR,"There's something wrong with storage file.\nTry \"reset\".").showAndWait();
	}
}
