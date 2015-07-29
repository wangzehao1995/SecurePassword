package wzhkun.securepw.core;

import java.io.Serializable;
import java.util.Date;

public class PasswordItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String app;
	private final String account;
	private String password;
	private long updateTime;

	public PasswordItem(String app, String account, String password) {
		this.app = app;
		this.account = account;
		this.password = password;
		this.updateTime = System.currentTimeMillis();
	}

	public String getAccount() {
		return account;
	}

	public String getApp() {
		return app;
	}

	public String getPassword() {
		return password;
	}

	public Date getUpdateTime() {
		return new Date(updateTime);
	}

	public void setDeleted() {
		this.password = null;
	}

	public boolean isDeleted() {
		return password == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordItem other = (PasswordItem) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		return true;
	}

}
