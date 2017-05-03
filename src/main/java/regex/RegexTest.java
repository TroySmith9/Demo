package regex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yiban on 2017/4/27.
 */
public class RegexTest {

    private final static Pattern pattern = Pattern.compile("201\\d-([0][\\d]|1[0-2])");

    public static void main(String[] args) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
        System.out.println(format.format(new Date()));
        testMatch("2015-01");
        testMatch("2005-01");
        testMatch("2015-01-15");
        testMatch("2017-13");
        Pattern.matches(pattern.toString(),"'");

    }

    private static void testMatch(String str) {
        Matcher matcher = pattern.matcher(str);
        System.out.println(str + " " + matcher.matches());
    }
}               
