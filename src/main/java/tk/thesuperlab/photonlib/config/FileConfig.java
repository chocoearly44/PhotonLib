package tk.thesuperlab.photonlib.config;

import java.io.*;

/**
 * Configuration based on object serialization
 *
 * @author chocoearly44
 * @since 1.0
 */
public class FileConfig {
	public FileConfig() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Creates and populates config file with initial values
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
		ObjectOutputStream oos = new ObjectOutputStream(fout);

		oos.writeObject(clazz.getDeclaredConstructor().newInstance());
	}

	/**
	 * Saves object to config file
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
		ObjectOutputStream oos = new ObjectOutputStream(fout);

		oos.writeObject(config);
	}

	/**
	 * Loads object from file
	 *
	 * @param configFile Config file
	 * @param clazz      Config class
	 * @param <T>        Config type
	 * @return Populated config object
	 * @throws IOException            {@code configFile} doesn't exist, filesystem error...
	 * @throws ClassNotFoundException {@code clazz} doesn't exist
	 */
	public static <T extends Serializable> T loadConfig(File configFile, Class<T> clazz) throws IOException, ClassNotFoundException {
		if(!configFile.exists()) {
			throw new IOException("Config file doesn't exist");
		}

		FileInputStream fis = new FileInputStream(configFile);
		ObjectInputStream ois = new ObjectInputStream(fis);

		return clazz.cast(ois.readObject());
	}
}