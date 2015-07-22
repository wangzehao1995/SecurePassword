package wzhkun.securepw.ui.javafx;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class UIFactory {
	private UIFactory() {

	}

	private static UIFactory uiFactory = new UIFactory();

	public static UIFactory getUIFactory() {
		return uiFactory;
	}

	public ObjectAndController<Parent, LoginController> getLogin() {
		return new ObjectAndController<>(getClass().getResource("Login.fxml"));
	}

	public ObjectAndController<Parent, ResetController> getReset() {
		return new ObjectAndController<>(getClass().getResource("Reset.fxml"));
	}

	public ObjectAndController<Parent, MainSceneController> getMainScene() {
		ObjectAndController<Parent, MainSceneController> result= new ObjectAndController<>(getClass().getResource("MainScene.fxml"));
		result.getController().setBar(getBar().getObejct());
		return result;
	}

	public ObjectAndController<Parent, PasswordItemEditorController> getPasswordItemEditor() {
		return new ObjectAndController<>(getClass().getResource("PasswordItemEditor.fxml"));
	}

	public ObjectAndController<Pane, PasswordItemController> getPasswordItemPane() {
		return new ObjectAndController<>(getClass().getResource("PasswordItem.fxml"));
	}
	
	public ObjectAndController<Pane, PasswordBoxController> getPasswordBox(){
		return new ObjectAndController<>(getClass().getResource("PasswordBox.fxml"));
	}
	
	private ObjectAndController<Pane, BarController> getBar(){
		return new ObjectAndController<>(getClass().getResource("Bar.fxml"));
	}
	
	public ObjectAndController<Pane, ChangePasswordController> getChangePassword(){
		return new ObjectAndController<>(getClass().getResource("ChangePassword.fxml"));
	}
	
	public ObjectAndController<Pane, ImportController> getImport(){
		return new ObjectAndController<>(getClass().getResource("Import.fxml"));
	}
	
	public ObjectAndController<Pane, ExportController> getExport(){
		return new ObjectAndController<>(getClass().getResource("Export.fxml"));
	}
}
