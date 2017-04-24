package concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用tryLock避免死锁，本例中休眠时间随机一点更好
 * @author pei.shi
 *
 */
public class TryLockTest {

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
		boolean print = false;
		while(!print){
			if (lock1.tryLock()) {
				if (lock2.tryLock()){
					try {
						// 
						TimeUnit.SECONDS.sleep(3);
						System.out.println("print1 ^^^^");
						print = true;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						lock2.unlock();
					}
				}
				lock1.unlock();
			}
		}
	}

	static void print2() {
		boolean print = false;
		while(!print){
			if (lock2.tryLock()) {
				if (lock1.tryLock()){
					try {
						TimeUnit.SECONDS.sleep(1);
						System.out.println("print2 ^^^^");
						print = true;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						lock1.unlock();
					}
				}
				lock2.unlock();
			}
		}
	}
}
