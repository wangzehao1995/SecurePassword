package wzhkun.securepw.ui.javafx;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import wzhkun.securepw.core.PasswordItem;

public class JavaFXPasswordItem {
	public JavaFXPasswordItem(PasswordItem item) {
		site=new TextField(item.getSite());
		site.setStyle("-fx-base:yellow");
		site.setEditable(false);
		account=new TextField(item.getAccount());
		account.setEditable(false);
		lastPassword=new TextField(item.getLastPassword());
		if(item.getLastPassword()==null)
			lastPassword.setVisible(false);
		password=new TextField(item.getPassword());
		password.setEditable(false);
	}

	private GridPane pane;
	private TextField site;
	private TextField account;
	private TextField lastPassword;
	private TextField password;
	
	public Pane getPane(){
		if (pane == null) {
			pane=new GridPane();
			
			pane.add(site, 0,0);
			pane.add(lastPassword, 1, 0);
			pane.add(account, 0, 1);
			pane.add(password, 1, 1);
		}
		return pane;
	}
}
