package wzhkun.securepw.ui.javafx;

import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.core.PasswordItem;

public class PasswordBoxController {
	
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
}
