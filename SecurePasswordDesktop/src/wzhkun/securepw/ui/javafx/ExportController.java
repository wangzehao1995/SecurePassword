package wzhkun.securepw.ui.javafx;

import java.io.File;
import java.io.IOException;

import javax.crypto.BadPaddingException;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.MyFile;

public class ExportController extends ImportOrExportController{

	@FXML
	public void export(){
		doIt();
	}
	
	@Override
	protected void doItWithMyPassword(File file) throws BadPaddingException, ClassNotFoundException, IOException {
		BLServiceManager.getPasswordSafeBL().export(MyFile.toMyFile(file));
	}

	@Override
	protected void doItWithNewPassword(File file, String password)
			throws BadPaddingException, ClassNotFoundException, IOException {
		BLServiceManager.getPasswordSafeBL().export(MyFile.toMyFile(file), password);
		
	}
	
	@Override
	protected File getTargetFile() {
		return new FileChooser().showSaveDialog(MainApplication.getMainApplication().getStage());
	}

}
