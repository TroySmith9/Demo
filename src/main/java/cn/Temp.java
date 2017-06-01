package cn;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by yiban on 2017/1/17.
 */
public class Temp {

    private static Random random = new SecureRandom();

    public static String nextLong(){
//		long a = random.nextLong();
        return String.valueOf(Math.abs(random.nextLong()));
    }
    
    private Temp(){
        
    }
    

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        String[] imeis={"868291028595748","861335031335670","860954025329209","867798020384690","861836037296790","866624028787695","866724022014714","865029038939669","99000661839442","865085036591142","867993028954194","867756020395744","A00000694474EA","99000833163448","861626030529440","865266020693967","868806021716114"};
//        for (String imei:imeis){
//            System.out.println(parseStrToMd5L32(imei));
//        }

//        System.out.println(generateMuid("79927398712"));
//        System.out.println(parseStrToMd5L32("865168029272605"));
        
        Short sh=1;
        Integer integer = Integer.valueOf(sh);
        System.out.println(integer);
        
    }
    

    /**
     * md5加密
     * @param imei
     * @return
     */
    private static String parseStrToMd5L32(String imei){
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(imei.getBytes());
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : bytes){
                int bt = b&0xff;
                if (bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
//            Log.error();
        }
        return reStr;
    }

    /**
     * 根据手机唯一标识符和终端类型得到muid加密字符串
     * @param uniqueId   手机唯一标识符
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateMuid(String uniqueId) throws NoSuchAlgorithmException{
        String lowerCaseUniqueId = uniqueId.toLowerCase();
        return getSignatureSign(lowerCaseUniqueId);
    }

    /**
     * MD5加密
     *
     * @param url
     *           加密URL
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getSignatureSign(String url) throws NoSuchAlgorithmException {
        char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        byte[] strTemp = url.getBytes();
        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
        mdTemp.update(strTemp);
        byte[] md = mdTemp.digest();
        int j = md.length;
        char[] str = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[(k++)] = HEX_DIGITS[(byte0 >>> 4 & 0xF)];
            str[(k++)] = HEX_DIGITS[(byte0 & 0xF)];
        }
        return new String(str);
    }
    
//
//    /**
//     * 根据服务端图片url获取客户端名称
//     *
//     * @param serverImgUrl
//     * @return
//     */
//    public static String getClientImgName(String serverImgUrl) {
//        String clientImgName = serverImgUrl;
//        if (StringUtils.isNotBlank(serverImgUrl)) {
//            if (serverImgUrl.startsWith("http")) {
//                clientImgName = serverImgUrl.substring(serverImgUrl.indexOf("/group") + 1).replaceAll("/", "_");
//            } else {
//                clientImgName = serverImgUrl.replaceAll("/", "_");
//            }
//        }
//        return clientImgName;
//    }

    
    
    
}
