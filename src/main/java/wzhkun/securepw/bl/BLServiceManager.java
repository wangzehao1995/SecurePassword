package wzhkun.securepw.bl;

public class BLServiceManager {
	
	private static LoginBL loginBL=new LoginBL();
	public static LoginBL getLoginBL(){
		return loginBL;
	}
	
	public static ResetBL resetBL=new ResetBL();
	public static ResetBL getResetBL(){
		return resetBL;
	}
	
	public static PasswordSafeBL safeBL=new PasswordSafeBL();
	public static PasswordSafeBL getPasswordSafeBL(){
		return safeBL;
	}
}
