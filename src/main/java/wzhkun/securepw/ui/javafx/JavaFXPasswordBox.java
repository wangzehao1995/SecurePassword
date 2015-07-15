package wzhkun.securepw.ui.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wzhkun.securepw.core.PasswordItem;

public class JavaFXPasswordBox {
	private static Scene scene;
	
	public Scene getScene(){
		if (scene == null) {
			try {
				scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("PasswordBox.fxml")));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return scene;
	}
	
	
	
	@FXML
	VBox box;
	
	@FXML
	public void add(){
		initBox();
	}
	
	@FXML
	public void setting(){
	}
	
	public void initBox(){
		addToBox(new JavaFXPasswordItem(new PasswordItem("fuck", "fuck", "fuck")).getPane());
	}
	
	private void addToBox(Pane pane){
		box.getChildren().add(pane);
	}
}
