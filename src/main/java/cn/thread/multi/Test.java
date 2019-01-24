package cn.thread.multi;

/**
 * troy
 * 2018/6/30
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        for (int i = 0; i <= 10; i++) {
            if (i == 4) {
                Thread.sleep(10);
                test.stop();
            }
            test.run();
        }
        System.out.println("dasssssssss");
        Thread.sleep(100);
    }


    //    private static volatile boolean flag = true;
    private volatile boolean flag = true;

    private void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (flag) {
                    doSomeThing();
                } else {
                    System.out.println(" stoped:" + Thread.currentThread().getId());
                }
            }
        }).start();
    }

    private void doSomeThing() {
        System.out.println("doSomeThing:" + Thread.currentThread().getId());
    }

    private void stop() {
        flag = false;
    }
}
