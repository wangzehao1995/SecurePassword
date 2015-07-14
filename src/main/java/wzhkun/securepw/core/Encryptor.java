package wzhkun.securepw.core;

import javax.crypto.BadPaddingException;

interface Encryptor {
	byte[] encrypt(byte[] input);
	byte[] decrypt(byte[] input) throws BadPaddingException;
}
