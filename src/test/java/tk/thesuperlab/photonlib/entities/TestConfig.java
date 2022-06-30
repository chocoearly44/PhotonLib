package tk.thesuperlab.photonlib.entities;

import java.io.Serializable;

public class TestConfig implements Serializable {
	private String testString;

	public TestConfig() {
	}

	public TestConfig(String testString) {
		this.testString = testString;
	}

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}
}
