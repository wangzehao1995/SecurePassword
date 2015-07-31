package wzhkun.securepw.bl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class SettingBL {
	public static class Entity implements Serializable {
		private static final long serialVersionUID = 1L;

		public Map<String, String> map = new TreeMap<>();

		public String get(String key) {
			if (key == null) {
				throw new IllegalArgumentException();
			}
			String result = map.get(key);
			if (result == null) {
				return "";
			}
			return result;
		}

		public void put(String key, String value) {
			if (key == null || value == null) {
				return;
			}
			map.put(key, value);
		}
	}

	private Entity entity ;

	private MyFile settingFile;

	private static final String SYNC_FILE = "SYNC_FILE";
	
	public void setSettingFile(MyFile file){
		settingFile=file;
	}

	public String getSyncFilePath() {
		refreshEntity();
		return entity.get(SYNC_FILE);
	}

	public void setSyncFilePath(String path) throws IOException {
		entity.put(SYNC_FILE, path);
		saveEntity();
	}

	private void refreshEntity() {
		entity = readEntity();
	}

	private Entity readEntity() {
		if(settingFile==null){
			throw new RuntimeException("Wrong Object State");
		}
		try (ObjectInputStream decoder = new ObjectInputStream(settingFile.getInputStream());) {
			return (Entity) decoder.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new Entity();
		}
	}

	private void saveEntity() throws IOException {
		ObjectOutputStream encoder = new ObjectOutputStream(settingFile.getOutputStream());
		encoder.writeObject(entity);
		encoder.close();
	}
}
