package wzhkun.securepw.ui.javafx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class JavaFXPasswordItem {

	private Pane pane;
	
	public Pane getPane(){
		if (pane == null) {
			try {
				pane = new Pane((Parent) FXMLLoader.load(getClass().getResource("PasswordItem.fxml")));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return pane;
	}
}
