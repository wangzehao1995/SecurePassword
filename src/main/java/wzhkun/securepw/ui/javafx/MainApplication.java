package wzhkun.securepw.ui.javafx;

import static wzhkun.securepw.ui.javafx.DPIFormatter.*;

import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wzhkun.securepw.core.PasswordItem;

public class MainApplication extends Application {
	private static MainApplication app;

	public static MainApplication getMainApplication() {
		return app;
	}

	public static void main(String[] args) {
		Application.launch(MainApplication.class);
	}

	private Stage stage;
	private Scene loginScene;
	private Scene resetScene;
	private Scene boxScene;
	private Scene editorScene;
	private ObjectAndController<Parent, LoginController> login;
	private ObjectAndController<Parent, ResetController> reset;
	private ObjectAndController<Parent, PasswordBoxController> box;
	private ObjectAndController<Parent, PasswordItemEditorController> editor;
	private Stack<Scene> sceneStack = new Stack<>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		app = this;
		this.stage = primaryStage;
		
		showLoginScene();
		showStage();

	}

	private void showStage() {
		stage.setWidth(cmToPx(9));
		stage.setHeight(cmToPx(16));
		stage.show();
	}

	public void showResetScene() {
		if (reset == null) {
			reset = new ObjectAndController<>(getClass().getResource("Reset.fxml"));
			resetScene = new Scene(reset.getObejct());
		}
		showScene(resetScene);
	}

	public void showLoginScene() {
		if (login == null) {
			login = new ObjectAndController<>(getClass().getResource("Login.fxml"));
			loginScene = new Scene(login.getObejct());
		}
		showScene(loginScene);
	}

	public void showPasswordBoxScene() {
		if (box == null) {
			box = new ObjectAndController<>(getClass().getResource("PasswordBox.fxml"));
			boxScene = new Scene(box.getObejct());
		}

		refreshPasswordBox();

		showScene(boxScene);
	}
	
	public void showPasswordItemEditorScene() {
		if (editor == null) {
			editor = new ObjectAndController<>(getClass().getResource("PasswordItemEditor.fxml"));
			editorScene = new Scene(editor.getObejct());
		}
		showScene(editorScene);
	}

	public void showLastScene() {
		if(sceneStack.isEmpty()){
			showLoginScene();
		}
		Scene current=sceneStack.pop();
		if(current==sceneStack.peek()){
			showSceneOnPeek();
		}
		showSceneOnPeek();
	}
	
	public void refreshPasswordBox(){
		box.getController().display();
	}

	public void showPasswordItemEditorScene(PasswordItem item) {
		showPasswordItemEditorScene();
		editor.getController().setPasswordItem(item);
	}

	private void showScene(Scene scene) {
		sceneStack.push(scene);
		showSceneOnPeek();
	}

	private void showSceneOnPeek() {
		stage.setScene(sceneStack.peek());
	}

}
