package wzhkun.securepw.bl;

import java.io.IOException;

import javax.crypto.BadPaddingException;

import wzhkun.securepw.core.PasswordSafe;

public class LoginBL {

	public void login(String password) throws BadPaddingException, ClassNotFoundException, IOException {
		PasswordSafe safe = PasswordSafe.getLocalSafe(password);
		safe.save();
		BLServiceManager.getPasswordSafeBL().setPasswordSafe(safe);
	}

}
