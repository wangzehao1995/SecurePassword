package wzhkun.securepw.ui.javafx;

import static wzhkun.securepw.ui.javafx.DPIFormatter.*;

import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
	private Scene mainScene;
	private Scene editorScene;
	private ObjectAndController<Parent, LoginController> login;
	private ObjectAndController<Parent, ResetController> reset;
	private ObjectAndController<Parent, MainSceneController> main;
	private ObjectAndController<Parent, PasswordItemEditorController> editor;
	private ObjectAndController<Pane, PasswordBoxController> passwordBox;
	private ObjectAndController<Pane, ChangePasswordController> changePassword;
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

	public void showChangePassword() {

	}

	public void showCloudSync() {

	}

	public void showImport() {

	}

	public void showExport() {

	}

	public void showResetScene() {
		if (reset == null) {
			reset = UIFactory.getUIFactory().getReset();
			resetScene = new Scene(reset.getObejct());
		}
		showScene(resetScene);
	}

	public void showLoginScene() {
		if (login == null) {
			login = UIFactory.getUIFactory().getLogin();
			loginScene = new Scene(login.getObejct());
		}
		showScene(loginScene);
	}

	public void showPasswordBoxScene() {
		showMainScene();
		if(passwordBox==null){
			passwordBox=UIFactory.getUIFactory().getPasswordBox();
		}
		main.getController().setMainScene(passwordBox);
		refreshPasswordBox();
	}
	
	public void showChangePasswordScene(){
		showMainScene();
		if(changePassword==null){
			changePassword=UIFactory.getUIFactory().getChangePassword();
		}
		main.getController().setMainScene(changePassword);
	}

	public void showMainScene() {
		if (main == null) {
			main = UIFactory.getUIFactory().getMainScene();
			mainScene = new Scene(main.getObejct());
		}
		showScene(mainScene);
	}
	
	private void hideBar(){
		if(!sceneStack.isEmpty()&&sceneStack.peek()==mainScene){
			main.getController().hideBar();
		}
	}

	public void showPasswordItemEditorScene() {
		if (editor == null) {
			editor = UIFactory.getUIFactory().getPasswordItemEditor();
			editorScene = new Scene(editor.getObejct());
		}
		showScene(editorScene);
	}

	public void showLastScene() {
		if (sceneStack.isEmpty()) {
			showLoginScene();
		}
		Scene current = sceneStack.pop();
		if (current == sceneStack.peek()) {
			showSceneOnPeek();
		}
		showSceneOnPeek();
	}

	public void refreshPasswordBox() {
		if (main.getController().getMainScene().getController() instanceof PasswordBoxController) {
			((PasswordBoxController) main.getController().getMainScene().getController()).display();
		}
	}

	public void showPasswordItemEditorScene(PasswordItem item) {
		showPasswordItemEditorScene();
		editor.getController().setPasswordItem(item);
	}

	private void showScene(Scene scene) {
		if (sceneStack.isEmpty() || sceneStack.peek() != scene) {
			sceneStack.push(scene);
		}
		hideBar();
		showSceneOnPeek();
	}

	private void showSceneOnPeek() {
		stage.setScene(sceneStack.peek());
	}

}
