package wzhkun.securepw.ui.javafx;

import static wzhkun.securepw.ui.javafx.DPIFormatter.*;

import java.util.Stack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wzhkun.securepw.ui.DisplayController;
import wzhkun.securepw.ui.SceneContiner;

public class JavaFXApplication extends Application implements DisplayController{
	private static DisplayController app;
	
	public static DisplayController getMainApplication(){
		return app;
	}

	private Stage stage;
	private Stack<Scene> sceneStack=new Stack<>();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		app=this;
		this.stage=primaryStage;
		JavaFXUIManager.getUIManager().initCompleted();
	}
	
	public void showApplication(){
		stage.setWidth(cmToPx(9));
		stage.setHeight(cmToPx(16));
		stage.show();
	}
	
	@Override
	public void showLastScene(){
		sceneStack.pop();
		showSceneOnPeek();
	}

	private void showSceneOnPeek() {
		stage.setScene(sceneStack.peek());
	}

	@Override
	public void showScene(SceneContiner scene) {
		sceneStack.push((Scene) scene.getScene());
		showSceneOnPeek();
	}
}
