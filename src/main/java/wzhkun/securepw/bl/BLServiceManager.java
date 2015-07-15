package wzhkun.securepw.bl;

public class BLServiceManager {
	private static LoginBL loginBL=new LoginBL();
	public static LoginBL getLoginBL(){
		return loginBL;
	}
	
	private static ResetBL resetBL=new ResetBL();
	public static ResetBL getResetBL(){
		return resetBL;
	}
	
	private static PasswordSafeBL passwordSafeBL=new PasswordSafeBL();
	public static PasswordSafeBL getPasswordSafeBL(){
		return passwordSafeBL;
	}
}
