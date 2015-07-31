package wzhkun.securepw.ui.javafx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.SettingBL;
import wzhkun.securepw.ui.javafx.alert.UnableToAccessFileAlert;

public class SyncController implements Initializable{
	
	private SettingBL bl=BLServiceManager.getSettingBL();
	
	@FXML
	private TextField filePath;
	
	@FXML
	public void choose(){
		File file=new FileChooser().showOpenDialog(MainApplication.getMainApplication().getStage());
		if(file==null){
			return;
		}
		filePath.setText(file.getAbsolutePath());
		setPath();
	}
	
	@FXML
	public void setPath(){
		try {
			bl.setSyncFilePath(filePath.getText());
		} catch (IOException e) {
			new UnableToAccessFileAlert().showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTextFieldListener();
		filePath.setText(bl.getSyncFilePath());
	}

	public void setTextFieldListener() {
		filePath.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(oldValue==true&&newValue==false){
					setPath();
				}
			}
		});
	}
}
