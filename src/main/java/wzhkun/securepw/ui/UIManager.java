package wzhkun.securepw.ui;

import java.util.function.Consumer;

import wzhkun.securepw.ui.javafx.JavaFXUIManager;

public abstract class UIManager {
	private static UIManager singleton;
	
	public static UIManager getUIManager() {
		if(singleton==null){
			singleton = new JavaFXUIManager();
		}
		return singleton;
	}


	public abstract DisplayController getDisplayController();

	public abstract LoginScene getLoginScene();

	public abstract ResetScene getResetScene();

	public abstract void initApplication(Consumer<Object> afterinitCallback);
}
