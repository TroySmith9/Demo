package logquene;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 功能描述：日志（生产者-消费者模式）
 */
public final class SysLoginLogUtil {
	private static final Log logger = LogFactory.getLog(SysLoginLogUtil.class);

	/** 全局唯一实例 */
	private static volatile SysLoginLogUtil instance = null;

	/** 日志队列 */
	private ConcurrentLinkedQueue<Object> logQueue = null;

	/** 线程池 */
	private ThreadPoolExecutor pool = null;

	/** 延迟时间 */
	private Long delayTime = 60000L;

	/** 批量插入大小 */
	private Long batchsize = 1000L;

	private SysLoginLogUtil() {
		logQueue = new ConcurrentLinkedQueue<Object>();
		pool =new ThreadPoolExecutor(2, 8, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(512),
						new ThreadPoolExecutor.CallerRunsPolicy());

		// 添加消费者
		pool.execute(new LogConsumer());

	}

	public static SysLoginLogUtil getInstance() {
		if (null == instance) {
			synchronized (SysLoginLogUtil.class) {
				if (null == instance) {
					instance = new SysLoginLogUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * 添加登录日志
	 * @param log
	 */
	public void saveLog(Object log) {
		if (null != log) {
			logQueue.add(log);
		}
	}

	/**
	 * 功能描述：日志消费者任务
	 */
	class LogConsumer implements Runnable {
		private final Long WAITTIME = 1000L;

		@Override
		public void run() {
			// 初始化变量
			Object log = null;
			List<Object> logList = new ArrayList<Object>();
			Date writeTime = new Date();
			boolean flag = false;
			while (true) {
				try {
					log = logQueue.poll();
					if (null == log) {
						try {
							// 防止线程空跑
							Thread.sleep(WAITTIME);
						} catch (Exception e) {
						}

						flag = ((System.currentTimeMillis() - writeTime.getTime()) >= delayTime);

						if (flag && logList.size() > 0) {

							pool.execute(new LogHandleThread(logList));

							// 初始化变量
							logList = new ArrayList<Object>();
							writeTime = new Date();
							flag = false;
						}
						continue;
					}

					logList.add(log);
					if (logList.size() >= batchsize) {
						pool.execute(new LogHandleThread(logList));

						// 初始化变量
						logList = new ArrayList<Object>();
						writeTime = new Date();
						flag = false;
					}
				} catch (Throwable e) {
					// 防止异常导致消费者任务结束
					logger.error("LogConsumer error!", e);
				}
			}
		}
	}
}

class LogHandleThread implements Runnable {
	private static final Log logger = LogFactory.getLog(LogHandleThread.class);

	private List<Object> logList = null;

	// private SysLoginService sysLoginService = null;

	public LogHandleThread(List<Object> logList) {
		this.logList = logList;
		// sysLoginService = EnvContext.getBean("sysLoginService");
	}

	@Override
	public void run() {
		if (null == logList || 0 == logList.size()) {
			return;
		}
		try {
			logger.info("LogHandleThread excute logList size=" + logList.size());
			// sysLoginService.batchLoginLog(logList);
		} catch (Exception e) {
			logger.error("LogHandleThread batch insert error!", e);
		} finally {
			logList.clear();
			logList = null;
			// sysLoginService = null;
		}
	}

}