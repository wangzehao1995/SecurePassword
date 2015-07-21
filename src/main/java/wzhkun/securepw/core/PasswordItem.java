package wzhkun.securepw.core;

import java.io.Serializable;
import java.util.Date;

public class PasswordItem implements Serializable{
	private static final long serialVersionUID = 1L;

	private final String app;
	private final String account;
	private transient String lastPassword;
	private String password;
	private long updateTime;
	
	public void changePassword(String newPassword){
		this.lastPassword=this.password;
		this.password=newPassword;
		this.updateTime=System.currentTimeMillis();
	}
	
	public void delete(){
		this.password=null;
		updateTime=System.currentTimeMillis();
	}
	
	public PasswordItem(String app,String key,String value){
		this.app=app;
		this.account=key;
		this.password=value;
		this.updateTime=System.currentTimeMillis();
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getApp(){
		return app;
	}
	
	public String getLastPassword(){
		return lastPassword;
	}

	public String getPassword() {
		return password;
	}

	public Date getUpdateTime() {
		return new Date(updateTime);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
}
