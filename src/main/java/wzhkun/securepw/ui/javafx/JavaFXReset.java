package wzhkun.securepw.ui.javafx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import wzhkun.securepw.bl.BLServiceManager;

public class JavaFXReset {
	private Scene scene;

	public Scene getScene() {
		if (scene == null) {
			try {
				scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("Reset.fxml")));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return scene;
	}

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
