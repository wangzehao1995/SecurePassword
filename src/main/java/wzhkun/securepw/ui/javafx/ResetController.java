package wzhkun.securepw.ui.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import wzhkun.securepw.bl.BLServiceManager;

public class ResetController {

	@FXML
	PasswordField password;

	@FXML
	public void reset() {
		ButtonType result=new Alert(AlertType.CONFIRMATION).showAndWait().get();
		
		if (result==ButtonType.OK){
			try {
				BLServiceManager.getResetBL().reset(password.getText());
			} catch (IOException e) {
				e.printStackTrace();
				new UnableToAccessFileAlert().showAndWait();
			}
		}
		
		cancel();
	}

	@FXML
	public void cancel() {
		password.setText("");
		MainApplication.getMainApplication().showLastScene();
	}

}
