package wzhkun.securepw.ui.javafx;

import java.util.function.Consumer;

import javafx.application.Application;
import wzhkun.securepw.ui.DisplayController;
import wzhkun.securepw.ui.LoginScene;
import wzhkun.securepw.ui.ResetScene;
import wzhkun.securepw.ui.UIManager;

public class JavaFXUIManager extends UIManager{
	
	public static JavaFXUIManager getUIManager(){
		return (JavaFXUIManager) UIManager.getUIManager();
	}

	Consumer<Object> initCallback;
	@Override
	public void initApplication(Consumer<Object> callback) {
		this.initCallback=callback;
		Application.launch(JavaFXApplication.class);
	}
	
	public void initCompleted(){
		initCallback.accept(null);
	}

	@Override
	public DisplayController getDisplayController() {
		return JavaFXApplication.getMainApplication();
	}

	@Override
	public LoginScene getLoginScene() {
		return new JavaFXLogin();
	}

	@Override
	public ResetScene getResetScene() {
		// TODO Auto-generated method stub
		return new JavaFXReset();
	}

}
