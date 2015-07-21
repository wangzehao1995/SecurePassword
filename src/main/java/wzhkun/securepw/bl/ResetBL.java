package wzhkun.securepw.bl;

import java.io.IOException;

import wzhkun.securepw.core.PasswordSafe;

public class ResetBL {
	public void reset(String password) throws IOException{
		PasswordSafe safe=PasswordSafe.newLocalSafe(password);
		BLServiceManager.getPasswordSafeBL().setPasswordSafe(safe);
		safe.save();
	}
}
