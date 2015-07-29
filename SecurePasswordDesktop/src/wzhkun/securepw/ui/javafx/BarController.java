package wzhkun.securepw.ui.javafx;

import javafx.fxml.FXML;

public class BarController {

	@FXML
	public void safeBox() {
		MainApplication.getMainApplication().showPasswordBoxScene();
	}

	@FXML
	public void changePassword() {
		MainApplication.getMainApplication().showChangePassword();
	}

	@FXML
	public void cloudSync() {
		MainApplication.getMainApplication().showCloudSync();
	}

	@FXML
	public void import_() {
		MainApplication.getMainApplication().showImport();
	}

	@FXML
	public void export() {
		MainApplication.getMainApplication().showExport();
	}
}
