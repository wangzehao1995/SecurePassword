package wzhkun.securepw.ui.javafx;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UnableToAccessFileAlert {
	public void showAndWait(){
		new Alert(AlertType.ERROR,"unable to access .safe file.").showAndWait();
	}
}
