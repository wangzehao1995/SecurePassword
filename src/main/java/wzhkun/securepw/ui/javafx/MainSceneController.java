package wzhkun.securepw.ui.javafx;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.PasswordSafeBL;
import wzhkun.securepw.core.PasswordItem;

public class MainSceneController {
	private Map<Pane, PasswordItemController> controllerMap = new HashMap<>();
	private PasswordSafeBL bl = BLServiceManager.getPasswordSafeBL();

	@FXML
	private VBox box;

	@FXML
	public void add() {
		MainApplication.getMainApplication().showPasswordItemEditorScene();
	}

	@FXML
	public void close() {
		bl.setPasswordSafe(null);
		MainApplication.getMainApplication().showLastScene();
	}

	private void clean() {
		box.getChildren().clear();
		controllerMap.clear();
	}

	public void display() {
		clean();
		Set<PasswordItem> items = bl.getPasswordItems();
		for (PasswordItem item : items) {
			addPasswordItem(item);
		}
	}

	private void addPasswordItem(PasswordItem item) {
		ObjectAndController<Pane, PasswordItemController> itemPane = UIFactory.getUIFactory().getPasswordItemPane();
		itemPane.getController().setPasswordItem(item);
		controllerMap.put(itemPane.getObejct(), itemPane.getController());
		box.getChildren().add(itemPane.getObejct());
	}

	@FXML
	private BorderPane borderPane;
	private Pane bar;
	private boolean barShowed = false;

	@FXML
	public void bar() {
		if (barShowed) {
			hideBar();
		} else {
			showBar();
		}
	}

	public void setBar(Pane bar) {
		this.bar = bar;
	}

	public void showBar() {
		borderPane.setLeft(bar);
		barShowed = true;
	}

	public void hideBar() {
		borderPane.setLeft(null);
		barShowed = false;
	}
}
