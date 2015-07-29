package wzhkun.securepw.ui.javafx;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class UIFactory {
	
	private static final String FOLDER="fxml/";
	
	private UIFactory() {

	}

	private static UIFactory uiFactory = new UIFactory();

	public static UIFactory getUIFactory() {
		return uiFactory;
	}

	public ObjectAndController<Parent, LoginController> getLogin() {
		return load("Login.fxml");
	}

	public ObjectAndController<Parent, ResetController> getReset() {
		return load("Reset.fxml");
	}

	public ObjectAndController<Parent, MainSceneController> getMainScene() {
		ObjectAndController<Parent, MainSceneController> result= load("MainScene.fxml");
		result.getController().setBar(getBar().getObejct());
		return result;
	}

	public ObjectAndController<Parent, PasswordItemEditorController> getPasswordItemEditor() {
		return load("PasswordItemEditor.fxml");
	}

	public ObjectAndController<Pane, PasswordItemController> getPasswordItemPane() {
		return load("PasswordItem.fxml");
	}
	
	public ObjectAndController<Pane, PasswordBoxController> getPasswordBox(){
		return load("PasswordBox.fxml");
	}
	
	private ObjectAndController<Pane, BarController> getBar(){
		return load("Bar.fxml");
	}
	
	public ObjectAndController<Pane, ChangePasswordController> getChangePassword(){
		return load("ChangePassword.fxml");
	}
	
	public ObjectAndController<Pane, ImportController> getImport(){
		return load("Import.fxml");
	}
	
	public ObjectAndController<Pane, ExportController> getExport(){
		return load("Export.fxml");
	}
	
	public ObjectAndController<Pane, SyncController> getSync(){
		return load("Sync.fxml");
	}
	
	private <A,B> ObjectAndController<A, B> load(String file){
		return new ObjectAndController<>(getClass().getResource(FOLDER+file));
	}
}
