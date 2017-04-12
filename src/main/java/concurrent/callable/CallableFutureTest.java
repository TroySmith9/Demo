package concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable 和 Future实现线程等待
 */
public class CallableFutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("start main thread ");
        ExecutorService exec = Executors.newFixedThreadPool(5);

        //新建一个Callable 任务，并将其提交到一个ExecutorService. 将返回一个描述任务情况的Future.
        Callable<String> call = new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("start new thread ");
                Thread.sleep(5000);
                System.out.println("end new thread ");
                return "返回内容";
            }
        };

        Future<String> task = exec.submit(call);
        Thread.sleep(1000);
        System.out.println("task.isDone():"+task.isDone());
        String retn = task.get();
        System.out.println("task.isDone():"+task.isDone());
        //关闭线程池
        exec.shutdown();
        System.out.println(retn + "--end main thread");
    }

}