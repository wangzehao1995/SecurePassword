package wzhkun.securepw.ui.javafx;

import static wzhkun.securepw.ui.javafx.DPIFormatter.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application{

	
	public static void main(String[] args) {
		Application.launch(MainApplication.class);
	}

	private Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage=primaryStage;
		Scene scene = new JavaFXLogin();
		stage.setScene(scene);
		stage.show();
		
		stage.setWidth(cmToPx(16));
		stage.setHeight(cmToPx(12));
		
		
	}

	
}
