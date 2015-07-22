package wzhkun.securepw.ui.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.core.PasswordItem;

public class PasswordItemController {

	@FXML
	TextField app;
	@FXML
	TextField account;
	@FXML
	TextField password;

	@FXML
	public void edit() {
		MainApplication.getMainApplication().showPasswordItemEditorScene(getPasswordItem());
	}

	@FXML
	public void delete() {
		try {
			ButtonType type = new Alert(AlertType.CONFIRMATION,"Are you sure you want to delete "+app.getText()+" "+account.getText()).showAndWait().get();
			if (type == ButtonType.OK) {
				BLServiceManager.getPasswordSafeBL().removePasswordItem(getPasswordItem());
				MainApplication.getMainApplication().refreshPasswordBox();
			}
		} catch (IOException e) {
			new UnableToAccessFileAlert().showAndWait();
			e.printStackTrace();
		}
	}

	public PasswordItem getPasswordItem() {
		return new PasswordItem(app.getText(), account.getText(), password.getText());
	}

	public void setPasswordItem(PasswordItem item) {
		if (item.getApp() != null) {
			this.app.setText(item.getApp());
		}

		if (item.getAccount() != null) {
			this.account.setText(item.getAccount());
		}

		if (item.getPassword() != null) {
			this.password.setText(item.getPassword());
		}
	}
}
