package wzhkun.securepw.bl;

import wzhkun.securepw.core.PasswordSafe;

public class ResetBL {
	public void reset(String password){
		PasswordSafe safe=PasswordSafe.newLocalSafe(password);
		BLServiceManager.getPasswordSafeBL().setPasswordSafe(safe);
	}
}
