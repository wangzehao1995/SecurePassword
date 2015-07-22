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

	public ObjectAndController<Parent, MainSceneController> getPasswordBox() {
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
	
	public ObjectAndController<Pane, BarController> getBar(){
		return new ObjectAndController<>(getClass().getResource("Bar.fxml"));
	}
}
