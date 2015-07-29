package wzhkun.securepw.ui.javafx;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.core.PasswordItem;

public class PasswordBoxController implements Initializable{
	
	@FXML
	VBox box;
	
	public void clean() {
		box.getChildren().clear();
	}
	
	public void addPasswordItem(PasswordItem item) {
		ObjectAndController<Pane, PasswordItemController> itemPane = UIFactory.getUIFactory().getPasswordItemPane();
		itemPane.getController().setPasswordItem(item);
		box.getChildren().add(itemPane.getObejct());
	}
	
	public void display() {
		clean();
		Set<PasswordItem> items = BLServiceManager.getPasswordSafeBL().getPasswordItems();
		for (PasswordItem item : items) {
			addPasswordItem(item);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BLServiceManager.getPasswordSafeBL().setSyncFile(new File(BLServiceManager.getSettingBL().getSyncFilePath()));
	}
}
