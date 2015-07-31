package wzhkun.securepw.ui.javafx;

import java.io.File;
import java.io.IOException;

import javax.crypto.BadPaddingException;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.MyFile;

public class ImportController extends ImportOrExportController{

	@FXML
	public void import_(){
		doIt();
	}
	
	@Override
	protected void doItWithMyPassword(File file) throws BadPaddingException, ClassNotFoundException, IOException {
		BLServiceManager.getPasswordSafeBL().import_(MyFile.toMyFile(file));
	}

	@Override
	protected void doItWithNewPassword(File file, String password)
			throws BadPaddingException, ClassNotFoundException, IOException {
		BLServiceManager.getPasswordSafeBL().import_(MyFile.toMyFile(file), password);
	}

	@Override
	protected File getTargetFile() {
		return new FileChooser().showOpenDialog(MainApplication.getMainApplication().getStage());
	}

}
