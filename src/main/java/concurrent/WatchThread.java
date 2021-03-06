package concurrent;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试监控类
 * @author
 *
 */
public class WatchThread {

	/**  
	 * 测试函数 
	 */
	public static void main(String[] args) throws InterruptedException {
		long time=System.currentTimeMillis();
		WatchThread test = new WatchThread();
		test.testThread();
		System.out.println( "所有结束.");
		System.out.println(" ******"+(System.currentTimeMillis()-time));
	}

	/**
	 * 测试函数
	 * @throws InterruptedException
	 */
	public void testThread() throws InterruptedException {
//		testNewThread();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		int threadNum = 10;
		//初始化countDown
		final CountDownLatch threadSignal = new CountDownLatch(threadNum);
		for (int i = 0; i < threadNum; i++) { //开threadNum个线程   
			executor.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + "结束. 还有"+ threadSignal.getCount() + " 个线程");
					threadSignal.countDown();
				}
			});
//			Thread.sleep(1);
		}
		System.out.println("threadSignal");
		threadSignal.await(); //等待所有子线程执行完   
		System.out.println(threadSignal.getCount());
		
		executor.shutdownNow();
		System.out.println("threadSignal2434");
	}

	public void testNewThread() throws InterruptedException {
		int threadNum = 10;
		//初始化countDown
		CountDownLatch threadSignal = new CountDownLatch(threadNum);
		//创建固定长度的线程池
		Executor executor = Executors.newFixedThreadPool(threadNum);
		for (int i = 0; i < threadNum; i++) { //开threadNum个线程   
			Runnable task = new TestThread(threadSignal);
                           //执行
			executor.execute(task);
		}
		threadSignal.await(); //等待所有子线程执行完   
		//do work
		System.out.println(Thread.currentThread().getName() + "+++++++结束.");
	}

	/**
	 * 
	 * @author jill
	 *
	 */
	private class TestThread implements Runnable {
		private CountDownLatch threadsSignal;

		public TestThread(CountDownLatch threadsSignal) {
			this.threadsSignal = threadsSignal;
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + "开始...");
			//do shomething--做你自己的业务逻辑
			System.out.println("开始了线程：：：：" + threadsSignal.getCount());
			//线程结束时计数器减1
			threadsSignal.countDown();  
			System.out.println(Thread.currentThread().getName() + "结束. 还有"+ threadsSignal.getCount() + " 个线程");
		}
	}

}
