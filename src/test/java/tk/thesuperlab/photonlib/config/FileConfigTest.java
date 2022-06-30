package tk.thesuperlab.photonlib.config;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.thesuperlab.photonlib.entities.TestConfig;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileConfigTest {
	private static File configFile;

	@BeforeClass
	public static void init() {
		configFile = new File("config.fileConfig");
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
		FileConfig.initializeConfig(configFile, TestConfig.class);
	}

	@Test(expected = IOException.class)
	public void saveConfigNoFile() throws IOException {
		FileConfig.saveConfig(configFile, TestConfig.class);
	}

	@Test
	public void saveConfig() throws Exception {
		FileConfig.initializeConfig(configFile, TestConfig.class);
		FileConfig.saveConfig(configFile, TestConfig.class);
	}

	@Test(expected = IOException.class)
	public void loadConfigNoFile() throws IOException, ClassNotFoundException {
		TestConfig config = FileConfig.loadConfig(configFile, TestConfig.class);
	}

	@Test
	public void loadConfig() throws Exception {
		FileConfig.initializeConfig(configFile, TestConfig.class);
		FileConfig.saveConfig(configFile, new TestConfig("PhotonLib"));

		TestConfig config = FileConfig.loadConfig(configFile, TestConfig.class);
		assertEquals("PhotonLib", config.getTestString());
	}
}