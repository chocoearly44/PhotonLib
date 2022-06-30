package tk.thesuperlab.photonlib.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/**
 * Configuration based on JSON serialization
 *
 * @author chocoearly44
 * @since 1.0
 */
public class JsonConfig {
	private static final ObjectMapper om;

	static {
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public JsonConfig() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Creates and populates JSON file with initial values
	 *
	 * @param configFile Config file
	 * @param clazz      Config class
	 * @param <T>        Config type
	 * @throws Exception class missing, filesystem error...
	 */
	public static <T extends Serializable> void initializeConfig(File configFile, Class<T> clazz) throws Exception {
		if(!configFile.exists()) {
			configFile.createNewFile();
		}

		FileOutputStream fout = new FileOutputStream(configFile);
		om.writeValue(fout, clazz.getDeclaredConstructor().newInstance());
	}

	/**
	 * Saves object to JSON file
	 *
	 * @param configFile Config file
	 * @param config     Config object
	 * @param <T>        Config type
	 * @throws IOException {@code configFile} doesn't exist, filesystem error...
	 */
	public static <T extends Serializable> void saveConfig(File configFile, T config) throws IOException {
		if(!configFile.exists()) {
			throw new IOException("Config file doesn't exist");
		}

		FileOutputStream fout = new FileOutputStream(configFile);
		om.writeValue(fout, config);
	}

	/**
	 * Loads object from JSON file
	 *
	 * @param configFile Config file
	 * @param clazz      Config class
	 * @param <T>        Config type
	 * @return Populated config object
	 * @throws IOException {@code configFile} doesn't exist, filesystem error...
	 */
	public static <T extends Serializable> T loadConfig(File configFile, Class<T> clazz) throws IOException {
		if(!configFile.exists()) {
			throw new IOException("Config file doesn't exist");
		}

		FileInputStream fis = new FileInputStream(configFile);
		return om.readValue(fis, clazz);
	}
}