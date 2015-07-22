package wzhkun.securepw.ui.javafx;

import java.io.File;
import java.io.IOException;

import javax.crypto.BadPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.ui.javafx.alert.UnableToAccessFileAlert;
import wzhkun.securepw.ui.javafx.alert.WrongPasswordAlert;
import wzhkun.securepw.ui.javafx.alert.WrongSafeFileAlert;

public class ImportController {
	@FXML
	TextField filePath;
	@FXML
	CheckBox useMyPassword;
	@FXML
	PasswordField password;

	@FXML
	public void import_() {
		File file = new File(filePath.getText());
		try {
			if (useMyPassword.isSelected()) {
				BLServiceManager.getPasswordSafeBL().import_(file);
			} else {
				BLServiceManager.getPasswordSafeBL().import_(file, password.getText());
			}
		} catch (BadPaddingException e) {
			new WrongPasswordAlert().showAndWait();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			new WrongSafeFileAlert().showAndWait();
			e.printStackTrace();
		} catch (IOException e) {
			new UnableToAccessFileAlert().showAndWait();
			e.printStackTrace();
		}
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
