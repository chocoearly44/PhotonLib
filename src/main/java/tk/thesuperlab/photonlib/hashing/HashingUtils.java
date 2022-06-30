package tk.thesuperlab.photonlib.hashing;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;

public class HashingUtils {
	public HashingUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String hashSha256(String input) {
		return DigestUtils.sha256Hex(input);
	}

	public static String hashMd5(String input) {
		return DigestUtils.md5Hex(input);
	}

	public static String hashBase64(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes());
	}
}
