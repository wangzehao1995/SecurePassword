package wzhkun.securepw.ui.javafx;

import java.io.IOException;

import javax.crypto.BadPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import wzhkun.securepw.bl.BLServiceManager;

public class LoginController {
	
	@FXML
	PasswordField password;
	
	@FXML
	public void login(){
		try {
			BLServiceManager.getLoginBL().login(password.getText());
			MainApplication.getMainApplication().showPasswordBoxScene();
		} catch (BadPaddingException e) {
			new Alert(AlertType.ERROR,"Wrong Password").showAndWait();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			new Alert(AlertType.ERROR,"There's something wrong with storage file.\nTry \"reset\".").showAndWait();
			e.printStackTrace();
		} catch (IOException e) {
			new Alert(AlertType.ERROR,"Can't read the storage file.\n"+e.getMessage()).showAndWait();
			e.printStackTrace();
		}
	}
	@FXML
	public void reset(){
		MainApplication.getMainApplication().showResetScene();
	}

}
