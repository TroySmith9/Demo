package cn;

public class BreakTest {

    public static void main(String[] args) {

        //testFor();
        testWhile();
    }

    private static void testWhile() {
        int i = 0;
        while (i < 20) {
            int j = 0;
            while (j < 10) {
                if (j == 5) {
                    System.out.println("j=" + j);
                    break;
                }
                j++;
            }
            System.out.println("i=" + i);
            if (i == 6) {
                break;
            }
            i++;
        }
    }

    protected static void testFor() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5) {
                    System.out.println("j=" + j);
                    break;

                }
            }
            System.out.println("i=" + i);
            if (i == 6) {
                break;
            }
        }
    }
}


	
