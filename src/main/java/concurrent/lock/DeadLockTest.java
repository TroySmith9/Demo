package concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTest {

    static Lock lock1 = new ReentrantLock();

    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                print1();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                print2();
            }
        }.start();
    }


    static void print1() {
        lock1.lock();
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("print1 in ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("print1 ^^^^");
    }

    static void print2() {
        lock2.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("print2 in ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock1.lock();
        System.out.println("print2 ^^^^");
    }
}
