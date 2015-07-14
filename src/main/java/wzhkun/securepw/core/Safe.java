package wzhkun.securepw.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.crypto.BadPaddingException;

public class Safe <T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	protected final List<T> entities = new ArrayList<>();

	private transient Encryptor encryptor;

	public Safe(String password) {
		this.encryptor  = EncryptorFactory.getEncryptor(password);
	}
	
	public Safe(byte[] encrypted,Safe<?> useSamePassword) throws BadPaddingException, ClassNotFoundException, IOException {
		this.encryptor=useSamePassword.encryptor;
		initFromBytes(encrypted);
	}
	
	public Safe(byte[] encrypted, String password) throws BadPaddingException, ClassNotFoundException, IOException {
		this(password);
		initFromBytes(encrypted);
	}

	@SuppressWarnings("unchecked")
	private void initFromBytes(byte[] encrypted)
			throws BadPaddingException, IOException, ClassNotFoundException {
		byte[] decrypted = encryptor.decrypt(encrypted);
		ObjectInputStream ois;
		ois = new ObjectInputStream(new ByteArrayInputStream(decrypted));
		Safe<?> safe = (Safe<?>) ois.readObject();
		ois.close();
		this.entities.addAll((Collection<? extends T>) safe.allItems());
	}

	public void changePassword(String newPassword) throws BadPaddingException {
		encryptor=EncryptorFactory.getEncryptor(newPassword);
	}

	public void add(T item) {
		entities.add(item);
	}

	public void remove(PasswordItem item) {
		entities.remove(item);
	}

	public List<T> allItems() {
		return Collections.unmodifiableList(entities);
	}

	byte[] encrypt() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			oos.close();
		} catch (Exception wontHappen) {
			throw new RuntimeException(wontHappen);
		}
		return encryptor.encrypt(bos.toByteArray());
	}

}