package wzhkun.securepw.ui.javafx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import wzhkun.securepw.bl.BLServiceManager;

public class MainSceneController {

	@FXML
	public void add() {
		MainApplication.getMainApplication().showPasswordItemEditorScene();
	}

	@FXML
	public void close() {
		BLServiceManager.getPasswordSafeBL().setPasswordSafe(null);
		MainApplication.getMainApplication().showLastScene();
	}	

	@FXML
	private BorderPane borderPane;
	
	private Pane bar;

	@FXML
	public void bar() {
		if (isBarShowed()) {
			hideBar();
		} else {
			showBar();
		}
	}
	
	private boolean isBarShowed(){
		return borderPane.getLeft()!=null;
	}

	public void setBar(Pane bar) {
		this.bar = bar;
	}

	public void showBar() {
		borderPane.setLeft(bar);
	}

	public void hideBar() {
		borderPane.setLeft(null);
	}
	
	private ObjectAndController<? extends Node, ? extends Object> mainScene;
	
	public void setMainScene(ObjectAndController<? extends Node, ? extends Object> mainScene){
		this.mainScene=mainScene;
		borderPane.setCenter(mainScene.getObejct());
	}
	
	public ObjectAndController<? extends Node, ? extends Object> getMainScene(){
		return mainScene;
	}
}
