package wzhkun.securepw.ui.javafx;

import java.io.IOException;

import javax.crypto.BadPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.ui.javafx.alert.UnableToAccessFileAlert;
import wzhkun.securepw.ui.javafx.alert.WrongPasswordAlert;
import wzhkun.securepw.ui.javafx.alert.WrongSafeFileAlert;

public class LoginController {
	
	@FXML
	PasswordField password;
	
	@FXML
	public void login(){
		try {
			if(!MainApplication.LOCAL_SAFE.exists()){
				BLServiceManager.getPasswordSafeBL().reset(password.getText());
			}
			BLServiceManager.getPasswordSafeBL().login(password.getText());
			password.setText("");
			MainApplication.getMainApplication().showPasswordBoxScene();
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
	public void reset(){
		MainApplication.getMainApplication().showResetScene();
	}

}
