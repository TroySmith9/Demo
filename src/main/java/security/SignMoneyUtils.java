package security;

import java.security.MessageDigest;

/**
 * Created by yiban on 2017/5/16.
 */
public class SignMoneyUtils {

    public static void main(String[] args) {
        System.out.println(sigMoney("79927398712",2));
    }

    protected static String sigMoney(String uid, long t) {
        return sha(uid + sha("di_3$1ml") + t).substring(8, 18);
    }

    public static String sha(String text) {
        return getHexMessageDigest(text, "sha");
    }
    

    private static String getHexMessageDigest(String text, String algorithm) {
        return SecurityUtils.toHex(getMessageDigest(text, algorithm));
    }

    public static byte[] getMessageDigest(String text, String algorithm) {
        try {
            MessageDigest e = MessageDigest.getInstance(algorithm);
            e.update(text.getBytes("UTF-8"));
            byte[] bytes = e.digest();
            return bytes;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

}
