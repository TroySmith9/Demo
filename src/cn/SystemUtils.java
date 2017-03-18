package cn;

/**
 * Created by yiban on 2017/3/18.
 */
public class SystemUtils {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(SystemUtils.class.getResourceAsStream("log4j.properties"));
        System.out.println(SystemUtils.class.getClassLoader().getResource("log4j.properties"));
    }
}
