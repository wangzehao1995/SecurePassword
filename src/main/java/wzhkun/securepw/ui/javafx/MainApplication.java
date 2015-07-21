package wzhkun.securepw.ui.javafx;

import static wzhkun.securepw.ui.javafx.DPIFormatter.*;

import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Parent;
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
	private Scene loginScene;
	private Scene resetScene;
	private Scene boxScene;
	private ObjectAndController<Parent, LoginController> login;
	private ObjectAndController<Parent, ResetController> reset;
	private ObjectAndController<Parent, PasswordBoxController> box;
	private Stack<Scene> sceneStack=new Stack<>();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		app=this;
		this.stage=primaryStage;
		showStage();		
		showLoginScene();
		
	}

	private void showStage() {
		stage.setWidth(cmToPx(9));
		stage.setHeight(cmToPx(16));
		stage.show();
	}

	public void showResetScene(){
		if(reset==null){
			reset=new ObjectAndController<>(getClass().getResource("Reset.fxml"));
			resetScene=new Scene(reset.getObejct());
		}
		sceneStack.push(resetScene);
		showSceneOnPeek();
	}
	
	public void showLoginScene(){
		if(login==null){
			login=new ObjectAndController<>(getClass().getResource("Login.fxml"));
			loginScene=new Scene(login.getObejct());
		}
		sceneStack.push(loginScene);
		showSceneOnPeek();
	}
	
	public void showPasswordBoxScene(){
		if(box==null){
			box=new ObjectAndController<>(getClass().getResource("PasswordBox.fxml"));
			boxScene=new Scene(box.getObejct());
		}
		sceneStack.push(boxScene);
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
