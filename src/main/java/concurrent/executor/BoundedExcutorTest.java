package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yiban on 2017/4/14.
 */
public class BoundedExcutorTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        BoundedExecutor e = new BoundedExecutor(exec, 3);

        for (int i = 1; i < 10; i++) {
            final int c = i;
            e.submitTask(new Runnable() {

                @Override
                public void run() {
                    System.out.println("执行任务:" + c);
                }
            });
        }
    }
}
