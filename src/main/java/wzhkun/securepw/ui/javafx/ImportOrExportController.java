package wzhkun.securepw.ui.javafx;

import java.io.File;
import java.io.IOException;

import javax.crypto.BadPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import wzhkun.securepw.ui.javafx.alert.UnableToAccessFileAlert;
import wzhkun.securepw.ui.javafx.alert.WrongPasswordAlert;
import wzhkun.securepw.ui.javafx.alert.WrongSafeFileAlert;

public abstract class ImportOrExportController {
	@FXML
	TextField filePath;
	@FXML
	CheckBox useMyPassword;
	@FXML
	PasswordField password;

	public void doIt() {
		File file = new File(filePath.getText());
		try {
			if (useMyPassword.isSelected()) {
				doItWithMyPassword(file);
			} else {
				doItWithNewPassword(file, password.getText());
			}
			new Alert(AlertType.INFORMATION,"Completed").show();
			cancel();
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
	
	protected abstract void doItWithMyPassword(File file) throws BadPaddingException,ClassNotFoundException,IOException;
	protected abstract void doItWithNewPassword(File file,String password)throws BadPaddingException,ClassNotFoundException,IOException;
	protected abstract File getTargetFile();

	@FXML
	public void cancel() {
		password.setText("");
		MainApplication.getMainApplication().showPasswordBoxScene();
	}

	@FXML
	public void chooseFile() {
		filePath.setText(getTargetFile().getAbsolutePath());
	}

}
