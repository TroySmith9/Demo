package cn;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test {// extends Date

    private String code = null;

    private String name = null;

    protected static SimpleDateFormat labeldtf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static boolean out(String string) {
        System.out.println(string);
        return true;
    }

    public static void main(String[] args) {
//		testArrayIndexAddAdd();
//        byte b2 = 10;
//        for (int i = 0; i <1000 ; i++) {
//            byte b1 = (byte) i;
//            if (!(b1 == (b1 & 0xff))){
//                System.out.println(MessageFormat.format("b1:{0} ***  b1 & 0xf:{1} *** i:{2}", b1, (b1 & 0xf), i));
//            }
//        }
//        System.out.println(b2 == (b2 & 0xf));
//        System.out.println(b2 & 0xf);
//        System.out.println(b2 & 0xff);
        System.out.println("dafaeaf");
        

    }

    private static void testArrayIndexAddAdd() {
        String[] strs = {"aa", "bb", "cc", "dd"};
        int index = 0;
        if (out(strs[index++])) {

        }
//		System.out.println(strs[index++]);
//		System.out.println(strs[index++]);
    }

    static void a() throws HighLevelException {
        try {
            b();
        } catch (MidLevelException e) {
            throw new HighLevelException(e);
        }
    }

    static void b() throws MidLevelException {
        c();
    }

    static void c() throws MidLevelException {
        try {
            d();
        } catch (LowLevelException e) {
            throw new MidLevelException(e);
        }
    }

    static void d() throws LowLevelException {
        e();
    }

    static void e() throws LowLevelException {
        throw new LowLevelException();
    }

    protected static void testJsonParse() {
        String memo = "'~!@#$%^&*(()_+{}|:'\\',\\daflek,,a;fje'';;\"\"ijap'''',,\'";
        String update = "{$set:{memo:'" + escapseSpecil(memo) + "'}}";
        try {
            System.out.println(Document.parse(update));
            ;
        } catch (Exception e) {
            org.slf4j.Logger log = LoggerFactory.getLogger(Test.class);
            log.error(update);
            e.printStackTrace();

        }
    }

    /**
     * 处理转义符
     *
     * @param str
     * @return
     */
    protected static String escapseSpecil(String str) {
        return str.replace("\\", "\\\\").replace("'", "\\'");
    }

    protected static void changeTestObj(final TestObject obj) {
        obj.context = "aaaaaa";
        System.out.println(obj.context);

    }

    protected static String getErrorByException(Exception e) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(e.getMessage())) {
            sb.append(e.getMessage()).append("\r\n");
        }
        sb.append(e).append("\r\n");
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; i++) {
            sb.append("\tat " + trace[i]).append("\r\n");
        }

//		Throwable ourCause = e.getCause();
        return sb.toString();
    }

    static void callback() {
        try {
            URL url =
                    new URL(
                            "http://www.baidu.com");
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            StringBuffer result2 = new StringBuffer();
            while ((line = br.readLine()) != null) {
                result2.append(line);
            }
            System.out.println(result2);

            StringBuffer result = new StringBuffer();
            while (br.readLine() != null) {
                result.append(br.readLine());
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void testMapEntry() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("01", "ab");
        map.put("05", "b");
        map.put("02", "c");
        Set<Entry<String, String>> entryset = map.entrySet();
        for (Entry<String, String> entry : entryset) {
            System.out.println("Key:" + entry.getKey() + ",Value:" + entry.getValue());
        }
    }

    static void testHashcode() {
        Test t1 = new Test();
        Test t2 = new Test();
        Test t3 = t1;
        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());
        System.out.println(t3.hashCode());

        // System.out.println(t1.code);
        // System.out.println(t1==t2);
        // System.out.println(t1.equals(t2));
        // System.out.println(t1.equals(t3));

        // t3.setCode("11");
        // System.out.println(t1.equals(t3));
    }

    protected static void testStringHashcode() {

        String data = "Hello";
        String data2 = "Hello";
        System.out.println(data.hashCode());
        System.out.println(data2.hashCode());

        String a = new String("i love you");
        String b = new String("i love you");

        System.out.println(a == b);
        System.out.println(a.equals(b));

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }

    protected static void renameFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            for (File tempfile : file.listFiles()) {
                String name = tempfile.getName();
                String newname = name.replaceAll("尚学堂_高淇_java300集最全视频教程_", "");
                System.out.println(path + "/" + newname);
                tempfile.renameTo(new File(path + "/" + newname));
            }
        }

    }

    protected static void compareIntAndInteger() {
        int i = 0;
        Integer j = null;
        System.out.println(i);
        System.out.println(j);

    }

    public void test() {
        System.out.println(super.getClass().getName());
    }

    private static final char[] data = new char[]{'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};

    private static final char[] units = new char[]{'元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿'};

    public static String convert(int money) {
        StringBuffer sbf = new StringBuffer();
        int unit = 0;
        while (money != 0) {
            sbf.insert(0, units[unit++]);
            int number = money % 10;
            sbf.insert(0, data[number]);
            money /= 10;
        }

        return sbf.toString();
    }

    public enum WeekDay {
        Sun(1), Mon, Tue;

        private WeekDay() {
            System.out.println("!!!!");
        }

        private WeekDay(int i) {
            System.out.println("******");
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

class HighLevelException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 4092517673855300016L;

    HighLevelException(Throwable cause) {
        super(cause);
    }
}

class MidLevelException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 8789342203581617742L;

    MidLevelException(Throwable cause) {
        super(cause);
    }
}

class LowLevelException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3718606650959329222L;
}

class TestObject {
    String context;

    TestObject(String context) {
        this.context = context;
    }
}
