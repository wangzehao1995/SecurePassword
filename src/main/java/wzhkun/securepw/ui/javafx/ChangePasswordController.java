package wzhkun.securepw.ui.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.ui.javafx.alert.UnableToAccessFileAlert;

public class ChangePasswordController {
	@FXML
	PasswordField password;

	@FXML
	public void change() {
		try {
			BLServiceManager.getPasswordSafeBL().changePassword(password.getText());
		} catch (IOException e) {
			new UnableToAccessFileAlert().showAndWait();
			e.printStackTrace();
		}

		cancel();
	}

	@FXML
	public void cancel() {
		password.setText("");
		MainApplication.getMainApplication().showPasswordBoxScene();
	}
}
