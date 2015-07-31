package wzhkun.securepw.bl;

public class BLServiceManager {

	public static PasswordSafeBL safeBL = new PasswordSafeBL();

	public static PasswordSafeBL getPasswordSafeBL() {
		return safeBL;
	}

	public static SettingBL settingBL = new SettingBL();

	public static SettingBL getSettingBL() {
		return settingBL;
	}
}
