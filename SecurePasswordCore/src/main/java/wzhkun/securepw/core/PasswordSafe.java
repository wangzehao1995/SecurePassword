package wzhkun.securepw.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.BadPaddingException;

public class PasswordSafe extends Safe<PasswordItem>implements Cloneable {

	private PasswordSafe(String password) {
		super(password);
	}

	private PasswordSafe(byte[] encrypted, String password)
			throws BadPaddingException, ClassNotFoundException, IOException {
		super(encrypted, password);
	}

	private PasswordSafe(byte[] encrypted, PasswordSafe useSamePassword)
			throws BadPaddingException, ClassNotFoundException, IOException {
		super(encrypted, useSamePassword);
	}

	private static final long serialVersionUID = 1L;

	public static PasswordSafe newSafe(String password) throws IOException {
		PasswordSafe newSafe= new PasswordSafe(password);
		return newSafe;
	}

	public static PasswordSafe getSafe(InputStream from, String password) throws BadPaddingException, ClassNotFoundException, IOException  {
		PasswordSafe safe = readStream(from, password);
		return safe;
	}
	
	public static PasswordSafe getSafe(InputStream from, PasswordSafe password) throws BadPaddingException, ClassNotFoundException, IOException  {
		PasswordSafe safe = readStream(from, password);
		return safe;
	}

	/**
	 * 
	 * @param from
	 * @param password
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws BadPaddingException
	 * @throws Exception
	 *             Can't read file
	 */
	public void import_(InputStream from, String password)
			throws BadPaddingException, ClassNotFoundException, IOException {
		PasswordSafe safe = readStream(from, password);
		importFromSafe(safe);
	}

	/**
	 * Use My Password
	 * 
	 * @param from
	 * @throws BadPaddingException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void import_(InputStream from) throws BadPaddingException, ClassNotFoundException, IOException {
		PasswordSafe safe = readStream(from, this);
		importFromSafe(safe);
	}

	public void export(OutputStream to) throws IOException {
		save(to);
	}

	public void export(OutputStream to, String newPassword) throws IOException {
		PasswordSafe clone;
		try {
			clone = (PasswordSafe) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		clone.changePassword(newPassword);
		clone.save(to);
	}

	@Override
	public void add(PasswordItem item) {
		entities.remove(item);
		super.add(item);
	}

	@Override
	public void remove(PasswordItem item) {
		if (item != null) {
			entities.remove(item);
			item.setDeleted();
			entities.add(item);
		}
	}

	@Override
	public Set<PasswordItem> allItems() {
		HashSet<PasswordItem> newSet = new HashSet<>();
		for (PasswordItem item : entities) {
			if (!item.isDeleted()) {
				newSet.add(item);
			}
		}
		return newSet;
	}

	private static byte[] readEncryptedBytes(InputStream from)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(from);
		byte[] result = (byte[]) ois.readObject();
		ois.close();
		return result;
	}

	private static PasswordSafe readStream(InputStream from, String password)
			throws BadPaddingException, ClassNotFoundException, IOException {
		return new PasswordSafe(readEncryptedBytes(from), password);
	}

	private static PasswordSafe readStream(InputStream from, PasswordSafe password)
			throws BadPaddingException, ClassNotFoundException, FileNotFoundException, IOException {
		return new PasswordSafe(readEncryptedBytes(from), password);
	}

	public void synchronize(PasswordSafe safe) {
		this.importFromSafe(safe);
		safe.importFromSafe(this);
	}

	private void importFromSafe(PasswordSafe safe) {
		NextItem: for (PasswordItem item : safe.allItems()) {
			if(entities.contains(item)){
				for (PasswordItem myItem : entities) {
					if (myItem.equals(item)) {
						if (myItem.getUpdateTime().before(item.getUpdateTime())) {
							entities.remove(myItem);
							entities.add(item);
							continue NextItem;
						}
					}
				}
			}else{
				entities.add(item);
			}
			
		}
	}

	public void save(OutputStream target) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(target);
		oos.writeObject(this.encrypt());
		oos.close();
	}
}
