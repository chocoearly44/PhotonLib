package tk.thesuperlab.photonlib.hashing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashingUtilsTest {
	@Test
	public void hashSha256() {
		assertEquals(
				"ea0d3f9089245b62382a5e34cfc1ef152ed852abc85b44d1c1f24da13986a9f4",
				HashingUtils.hashSha256("PhotonLib")
		);
	}

	@Test
	public void hashMd5() {
		assertEquals(
				"46182a04a72b5e1c38bf06b86c739031",
				HashingUtils.hashMd5("PhotonLib")
		);
	}

	@Test
	public void hashBase64() {
		assertEquals(
				"UGhvdG9uTGli",
				HashingUtils.hashBase64("PhotonLib")
		);
	}
}