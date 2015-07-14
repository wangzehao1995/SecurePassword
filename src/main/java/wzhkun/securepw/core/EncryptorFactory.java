package wzhkun.securepw.core;

class EncryptorFactory {
	public static Encryptor getEncryptor(String password){
		return new AESEncryptor(password);
	}
}
