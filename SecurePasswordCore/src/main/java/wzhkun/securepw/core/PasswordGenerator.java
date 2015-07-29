package wzhkun.securepw.core;

import java.io.Serializable;

public class PasswordGenerator implements Serializable {
	private static final long serialVersionUID = 1L;

	private int 长度 = 12;
	private boolean 大小写混合 = true;
	private boolean 字母数字混合 = true;
	private boolean 字母符号混合 = true;
	private boolean 第一位为字母 = true;
	private boolean 至少一位为数字 = true;
	private boolean 至少一位为特殊符号 = true;

	private static final String 小写字母 = "abcdefghijklmnopqrstuvwxyz";
	private static final String 大写字母 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String 数字 = "0123456789";
	private static final String 特殊符号 = "~!@#$%^&*()_+-={}|[]\\:;<>?,./";

	public PasswordGenerator() {

	}

	public String generate() {
		String 库 = 小写字母;
		if (大小写混合) {
			库 += 大写字母;
		}
		if (字母数字混合) {
			库 += 数字;
		}
		if (字母符号混合) {
			库 += 特殊符号;
		}

		String 密码 = "";
		for (int i = 0; i < 长度; i++) {
			密码 += random(库);
		}
		if (至少一位为数字 && 至少一位为特殊符号) {
			while (!(contains(密码, 数字) && contains(密码, 特殊符号))) {
				密码 = randomReplace(密码, 数字);
				密码 = randomReplace(密码, 特殊符号);
			}
		} else if (至少一位为数字) {
			while (!contains(密码, 数字)) {
				密码 = randomReplace(密码, 数字);
			}
		} else if (至少一位为特殊符号) {
			while (!contains(密码, 特殊符号)) {
				密码 = randomReplace(密码, 特殊符号);
			}
		}
		if (第一位为字母) {
			密码 = random(小写字母) + 密码.substring(1);
		}

		return 密码;
	}

	private String randomReplace(String object, String library) {
		char[] chars = object.toCharArray();
		chars[(int) (Math.random() * chars.length)] = random(library);
		return new String(chars);
	}

	private char random(String library) {
		return library.charAt((int) (Math.random() * library.length()));
	}

	private boolean contains(String source, String lib) {
		for (char c : lib.toCharArray()) {
			if (source.contains(Character.toString(c))) {
				return true;
			}
		}
		return false;
	}

	public int get长度() {
		return 长度;
	}

	public void set长度(int 长度) {
		this.长度 = 长度;
	}

	public boolean is大小写混合() {
		return 大小写混合;
	}

	public void set大小写混合(boolean 大小写混合) {
		this.大小写混合 = 大小写混合;
	}

	public boolean is字母数字混合() {
		return 字母数字混合;
	}

	public void set字母数字混合(boolean 字母数字混合) {
		this.字母数字混合 = 字母数字混合;
	}

	public boolean is字母符号混合() {
		return 字母符号混合;
	}

	public void set字母符号混合(boolean 字母符号混合) {
		this.字母符号混合 = 字母符号混合;
	}

	public boolean is第一位为字母() {
		return 第一位为字母;
	}

	public void set第一位为字母(boolean 第一位为字母) {
		this.第一位为字母 = 第一位为字母;
	}

	public boolean is至少一位为数字() {
		return 至少一位为数字;
	}

	public void set至少一位为数字(boolean 至少一位为数字) {
		this.至少一位为数字 = 至少一位为数字;
	}

	public boolean is至少一位为特殊符号() {
		return 至少一位为特殊符号;
	}

	public void set至少一位为特殊符号(boolean 至少一位为特殊符号) {
		this.至少一位为特殊符号 = 至少一位为特殊符号;
	}
}
