package wzhkun.securepw.bl;

import java.io.IOException;
import java.util.function.Consumer;

import javax.crypto.BadPaddingException;

import wzhkun.securepw.ui.*;

public class MainBL {

	public static void main(String[] args) {
		UIManager uiManager=UIManager.getUIManager();
		uiManager.initApplication(new Consumer<Object>() {
			
			@Override
			public void accept(Object t) {
				afterInited();
			}
		});
		
	}
	
	private static void afterInited(){
		UIManager uiManager=UIManager.getUIManager();
		DisplayController dc=uiManager.getDisplayController();
		final LoginScene loginScene=uiManager.getLoginScene();
		loginScene.setLoginHandler(new Consumer<String>() {
			
			@Override
			public void accept(String password) {
				try {
					BLServiceManager.getLoginBL().login(password);
				} catch (BadPaddingException e) {
					loginScene.showError("密码错误");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					loginScene.showError("密码错误");
					e.printStackTrace();
				} catch (IOException e) {
					loginScene.showError("密码错误");
					e.printStackTrace();
				}
			}
		});
		dc.showScene(loginScene);
		dc.showApplication();
	}

}
