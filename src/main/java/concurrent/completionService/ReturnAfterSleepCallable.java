package concurrent.completionService;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ReturnAfterSleepCallable
 */
public class ReturnAfterSleepCallable implements Callable<Integer> {

    private int sleepSeconds;
    private int returnValue;

    public ReturnAfterSleepCallable(int sleepSeconds, int returnValue) {
        this.sleepSeconds = sleepSeconds;
        this.returnValue = returnValue;
    }


    @Override
    public Integer call() throws Exception {
        System.out.println("begin to execute ");

        TimeUnit.SECONDS.sleep(sleepSeconds);
        return returnValue;
    }

}