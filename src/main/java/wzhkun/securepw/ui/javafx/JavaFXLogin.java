package wzhkun.securepw.ui.javafx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class JavaFXLogin extends Scene{
	
	public JavaFXLogin() throws IOException {
		super((Parent) FXMLLoader.load(JavaFXLogin.class.getResource("Login.fxml")));
	}

}
