package wzhkun.securepw.ui.javafx;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wzhkun.securepw.core.PasswordItem;

public class PasswordBoxController {
	private Map<Pane, PasswordItemController> controllerMap=new HashMap<>();
	
	@FXML
	VBox box;
	
	@FXML
	public void add(){
		initBox();
	}
	
	@FXML
	public void setting(){
	}
	
	@FXML
	public void close(){
		
	}
	
	public void initBox(){
		addPasswordItem(new PasswordItem("fuck", "fuck", "fuck"));
	}
	private void addPasswordItem(PasswordItem item){
		ObjectAndController<Pane, PasswordItemController> itemPane=new ObjectAndController<>(getClass().getResource("PasswordItem.fxml"));
		itemPane.getController().setPasswordItem(item);
		controllerMap.put(itemPane.getObejct(), itemPane.getController());
		box.getChildren().add(itemPane.getObejct());
	}
}
