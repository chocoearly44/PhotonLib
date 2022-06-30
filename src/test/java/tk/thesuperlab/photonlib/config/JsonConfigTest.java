package tk.thesuperlab.photonlib.config;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.thesuperlab.photonlib.entities.TestConfig;

import java.io.File;
import java.io.IOException;

public class JsonConfigTest {
	private static File configFile;

	@BeforeClass
	public static void init() {
		configFile = new File("config.jsonConfig");
	}

	@AfterClass
	public static void cleanup() {
		configFile.delete();
	}

	@Before
	public void beforeTest() {
		configFile.delete();
	}

	@Test
	public void initializeConfig() throws Exception {
		JsonConfig.initializeConfig(configFile, TestConfig.class);
	}

	@Test(expected = IOException.class)
	public void saveConfigNoFile() throws IOException {
		JsonConfig.saveConfig(configFile, TestConfig.class);
	}

	@Test
	public void saveConfig() throws Exception {
		JsonConfig.initializeConfig(configFile, TestConfig.class);
		JsonConfig.saveConfig(configFile, TestConfig.class);
	}

	@Test(expected = IOException.class)
	public void loadConfigNoFile() throws IOException {
		TestConfig config = JsonConfig.loadConfig(configFile, TestConfig.class);
	}
}