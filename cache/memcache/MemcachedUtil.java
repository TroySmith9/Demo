package memcache;

import java.io.*;
import java.util.Properties;

/**
 * memecached客户端
 * @author Administrator
 */
public class MemcachedUtil {
//	private static SockIOPool pool = null;
//
//	private static MemCachedClient mcc = null;
//
//	public static void main(String[] args) {
//		getSockIOPool();
//		System.out.println("aaa");
//	}
//
//	public static MemCachedClient getInitMemCached() {
//		if (pool == null) {
//			pool = getSockIOPool();
//		}
//		if (mcc == null) {
//			// 给类加锁 防止线程并发
//			synchronized (MemcachedUtil.class) {
//				if (mcc == null) {
//					mcc = new MemCachedClient();
//				}
//			}
//		}
//		return mcc;
//	}
//
//	private MemcachedUtil() {
//	}
//
//	public static SockIOPool getSockIOPool() {
//		String url = "";
//		String path = new File(MemcachedUtil.class.getResource("/").getFile()).getAbsolutePath();
//		System.out.println("memcach-path:" + path);
//		url = path + "\\memcache\\memcach.properties";
//		Properties props = new Properties();
//		InputStream in = null;
//		try {
//			in = new BufferedInputStream(new FileInputStream(url));
//			props.load(in);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		String server = props.getProperty("memcached.server");
//		String initConn = props.getProperty("memcached.initConn");
//		String minConn = props.getProperty("memcached.minConn");
//		String maxConn = props.getProperty("memcached.maxConn");
//		String maintSleep = props.getProperty("memcached.maintSleep");
//		String nagle = props.getProperty("memcached.nagle");
//		String socketTO = props.getProperty("memcached.socketTO");
//		String failover = props.getProperty("memcached.failover");
//		String aliveCheck = props.getProperty("memcached.aliveCheck");
//
//		String[] servers = { server };
//
//		pool = SockIOPool.getInstance();
//
//		pool.setServers(servers);
//
//		pool.setFailover(Boolean.parseBoolean(failover));
//
//		pool.setInitConn(Integer.parseInt(initConn));
//
//		pool.setMinConn(Integer.parseInt(minConn));
//
//		pool.setMaxConn(Integer.parseInt(maxConn));
//
//		pool.setMaintSleep(Integer.parseInt(maintSleep));
//
//		pool.setNagle(Boolean.parseBoolean(nagle));
//
//		pool.setSocketTO(Integer.parseInt(socketTO));
//
//		pool.setAliveCheck(Boolean.parseBoolean(aliveCheck));
//
//		pool.initialize();
//
//		return pool;
//
//	}

}
