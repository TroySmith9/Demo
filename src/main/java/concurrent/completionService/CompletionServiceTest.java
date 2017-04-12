package concurrent.completionService;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用CompletionService
 *
 * @author Xingle
 * @ClassName: CompletionServiceTest
 * TODO
 * @date 2014-9-16 上午11:32:45
 */
public class CompletionServiceTest {

    public static void main(String[] args) {
        
        int taskSize = 5;
        ExecutorService executor = Executors.newFixedThreadPool(taskSize);
        // 构建完成服务 
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);

        for (int i = 1; i <= taskSize; i++) {
            // 睡眠时间 
            int sleep = taskSize - i;
            // 返回结果  
            int value = i;
            //向线程池提交任务
            completionService.submit(new ReturnAfterSleepCallable(sleep, value));
            
            try {
                System.out.println("result:" + completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("all over. ");
        executor.shutdown();

    }

}