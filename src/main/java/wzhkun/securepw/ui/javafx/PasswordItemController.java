package wzhkun.securepw.ui.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import wzhkun.securepw.core.PasswordItem;

public class PasswordItemController {

	@FXML
	TextField app;
	@FXML
	TextField account;
	@FXML
	TextField password;

	@FXML
	public void edit(){
		
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
