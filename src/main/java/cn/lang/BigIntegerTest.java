package cn.lang;

import java.math.BigInteger;

/**
 * Created by yiban on 2017/4/22.
 */
public class BigIntegerTest {

    public static void main(String[] args) {
        BigInteger p = BigInteger.ONE;
        int i = 0;
        do {
            System.out.println(p = p.nextProbablePrime());
            System.out.println(p.bitCount());
            System.out.println();
            i++;
        } while (i < 10);
    }
}
