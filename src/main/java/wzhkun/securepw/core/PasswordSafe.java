package wzhkun.securepw.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.BadPaddingException;

public class PasswordSafe extends Safe<PasswordItem> implements Cloneable{

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

	private static final File localFile = new File("password.safe");

	private File repertory = localFile;

	public static PasswordSafe newLocalSafe(String password) {
		if (localFile.exists()) {
			localFile.delete();
		}
		return new PasswordSafe(password);
	}

	/**
	 * if local safe doesn't exist,a new safe will be created.
	 * 
	 * @param password
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws BadPaddingException
	 * @throws Exception
	 *             Can't create new local file
	 */
	public static PasswordSafe getLocalSafe(String password)
			throws BadPaddingException, IOException, ClassNotFoundException {
		if (!localFile.exists()) {
			return new PasswordSafe(password);
		}
		return readFromFile(localFile, password);
	}

	public static PasswordSafe getRemoteSafe(File file, String password) throws Exception {
		PasswordSafe safe = readFromFile(file, password);
		safe.repertory = file;
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
	public void importFromFile(File from, String password) throws BadPaddingException, ClassNotFoundException, IOException {
		PasswordSafe safe = readFromFile(from, password);
		importFromSafe(safe);
	}
	
	/**
	 * Use My Password
	 * @param from
	 * @throws BadPaddingException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void importFromFile(File from) throws BadPaddingException, ClassNotFoundException, IOException {
		PasswordSafe safe = readFromFile(from, this);
		importFromSafe(safe);
	}
	
	public void export(File to) throws IOException{
		saveToFile(to);
	}
	
	public void export(File to,String newPassword) throws IOException{
		PasswordSafe clone;
		try {
			clone = (PasswordSafe) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		clone.changePassword(newPassword);
		clone.saveToFile(to);
	}
	
	/**
	 * 
	 * @param file
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws BadPaddingException
	 * @throws Exception
	 *             Can't read or write file
	 */
	public void synchronizeWithFile(File file)
			throws BadPaddingException, ClassNotFoundException, FileNotFoundException, IOException {
		PasswordSafe safe = readFromFile(file, this);
		synchronize(safe);
		safe.saveToFile(file);
		this.save();
	}

	/**
	 * 
	 * @throws IOException
	 *             Can't write localFile
	 */
	public void save() throws IOException {
		saveToFile(repertory);
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
		HashSet<PasswordItem> newSet=new HashSet<>();
		for(PasswordItem item:entities){
			if(!item.isDeleted()){
				newSet.add(item);
			}
		}
		return newSet;
	}

	private static byte[] readEncryptedBytes(File from)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(from));
		byte[] result = (byte[]) ois.readObject();
		ois.close();
		return result;
	}

	private static PasswordSafe readFromFile(File from, String password)
			throws BadPaddingException, ClassNotFoundException, IOException {
		return new PasswordSafe(readEncryptedBytes(from), password);
	}

	private static PasswordSafe readFromFile(File from, PasswordSafe password)
			throws BadPaddingException, ClassNotFoundException, FileNotFoundException, IOException {
		return new PasswordSafe(readEncryptedBytes(from), password);
	}

	private void synchronize(PasswordSafe safe) {
		this.importFromSafe(safe);
		safe.importFromSafe(this);
	}

	private void importFromSafe(PasswordSafe safe) {
		NextItem:
		for (PasswordItem item : safe.allItems()) {
			for (PasswordItem myItem : entities) {
				if (myItem.equals(item)) {
					if (myItem.getUpdateTime().before(item.getUpdateTime())) {
						entities.remove(myItem);
						entities.add(item);
						continue NextItem;
					}
				}
			}
			
			add(item);
		}
	}

	private void saveToFile(File target) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(target));
		oos.writeObject(this.encrypt());
		oos.close();
	}
}
