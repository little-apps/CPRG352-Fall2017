package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Generates hashes and converts hashes from bytes to hex string
 * @author 704199
 */
public class HashUtil {
    public static final String HASH_ALGO = "SHA-256";
    
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hashHex = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if(hex.length() == 1) hashHex.append('0');
            hashHex.append(hex);
        }
        
        return hashHex.toString();
    }
    
    public static byte[] hash(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGO);
        return messageDigest.digest(str.getBytes());
    }
}
