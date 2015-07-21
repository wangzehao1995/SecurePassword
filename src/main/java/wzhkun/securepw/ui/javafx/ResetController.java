package wzhkun.securepw.ui.javafx;

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
			BLServiceManager.getResetBL().reset(password.getText());
		}else if(result==ButtonType.CANCEL){
			cancel();
		}
	}

	@FXML
	public void cancel() {
		MainApplication.getMainApplication().showLastScene();
	}

}
