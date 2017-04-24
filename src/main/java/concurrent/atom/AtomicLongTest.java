package concurrent.atom;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yiban on 2017/1/23.
 */
public class AtomicLongTest {
    private static final AtomicLong incrementId = new AtomicLong(-1);
    private static String seedPrex ;

    public static void main(String[] args) {
        //        testID();
        seedPrex = Long.toOctalString(1000);
        System.out.println(newUid());

    }

    /**
     * 新创建1个Id
     *
     * @return
     */
    public static long newUid() {
        long newId;

        while ((newId = incrementId.incrementAndGet()) < 10)
            ;
        System.out.println(newId);
        return Long.parseLong(seedPrex + newId);
    }


    private static void testID() {
        System.out.println(incrementId.getAndIncrement());
        long inc = incrementId.getAndIncrement();
        System.out.println(inc);
        for (; ; ) {

        }
    }
}
