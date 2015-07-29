package wzhkun.securepw.core;

import static org.junit.Assert.*;

import javax.crypto.BadPaddingException;

import org.junit.Test;

public class EncryptorTest {
	String[] texts = new String[] { "asdfsadflasdf", "lasd", "" };
	String[] passwords = texts;

	@Test
	public void test() {
		try {
			for (String password : passwords) {
				Encryptor encryptor = EncryptorFactory.getEncryptor(password);
				for (String text : texts) {
					byte[] encrypted = encryptor.encrypt(text.getBytes());
					byte[] decrypted = encryptor.decrypt(encrypted);
					String decryptedString = new String(decrypted);
					assertTrue("password:" + password + "\ntext:" + text + "\n", text.equals(decryptedString));
				}
			}
		} catch (BadPaddingException e) {
			fail(e.toString());
		}
	}

	@Test
	public void wrongTest() {
		Encryptor encryptor1 = EncryptorFactory.getEncryptor("password1");
		Encryptor encryptor2 = EncryptorFactory.getEncryptor("password2");
		for (String text : texts) {
			byte[] encrypted = encryptor1.encrypt(text.getBytes());
			try {
				encryptor2.decrypt(encrypted);
				fail();
			} catch (BadPaddingException e) {
			}
		}
	}

}
