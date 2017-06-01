package security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * Created by yiban on 2017/5/4.
 */
public class SecurityUtils {

    public static void main(String[] args) {
        String passowrd = "&*($HJDGH4867%&T345386754OHYOH*^(ughiuR5fu&f&$KHAOS$&^%";

        System.out.println(decrypt("UDID:868192022142851", passowrd));
//        Assert.assertEquals(encrypt("UDID:5A58EF1E-EEF2-478D-94EE-709B98407589",passowrd),
//                "bbf5e7f4cbd27afa301fe15f7931d83bb7b6097606c95f72ba8847f53aa7b7305c52ae3c9461beec64c1920eac18faa9");
    }
    
    public static String encrypt(String plainText, String password) {
        try {
            return toHex(encrypt(plainText.getBytes("UTF-8"), password));
        } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static byte[] encrypt(byte[] byteS, String pwd) {
        Object byteFina = null;

        Cipher cipher;
        byte[] byteFina1;
        try {
            cipher = Cipher.getInstance("AES");
            SecretKeySpec e = new SecretKeySpec(getKey(pwd), "AES");
            cipher.init(1, e);
            byteFina1 = cipher.doFinal(byteS);
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        } finally {
            cipher = null;
        }

        return byteFina1;
    }

    public static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 3);

        for (int i = 0; i < bytes.length; ++i) {
            int val = bytes[i] & 255;
            if (val < 16) {
                sb.append("0");
            }

            sb.append(Integer.toHexString(val));
        }

        return sb.toString();
    }

    private static byte[] getKey(String password) throws UnsupportedEncodingException {
        if (password.length() > 16) {
            password = password.substring(0, 16);
        } else if (password.length() < 16) {
            int count = 16 - password.length();

            for (int i = 0; i < count; ++i) {
                password = password + "0";
            }
        }

        return password.getBytes("UTF-8");
    }

    public static String decrypt(String cipherText, String password) {
        try {
            byte[] e = decrypt(hexTobytes(cipherText), password);
            return new String(e, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static byte[] hexTobytes(String str) {
        int l = str.length();
        if(l % 2 != 0) {
            throw new IllegalArgumentException("长度不是偶数!");
        } else {
            byte[] bytes = new byte[l / 2];

            for(int i = 0; i < l; i += 2) {
                String item = str.substring(i, i + 2);
                bytes[i / 2] = (byte)Integer.parseInt(item, 16);
            }

            return bytes;
        }
    }


    public static byte[] decrypt(byte[] byteD, String pwd) {
        Object byteFina = null;

        Cipher cipher;
        byte[] byteFina1;
        try {
            cipher = Cipher.getInstance("AES");
            SecretKeySpec e = new SecretKeySpec(getKey(pwd), "AES");
            cipher.init(2, e);
            byteFina1 = cipher.doFinal(byteD);
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        } finally {
            cipher = null;
        }

        return byteFina1;
    }
}
