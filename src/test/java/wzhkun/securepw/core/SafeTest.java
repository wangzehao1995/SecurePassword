package wzhkun.securepw.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class SafeTest {
	final String password="baslkdjf";
	final String newPassword="asdfasdf";
	final String key="asgdsda";
	final String value="sdalkf";
	@Test
	public void test() throws Exception {
		Safe<PasswordItem> safe=new Safe<>(password);
		safe.add(new PasswordItem(key, value));
		assertTrue(safe.allItems().contains(new PasswordItem(key, value)));
		byte[] encrypted=safe.encrypt();
		Safe<PasswordItem> decrypted=new Safe<>(encrypted, password);
		assertTrue(decrypted.allItems().contains(new PasswordItem(key, value)));
		safe.changePassword(newPassword);
		try{
			decrypted=new Safe<>(encrypted, newPassword);
			fail();
		}catch(Exception mustHappen){
		}
		encrypted=safe.encrypt();
		decrypted=new Safe<>(encrypted, newPassword);
		assertTrue(safe.allItems().contains(new PasswordItem(key, value)));
		safe.remove(new PasswordItem(key, value));
		assertFalse(safe.allItems().contains(new PasswordItem(key, value)));
	}

}
