import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {

    // md5 hashing algorithm
    private String md5Hashing(String value) throws Exception {
        MessageDigest algo = MessageDigest.getInstance("MD5");
        algo.update(value.getBytes(), 0, value.length());

        return new BigInteger(1, algo.digest()).toString(16);
    }

    // hash password using md5
    public String getMd5(String value) {
        String md5 = null;

        try {
            md5 = md5Hashing(value);
        } catch (Exception a) {
            a.printStackTrace();
        }

        return md5;
    }

}
