package wzhkun.securepw.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.BadPaddingException;

public class Safe<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	protected final Set<T> entities = new HashSet<>();

	private transient Encryptor encryptor;

	public Safe(String password) {
		this.encryptor = EncryptorFactory.getEncryptor(password);
	}

	public Safe(byte[] encrypted, Safe<?> useSamePassword)
			throws BadPaddingException, ClassNotFoundException, IOException {
		this.encryptor = useSamePassword.encryptor;
		initFromBytes(encrypted);
	}

	public Safe(byte[] encrypted, String password) throws BadPaddingException, ClassNotFoundException, IOException {
		this(password);
		initFromBytes(encrypted);
	}

	@SuppressWarnings("unchecked")
	private void initFromBytes(byte[] encrypted) throws BadPaddingException, IOException, ClassNotFoundException {
		byte[] decrypted = encryptor.decrypt(encrypted);
		ObjectInputStream ois;
		ois = new ObjectInputStream(new ByteArrayInputStream(decrypted));
		Safe<?> safe = (Safe<?>) ois.readObject();
		ois.close();
		this.entities.addAll((Collection<? extends T>) safe.allItems());
	}

	public void changePassword(String newPassword) {
		encryptor = EncryptorFactory.getEncryptor(newPassword);
	}

	public void add(T item) {
		entities.add(item);
	}

	public void remove(PasswordItem item) {
		entities.remove(item);
	}

	public Set<T> allItems() {
		return Collections.unmodifiableSet(entities);
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
