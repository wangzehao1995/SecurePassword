package wzhkun.securepw.ui.javafx;

import static wzhkun.securepw.ui.javafx.DPIFormatter.*;

import java.util.Stack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application{
	private static MainApplication app;
	
	public static MainApplication getMainApplication(){
		return app;
	}
	
	public static void main(String[] args) {
		Application.launch(MainApplication.class);
	}

	private Stage stage;
	private JavaFXLogin login=new JavaFXLogin();
	private JavaFXReset reset=new JavaFXReset();
	private Stack<Scene> sceneStack=new Stack<>();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		app=this;
		this.stage=primaryStage;
		stage.setWidth(cmToPx(16));
		stage.setHeight(cmToPx(12));
		stage.show();		
		showLoginScene();
		
	}

	public void showResetScene(){
		sceneStack.push(reset.getScene());
		showSceneOnPeek();
	}
	
	public void showLoginScene(){
		sceneStack.push(login.getScene());
		showSceneOnPeek();
	}
	
	public void showLastScene(){
		sceneStack.pop();
		showSceneOnPeek();
	}

	private void showSceneOnPeek() {
		stage.setScene(sceneStack.peek());
	}
}
