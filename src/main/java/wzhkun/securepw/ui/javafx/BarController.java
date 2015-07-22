package wzhkun.securepw.ui.javafx;

import javafx.fxml.FXML;

public class BarController {

	@FXML
	public void safeBox() {
		MainApplication.getMainApplication().showPasswordBoxScene();
	}

	@FXML
	public void changePassword() {
		MainApplication.getMainApplication().showChangePasswordScene();
	}

	@FXML
	public void cloudSync() {
	}

	@FXML
	public void import_() {
	}

	@FXML
	public void export() {
	}
}
