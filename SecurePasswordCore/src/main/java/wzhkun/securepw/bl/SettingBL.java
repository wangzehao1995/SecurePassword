package wzhkun.securepw.bl;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

	private Entity entity = readEntity();

	private static final File SETTING_FILE = new File("securepw.setting");

	private static final String SYNC_FILE = "SYNC_FILE";

	public String getSyncFilePath() {
		refreshEntity();
		return entity.get(SYNC_FILE);
	}

	public void setSyncFilePath(String path) throws FileNotFoundException {
		entity.put(SYNC_FILE, path);
		saveEntity();
	}

	private void refreshEntity() {
		entity = readEntity();
	}

	private Entity readEntity() {
		try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(SETTING_FILE));) {
			return (Entity) decoder.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new Entity();
		}
	}

	private void saveEntity() throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new FileOutputStream(SETTING_FILE));
		encoder.writeObject(entity);
		encoder.close();
	}
}
