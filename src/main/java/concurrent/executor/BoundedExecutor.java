package concurrent.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore来控制任务的提交速率
 *
 * @ClassName: BoundedExecutor
 */
public class BoundedExecutor {
    private final Executor exec;
    private final Semaphore semaphore;
    int bound;

    public BoundedExecutor(Executor exec, int bound) {
        this.exec = exec;
        this.semaphore = new Semaphore(bound);
        this.bound = bound;
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        //通过 acquire() 获取一个许可
        semaphore.acquire();
        System.out.println("----------当前可用的信号量个数:" + semaphore.availablePermits());
        try {
            exec.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有" + (bound - semaphore.availablePermits()) + "个并发");
                        command.run();
                    } finally {
                        //release() 释放一个许可
                        semaphore.release();
                        System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有" + (bound - semaphore.availablePermits()) + "个并发");
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}