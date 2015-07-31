package wzhkun.securepw.bl;

import java.io.IOException;
import java.util.Set;

import javax.crypto.BadPaddingException;

import wzhkun.securepw.core.PasswordItem;
import wzhkun.securepw.core.PasswordSafe;

public class PasswordSafeBL {
	private PasswordSafe safe;
	private MyFile syncSafeFile;
	private MyFile localSafeFile;

	public void setPasswordSafe(PasswordSafe safe) {
		this.safe = safe;
	}

	public PasswordSafe getPasswordSafe() {
		return safe;
	}
	
	public void login(String password) throws BadPaddingException, ClassNotFoundException, IOException {
		PasswordSafe safe = PasswordSafe.getSafe(localSafeFile.getInputStream(),password);
		BLServiceManager.getPasswordSafeBL().setPasswordSafe(safe);
	}
	
	public void reset(String password) throws IOException {
		PasswordSafe safe = PasswordSafe.newSafe(password);
		BLServiceManager.getPasswordSafeBL().setPasswordSafe(safe);
		safe.save(localSafeFile.getOutputStream());
	}

	public void addPasswordItem(PasswordItem item) throws IOException {
		safe.add(item);
		save();
	}

	public void removePasswordItem(PasswordItem item) throws IOException {
		safe.remove(item);
		save();
	}

	public void updatePasswordItem(PasswordItem old, PasswordItem newPI) throws IOException {
		safe.remove(old);
		safe.add(newPI);
		save();
	}

	public Set<PasswordItem> getPasswordItems() {
		return safe.allItems();
	}

	public void changePassword(String newPassword) throws IOException {
		safe.changePassword(newPassword);
		save();
	}

	/**
	 * use my password
	 */
	public void import_(MyFile from) throws BadPaddingException, ClassNotFoundException, IOException {
		safe.import_(from.getInputStream());
		save();
	}

	public void import_(MyFile from, String password) throws BadPaddingException, ClassNotFoundException, IOException {
		safe.import_(from.getInputStream(), password);
		save();
	}

	/**
	 * use my password
	 */
	public void export(MyFile to) throws IOException {
		safe.export(to.getOutputStream());
	}

	public void export(MyFile to, String password) throws IOException {
		safe.export(to.getOutputStream(), password);
	}

	

	public void setSyncFile(MyFile file) {
		syncSafeFile = file;
	}
	
	public void setLocalFile(MyFile file){
		localSafeFile=file;
	}

	public void sync() throws BadPaddingException, ClassNotFoundException, IOException {
		if (syncSafeFile != null) {
			PasswordSafe target=PasswordSafe.getSafe(syncSafeFile.getInputStream(), safe);
			safe.synchronize(target);
			safe.save(localSafeFile.getOutputStream());
			target.save(syncSafeFile.getOutputStream());
		}
	}

	private void save() throws IOException {
		safe.save(localSafeFile.getOutputStream());
		try {
			sync();
		} catch (BadPaddingException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
