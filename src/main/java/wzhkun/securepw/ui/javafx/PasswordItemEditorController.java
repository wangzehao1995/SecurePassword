package wzhkun.securepw.ui.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.core.PasswordGenerator;
import wzhkun.securepw.core.PasswordItem;

public class PasswordItemEditorController implements Initializable{
	@FXML
	TextField oldPassword;
	@FXML
	TextField newPassword;
	@FXML
	TextField account;
	@FXML
	TextField app;

	@FXML
	TextField length;
	@FXML
	CheckBox upperCase;
	@FXML
	CheckBox numbers;
	@FXML
	CheckBox symbols;
	@FXML
	CheckBox firstIsLetter;
	@FXML
	CheckBox atLeastOneNumber;
	@FXML
	CheckBox atLeastOneSymbol;

	private static final String goodStyle="-fx-base: limegreen;";
	private static final String badStype="-fx-base: #ff3333;";
	private PasswordGenerator pg;	

	@FXML
	public void generate() {
		try {
			exprotSettings(pg);
			length.setStyle(goodStyle);
			newPassword.setStyle(goodStyle);
			newPassword.setText(pg.generate());
		} catch (NumberFormatException e) {
			length.setStyle(badStype);
			newPassword.setStyle(badStype);
		}
	}

	@FXML
	public void confirm() {
		PasswordItem old=oldItem;
		PasswordItem newPI=new PasswordItem(app.getText(), account.getText(), newPassword.getText());
		try {
			BLServiceManager.getPasswordSafeBL().updatePasswordItem(old, newPI);
			MainApplication.getMainApplication().refreshPasswordBox();
			cancle();
		} catch (IOException e) {
			new UnableToAccessFileAlert().showAndWait();
			e.printStackTrace();
		}
	}

	@FXML
	public void cancle() {
		app.setText("");
		account.setText("");
		oldPassword.setText("");
		newPassword.setText("");
		MainApplication.getMainApplication().showLastScene();
	}

	private PasswordItem oldItem;
	public void setPasswordItem(PasswordItem item) {
		oldItem=item;
		this.oldPassword.setText(item.getPassword());
		this.newPassword.setText(item.getPassword());
		this.account.setText(item.getAccount());
		this.app.setText(item.getApp());
	}

	public void importSettings(PasswordGenerator pg) {
		this.length.setText("" + pg.get长度());
		this.upperCase.setSelected(pg.is大小写混合());
		this.numbers.setSelected(pg.is字母数字混合());
		this.symbols.setSelected(pg.is字母符号混合());
		this.firstIsLetter.setSelected(pg.is第一位为字母());
		this.atLeastOneNumber.setSelected(pg.is至少一位为数字());
		this.atLeastOneSymbol.setSelected(pg.is至少一位为特殊符号());
	}

	public void exprotSettings(PasswordGenerator pg) throws NumberFormatException {

		pg.set长度(Integer.parseInt(this.length.getText()));
		pg.set大小写混合(upperCase.isSelected());
		pg.set字母数字混合(numbers.isSelected());
		pg.set字母符号混合(symbols.isSelected());
		pg.set第一位为字母(firstIsLetter.isSelected());
		pg.set至少一位为数字(atLeastOneNumber.isSelected());
		pg.set至少一位为特殊符号(atLeastOneSymbol.isSelected());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pg = new PasswordGenerator();
		importSettings(pg);
	}
}
