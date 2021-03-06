package cn.bytes.math;

/**
 * Created by yiban on 2017/4/7.
 */
public class BitOpetrationTest {

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.getInteger("555",5));;
//        String str="111011100110101100101000000000";
//        System.out.println("111111111111111111111111111110".length());
        /* 符号为:最高位同时表示图号，0为正数，1为负数 */
        testBitOperation();
//        System.out.println(Integer.parseInt("100",2));
//        rightMove(8, 4);
//        rightMove(9, 5);
//        System.out.println(System.nanoTime());
//        System.out.println(System.currentTimeMillis());
//        testBitOperation();
//        rightMove(127,2);
//        rightMove(128,2);
//        rightMove(1024,2);
//        rightMove(1025,2);
//        
//        leftMove(8,2);
//        leftMove(9,2);
//
//        leftMove(127,2);
//        leftMove(128,2);
//        
//        leftMove(1024,3);
//        leftMove(1025,2);
//        System.out.println(Integer.parseInt("0001111", 6) & 15);
//        System.out.println(Integer.parseInt("0011111", 6) & 15);
//        System.out.println(Integer.parseInt("0111111", 6) & 15);
//        System.out.println(Integer.parseInt("1111111", 1) & 15);
    }

    private static void testBitOperation() {
    /*  
        1、二进制转换为十进制 

        二进制转换为10进制的规律为： 每位的值 * 2的（当前位-1次方） 
        例如： 
            00000001 = 0 * 2^7 + 0 * 2^6 + 0 * 2^5 + 0 * 2^4 + 0 * 2^3 + 0 * 2^2 + 0 * 2^1 + 1 * 2^0  = 1 
            00000010 = 0 * 2^7 + 0 * 2^6 + 0 * 2^5 + 0 * 2^4 + 0 * 2^3 + 0 * 2^2 + 1 * 2^1 + 0 * 2^0  = 2 
             
        2、二进制的符号位： 
            最高位表示符号位，0表示正数  ， 1表示负数 
             
         
        3、将二进制负数转换为十进制：先对该二进制数取反，然后加1，再转换为十进制，然后在前面加上负号 
            例如： 10101011 最高位为1，所以为负数 
               第一步：取反： 01010100 
               第二步：加1 ： 01010101 
               第三步：转换为10进制：85 
               第四步：加上负号： -85 
               所以      10101011 转换为十进制为 -85 
                
        4、将十进制负数转换为二进制：先得到该十进制负数的绝对值，然后转换为二进制，然后将该二进制取反，然后加1 
            例如：-85 
            第一步：得到绝对值 85 
            第二步：转换为二进制：01010101 
            第二步：取反：       10101010 
            第三步：加1：        10101011 
            所以，-85转换为二进制为  10101011 
     */  
          
        /* 
        ~ ‘非’ 运算符是将目标数的进制去反，即0变成1 ，1变成0 
        2的二进制码为 00000010 ， 它取反为11111101 ，可见取反后结果为负数（二进制负数转换为十进制的步骤为：将二进制去反，然后+1） 
         将 1111 1101 转换为10进制 ，第一步去反 得到 0000 0010 然后 加1 得到 00000011 ，得到的结果为3 ，然后在前面加上负号就可以了 
         所以结果为-3 
         */
        System.out.println("~5:"+~5);  
          
        /* 
          ^ 异或 ，计算方式为：两个二进制数的位相同则为0 不同则为1 
          23转换为二进制为：00010111 
          12转换为二进制为：00001100 
                计算结果为：00011011  =  27 
         */
        System.out.println(23 ^ 12);   
          
        /* 
         & 按位与 ，计算方式为：两个二进制数的位都为1则为1 ，否则为0 
         1的二进制为 ：00000001 
         2的二进制为 ：00000010 
              结果为 :00000000 = 0  
         */
        System.out.println(1 & 2);  
          
        /* 
          | 按位或 ，计算方式为：两个二进制位有一个为1就为1，否者为0 
          5 的二进制为：00000101 
          6 的二进制为：00000110 
                  结果为：00000111 = 7 
         */
        System.out.println(5 | 6);  
          
          
        /* 
          >> 有符号右移位  ，符号左边表示要被移位的数，右边表示需要移的位数，结果为正数则在左边补0，否则补1 
          3 的二进制为：00000010 
                向右移动1位：00000001 = 1  
         */
        System.out.println(3 >> 1);
    }

    private static void rightMove(int value, int moveIndex) {
        int intV = value >> moveIndex;
        System.out.println(value + ": " + Integer.toBinaryString(value) + " 右移" + moveIndex + " 位:" + intV + " :" + Integer.toBinaryString(intV));
    }

    private static void leftMove(int value, int moveIndex) {
        int intV = value << moveIndex;
        System.out.println(value + ": " + Integer.toBinaryString(value) + " 左移" + moveIndex + " 位:" + intV + " :" + Integer.toBinaryString(intV));
    }
}
