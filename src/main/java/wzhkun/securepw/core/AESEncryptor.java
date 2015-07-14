package wzhkun.securepw.core;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

class AESEncryptor implements Encryptor{
	private final Cipher encryptor;
	private final Cipher decryptor;
	AESEncryptor(String password) {
		try {
			Key key=buildKey(password);
			this.encryptor=Cipher.getInstance("AES");
			this.decryptor=Cipher.getInstance("AES");
			encryptor.init(Cipher.ENCRYPT_MODE, key);
			decryptor.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException|NoSuchPaddingException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] encrypt(byte[] input) {
		try {			
			byte[] result= encryptor.doFinal(input);
			return result;
		} catch (IllegalBlockSizeException|BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] decrypt(byte[] input) throws BadPaddingException {
		try {
			return decryptor.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		}
	}

	private Key buildKey(String password){
		byte[] hashCode256bits=sha256(password);
		SecretKeySpec key=new SecretKeySpec(hashCode256bits, "AES");
		return key;
	}
	
	private byte[] sha256(String password){
		final String algorithm="SHA-256";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] hashCode=md.digest(password.getBytes());
		byte[] fuck=new byte[16];
		System.arraycopy(hashCode, 0, fuck, 0, 16);
		return fuck;
	}
}
