package wzhkun.securepw.ui.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;

public class ImportController {
	
	@FXML
	CheckBox useMyPassword;
	@FXML
	PasswordField password;

	@FXML
	public void import_() {
		
	}

	@FXML
	public void cancel() {
		password.setText("");
		MainApplication.getMainApplication().showPasswordBoxScene();
	}

	@FXML
	public void chooseFile() {

	}
	
}
