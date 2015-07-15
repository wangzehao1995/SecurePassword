package wzhkun.securepw.ui.javafx;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import wzhkun.securepw.ui.ResetScene;

public class JavaFXReset implements ResetScene{
	private Scene scene;

	public Scene getScene() {
		if (scene == null) {
			try {
				scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("Reset.fxml")));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return scene;
	}

	@FXML
	PasswordField password;

	@FXML
	public void reset() {
		resetHandler.accept(password.getText());
	}

	@FXML
	public void cancel() {
		cancelHandler.accept(null);
	}

	private Consumer<String> resetHandler;
	private Consumer<Object> cancelHandler;
	
	@Override
	public void setResetHandler(Consumer<String> passwordConsumer) {
		this.resetHandler=passwordConsumer;
	}

	@Override
	public void setCancelHandler(Consumer<Object> nullConsumer) {
		this.cancelHandler=nullConsumer;
	}

}
