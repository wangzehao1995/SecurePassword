package wzhkun.securepw.ui.javafx;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.PasswordSafeBL;
import wzhkun.securepw.core.PasswordItem;

public class PasswordBoxController {
	private Map<Pane, PasswordItemController> controllerMap=new HashMap<>();
	private PasswordSafeBL bl=BLServiceManager.getPasswordSafeBL();
	
	@FXML
	private VBox box;
	
	@FXML
	public void add(){
		try {
			bl.addPasswordItem(new PasswordItem("a", "b", "c"));
			display();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void setting(){
	}
	
	@FXML
	public void close(){
		bl.setPasswordSafe(null);
		MainApplication.getMainApplication().showLastScene();
	}
	
	private void clean(){
		box.getChildren().clear();
		controllerMap.clear();
	}
	
	public void display(){
		clean();
		List<PasswordItem> items=bl.getPasswordItems();
		for(PasswordItem item:items){
			addPasswordItem(item);
		}
	}
	
	private void addPasswordItem(PasswordItem item){
		ObjectAndController<Pane, PasswordItemController> itemPane=new ObjectAndController<>(getClass().getResource("PasswordItem.fxml"));
		itemPane.getController().setPasswordItem(item);
		controllerMap.put(itemPane.getObejct(), itemPane.getController());
		box.getChildren().add(itemPane.getObejct());
	}
}
