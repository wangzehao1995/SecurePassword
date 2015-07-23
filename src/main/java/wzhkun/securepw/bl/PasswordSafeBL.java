package wzhkun.securepw.bl;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.crypto.BadPaddingException;

import wzhkun.securepw.core.PasswordItem;
import wzhkun.securepw.core.PasswordSafe;

public class PasswordSafeBL {
	private PasswordSafe safe;

	public void setPasswordSafe(PasswordSafe safe) {
		this.safe = safe;
	}

	public PasswordSafe getPasswordSafe() {
		return safe;
	}

	public void addPasswordItem(PasswordItem item) throws IOException {
		safe.add(item);
		safe.save();
	}

	public void removePasswordItem(PasswordItem item) throws IOException {
		safe.remove(item);
		safe.save();
	}

	public void updatePasswordItem(PasswordItem old, PasswordItem newPI) throws IOException {
		safe.remove(old);
		safe.add(newPI);
		safe.save();
	}

	public Set<PasswordItem> getPasswordItems() {
		return safe.allItems();
	}

	public void changePassword(String newPassword) throws IOException {
		safe.changePassword(newPassword);
		safe.save();
	}
	
	/**
	 * use my password
	 */
	public void import_(File from) throws BadPaddingException, ClassNotFoundException, IOException {
		safe.importFromFile(from);
		safe.save();
	}

	public void import_(File from, String password) throws BadPaddingException, ClassNotFoundException, IOException {
		safe.importFromFile(from, password);
		safe.save();
	}

	/**
	 * use my password
	 */
	public void export(File to) throws IOException {
		safe.export(to);
	}
	
	public void export(File to,String password) throws IOException{
		safe.export(to, password);
	}
}
