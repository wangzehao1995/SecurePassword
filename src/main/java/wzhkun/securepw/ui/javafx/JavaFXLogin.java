package wzhkun.securepw.ui.javafx;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import wzhkun.securepw.ui.LoginScene;

public class JavaFXLogin implements LoginScene{
	
	private Scene scene;
	
	@Override
	public Scene getScene(){
		if (scene == null) {
			try {
				scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("Login.fxml")));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return scene;
	}
	
	@FXML
	PasswordField password;
	
	@FXML
	public void login(){
		loginHandler.accept(password.getText());
	}
	@FXML
	public void reset(){
		toResetSceneHandler.accept(null);
	}
	
	private Consumer<String> loginHandler;
	private Consumer<Object> toResetSceneHandler;
	@Override
	public void setLoginHandler(Consumer<String> passwordConsumer) {
		this.loginHandler=passwordConsumer;
	}
	@Override
	public void toResetSceneHandler(Consumer<Object> nullConsumer) {
		this.toResetSceneHandler=nullConsumer;
	}
	@Override
	public void showError(String message) {
		new Alert(AlertType.ERROR,message);
	}

}
