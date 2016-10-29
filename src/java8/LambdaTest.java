package java8;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by yiban on 2016/7/12.
 */
public class LambdaTest {

    public static void main(String[] args) {
        testOldClass();
        String[] newWay = "Improving code with Lambda expressions in Java 8".split(" ");
        Arrays.sort(newWay, (s1, s2) -> {
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        });
        System.out.println(String.join(", ", newWay));
    }

    private static void testOldClass() {
        String[] oldWay = "Improving code with Lambda expressions in Java 8".split(" ");
        Arrays.sort(oldWay, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // 忽略大小写排序:
                return s1.toLowerCase().compareTo(s2.toLowerCase());
            }
        });
        System.out.println(String.join(", ", oldWay));
    }

}

