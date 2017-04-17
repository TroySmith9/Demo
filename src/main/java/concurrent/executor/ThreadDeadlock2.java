package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 调用者运行的饱和策略
 */
public class ThreadDeadlock2 {
    ExecutorService exec = new ThreadPoolExecutor(0, 2, 60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());

    private void putrunnable() {
        for (int i = 0; i < 4; i++) {
            exec.submit(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        System.out.println(Thread.currentThread().getName());
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new ThreadDeadlock2().putrunnable();
    }
}