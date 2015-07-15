package wzhkun.securepw.bl;

public class BLServiceManager {
	
	public static LoginBL getLoginBL(){
		return new LoginBL();
	}
	
	public static ResetBL getResetBL(){
		return new ResetBL();
	}
	
	public static PasswordSafeBL getPasswordSafeBL(){
		return new PasswordSafeBL();
	}
}
