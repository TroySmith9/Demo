package cn.java.lang;

import java.math.BigDecimal;

/**
 * Created by yiban on 2016/12/21.
 */
public class Number {

	public static void main(String args[]) {
//		float f1 = 0.05F;
//		float f2 = 0.01F;
//		System.out.println(f1 + f2);
//        System.out.println(f1==0.05F);
//
//        System.out.println((f1 + f2) == (0.05F+0.1F));
//        BigDecimal bd1=BigDecimal.valueOf(f1);
//        BigDecimal bd2=BigDecimal.valueOf(f2);
//        System.out.println(bd1.add(bd2));
//        System.out.println(bd1.add(bd2) == BigDecimal.valueOf(0.06));
//
//        System.out.println(1.0 - 0.42);
//		System.out.println(4.015 * 100);
//		System.out.println(123.3 / 100);

        float myNumber = (float) 3.146;
        if ( myNumber == 3.146f ) { //Noncompliant. Because of floating point imprecision, this will be false
            System.out.println("myNumber == 3.146f");
        }
        if ( myNumber != 3.146f ) { //Noncompliant. Because of floating point imprecision, this will be true
            System.out.println("myNumber != 3.146f");
        }

        if (myNumber < 4 || myNumber > 4) { // Noncompliant; indirect inequality test
            System.out.println("myNumber < 4 || myNumber > 4");
        }

        float zeroFloat = 0.0f;
        if (zeroFloat == 0) {  // Noncompliant. Computations may end up with a value close but not equal to zero.
            System.out.println("zeroFloat == 0");
        }
	}
}
