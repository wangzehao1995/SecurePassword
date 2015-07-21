package wzhkun.securepw.bl;

import java.io.IOException;
import java.util.List;

import wzhkun.securepw.core.PasswordItem;
import wzhkun.securepw.core.PasswordSafe;

public class PasswordSafeBL {
	private PasswordSafe safe;
	
	public void setPasswordSafe(PasswordSafe safe){
		this.safe=safe;
	}
	
	public PasswordSafe getPasswordSafe(){
		return safe;
	}
	
	
	public void addPasswordItem(PasswordItem item) throws IOException{
		safe.add(item);
		safe.save();
	}
	
	public List<PasswordItem> getPasswordItems(){
		return safe.allItems();
	}
}
