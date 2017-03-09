package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yiban on 2016/12/2.
 */
public class CounterTest {
    AtomicInteger counter = new AtomicInteger(0);

	public static void main(String[] args) {
		final CounterTest c = new CounterTest();
		new Thread() {
			@Override
			public void run() {
				c.count();
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				c.count();
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				c.count();
			}
		}.start();
	}

	public int count() {
		int result;
		boolean flag;
		do {
			result = counter.get();
			// 单线程下, compareAndSet返回永远为true,
			// 多线程下, 在与result进行compare时, counter可能被其他线程set了新值, 这时需要重新再取一遍再比较,
			// 如果还是没有拿到最新的值, 则一直循环下去, 直到拿到最新的那个值
			flag = counter.compareAndSet(result, result + 1);
			System.out.println(result + " " + flag);
        } while (!flag);

		return result;
	}
}
