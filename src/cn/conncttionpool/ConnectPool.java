package cn.conncttionpool;

/** 
 * Title: ConnectPool.java 
 * Description: 连接池管理器 
 * Copyright: Copyright (c) 2002/12/25 
 * Version 2.0 
 */

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 管理类DBConnectionManager支持对一个或多个由属性文件定义的数据库连接
 * 池的访问.客户程序可以调用getInstance()方法访问本类的唯一实例.
 */
public class ConnectPool {
	static public ConnectPool instance; // 唯一实例
	static public int clients;
	public Vector<Driver> drivers = new Vector<Driver>(); // 驱动
	public PrintWriter log;
	public Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>(); // 连接

	/**
	 * 返回唯一实例.如果是第一次调用此方法,则创建实例
	 * 
	 * @return DBConnectionManager 唯一实例
	 */
	static synchronized public ConnectPool getInstance() {
		if (instance == null) {
			instance = new ConnectPool();
		}

		clients++;

		return instance;
	}

	/**
	 * 建构函数私有以防止其它对象创建本类实例
	 */
	public ConnectPool() {
		init();
	}

	/**
	 * 将连接对象返回给由名字指定的连接池
	 * 
	 * @param name
	 *            在属性文件中定义的连接池名字
	 * @param con
	 *            连接对象
	 */
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			pool.freeConnection(con);
		} else {
			System.out.println("pool ==null");
		}
		clients--;
	}

	/**
	 * 获得一个可用的(空闲的)连接.如果没有可用连接,且已有连接数小于最大连接数 限制,则创建并返回新连接
	 * 
	 * @param name
	 *            在属性文件中定义的连接池名字
	 * @return Connection 可用连接或null
	 */
	public Connection getConnection(String name) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			// return pool.getConnection();
			return pool.returnConnection();
		}
		return null;
	}

	/**
	 * 获得一个可用连接.若没有可用连接,且已有连接数小于最大连接数限制, 则创建并返回新连接.否则,在指定的时间内等待其它线程释放连接.
	 * 
	 * @param name
	 *            连接池名字
	 * @param time
	 *            以毫秒计的等待时间
	 * @return Connection 可用连接或null
	 */
	public Connection getConnection(String name, long time) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			return pool.getConnection(time);
		}
		return null;
	}

	/**
	 * 关闭所有连接,撤销驱动程序的注册
	 */
	public synchronized void release() {
		// 等待直到最后一个客户程序调用
		if (--clients != 0) {
			return;
		}

		Enumeration<DBConnectionPool> allPools = pools.elements();
		while (allPools.hasMoreElements()) {
			DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
			pool.release();
		}
		Enumeration<Driver> allDrivers = drivers.elements();
		while (allDrivers.hasMoreElements()) {
			Driver driver = (Driver) allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);

				log("撤销JDBC驱动程序 " + driver.getClass().getName() + "的注册");
			} catch (SQLException e) {
				log(e, "无法撤销下列JDBC驱动程序的注册: " + driver.getClass().getName());
			}
		}
	}

	/**
	 * 根据指定属性创建连接池实例.
	 * 
	 * @param props
	 *            连接池属性
	 */
	private void createPools(Properties props) {
		Enumeration<?> propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			String name = (String) propNames.nextElement();
			if (name.endsWith(".url")) {
				String poolName = name.substring(0, name.lastIndexOf("."));
				String url = props.getProperty(poolName + ".url");
				if (url == null) {
					log("没有为连接池" + poolName + "指定URL");
					continue;
				}
				String user = props.getProperty(poolName + ".user");
				String password = props.getProperty(poolName + ".password");
				String maxconn = props.getProperty(poolName + ".maxconn", "0");
				int max;
				try {
					max = Integer.valueOf(maxconn).intValue();
				} catch (NumberFormatException e) {
					log("错误的最大连接数限制: " + maxconn + " .连接池: " + poolName);
					max = 0;
				}
				DBConnectionPool pool = new DBConnectionPool(poolName, url,
						user, password, max);
				pools.put(poolName, pool);
				log("成功创建连接池" + poolName);
			}
		}
	}

	/**
	 * 读取属性完成初始化
	 */
	private void init() {
		try {
			Properties p = new Properties();
			String configs = System.getProperty("user.dir")
					+ "confdb.properties";

			System.out.println("configs file local at " + configs);
			FileInputStream is = new FileInputStream(configs);
			Properties dbProps = new Properties();
			try {
				dbProps.load(is);
			} catch (Exception e) {
				System.err.println("不能读取属性文件. "
						+ "请确保db.properties在CLASSPATH指定的路径中");
				return;
			}
			String logFile = dbProps.getProperty("logfile",
					"DBConnectionManager.log");
			try {

				log = new PrintWriter(new FileWriter(logFile, true), true);
			} catch (IOException e) {
				System.err.println("无法打开日志文件: " + logFile);
				log = new PrintWriter(System.err);
			}
			loadDrivers(dbProps);
			createPools(dbProps);
		} catch (Exception e) {
		}
	}

	/**
	 * 171 * 装载和注册所有JDBC驱动程序 172 * 173 * @param props 属性 174
	 */
	private void loadDrivers(Properties props) {
		String driverClasses = props.getProperty("drivers");
		StringTokenizer st = new StringTokenizer(driverClasses);
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			try {
				Driver driver = (Driver) Class.forName(driverClassName)
						.newInstance();
				DriverManager.registerDriver(driver);
				drivers.addElement(driver);
				System.out.println(driverClassName);
				log("成功注册JDBC驱动程序" + driverClassName);
			} catch (Exception e) {
				log("无法注册JDBC驱动程序: " + driverClassName + ", 错误: " + e);
			}
		}
	}

	/**
	 * 将文本信息写入日志文件
	 */
	private void log(String msg) {
		log.println(new Date() + ": " + msg);
	}

	/**
	 * 将文本信息与异常写入日志文件
	 */
	private void log(Throwable e, String msg) {
		log.println(new Date() + ": " + msg);
		e.printStackTrace(log);
	}

	/**
	 * 此内部类定义了一个连接池.它能够根据要求创建新连接,直到预定的最 大连接数为止.在返回连接给客户程序之前,它能够验证连接的有效性.
	 */

	class DBConnectionPool {
		// private int checkedOut;
		private Vector<Connection> freeConnections = new Vector<Connection>();
		private int maxConn;
		private String name;
		private String password;
		private String URL;
		private String user;

		/**
		 * 创建新的连接池
		 * 
		 * @param name
		 *            连接池名字
		 * @param URL
		 *            数据库的JDBC URL
		 * @param user
		 *            数据库帐号,或 null
		 * @param password
		 *            密码,或 null
		 * @param maxConn
		 *            此连接池允许建立的最大连接数
		 */
		public DBConnectionPool(String name, String URL, String user,
				String password, int maxConn) {
			this.name = name;
			this.URL = URL;
			this.user = user;
			this.password = password;
			this.maxConn = maxConn;
		}

		/**
		 * 将不再使用的连接返回给连接池
		 * 
		 * @param con
		 *            客户程序释放的连接
		 */
		public synchronized void freeConnection(Connection con) {
			// 将指定连接加入到向量末尾
			try {
				if (con.isClosed()) {
					System.out.println("before freeConnection con is closed");
				}
				freeConnections.addElement(con);
				Connection contest = (Connection) freeConnections.lastElement();
				if (contest.isClosed()) {
					System.out
							.println("after freeConnection contest is closed");
				}
				notifyAll();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

		/**
		 * 从连接池获得一个可用连接.如没有空闲的连接且当前连接数小于最大连接 数限制,则创建新连接.如原来登记为可用的连接不再有效,则从向量删除之,
		 * 然后递归调用自己以尝试新的可用连接.
		 */
		public synchronized Connection getConnection() {
			Connection con = null;
			if (freeConnections.size() > 0) {
				// 获取向量中第一个可用连接
				con = (Connection) freeConnections.firstElement();
				freeConnections.removeElementAt(0);
				try {
					if (con.isClosed()) {
						log("从连接池" + name + "删除一个无效连接");
						System.out.println("从连接池" + name + "删除一个无效连接");
						// 递归调用自己,尝试再次获取可用连接
						con = getConnection();
					}
				} catch (SQLException e) {
					log("从连接池" + name + "删除一个无效连接时错误");
					System.out.println("从连接池" + name + "删除一个无效连接出错");
					// 递归调用自己,尝试再次获取可用连接
					con = getConnection();
				}
				if (freeConnections.size() > maxConn) {
					System.out.println(" 删除一个溢出连接 ");
					releaseOne();
				}
			}

			else if ((maxConn == 0) || (freeConnections.size() < maxConn)) {
				con = newConnection();
			}

			return con;
		}

		public synchronized Connection returnConnection() {
			Connection con = null;
			// 如果闲置小于最大连接,返回一个新连接
			if (freeConnections.size() < maxConn) {
				con = newConnection();
			}
			// 如果闲置大于最大连接，返回一个可用的旧连接
			else if (freeConnections.size() >= maxConn) {

				con = (Connection) freeConnections.firstElement();
				System.out.println(" [a 连接池可用连接数 ] : " + "[ "
						+ freeConnections.size() + " ]");
				freeConnections.removeElementAt(0);
				System.out.println(" [b 连接池可用连接数 ] : " + "[ "
						+ freeConnections.size() + " ]");
				try {
					if (con.isClosed()) {
						log("从连接池" + name + "删除一个无效连接");
						System.out.println("从连接池" + name + "删除一个无效连接");
						returnConnection();
					}
				} catch (SQLException e) {
					log("从连接池" + name + "删除一个无效连接时错误");
					System.out.println("从连接池" + name + "删除一个无效连接出错");
					returnConnection();
				}
			}
			return con;
		}

		/**
		 * 从连接池获取可用连接.可以指定客户程序能够等待的最长时间 参见前一个getConnection()方法.
		 * 
		 * @param timeout
		 *            以毫秒计的等待时间限制
		 */
		public synchronized Connection getConnection(long timeout) {
			long startTime = new Date().getTime();
			Connection con;
			while ((con = getConnection()) == null) {
				try {
					wait(timeout);
				} catch (InterruptedException e) {
				}
				if ((new Date().getTime() - startTime) >= timeout) {
					// wait()返回的原因是超时
					return null;
				}
			}
			return con;
		}

		/**
		 * 关闭所有连接
		 */
		public synchronized void release() {
			Enumeration<Connection> allConnections = freeConnections.elements();
			while (allConnections.hasMoreElements()) {
				Connection con = (Connection) allConnections.nextElement();
				try {
					con.close();
					log("关闭连接池" + name + "中的一个连接");
				} catch (SQLException e) {
					log(e, "无法关闭连接池" + name + "中的连接");
				}
			}
			freeConnections.removeAllElements();
		}

		/**
		 * 关闭一个连接
		 */
		public synchronized void releaseOne() {
			if (freeConnections.firstElement() != null) {
				Connection con = (Connection) freeConnections.firstElement();
				try {
					con.close();
					System.out.println("关闭连接池" + name + "中的一个连接");
					log("关闭连接池" + name + "中的一个连接");
				} catch (SQLException e) {

					System.out.println("无法关闭连接池" + name + "中的一个连接");
					log(e, "无法关闭连接池" + name + "中的连接");
				}
			} else {
				System.out
						.println("releaseOne() bug.......................................................");

			}
		}

		/**
		 * 创建新的连接
		 */
		private Connection newConnection() {
			Connection con = null;
			try {
				if (user == null) {
					con = DriverManager.getConnection(URL);
				} else {
					con = DriverManager.getConnection(URL, user, password);
				}
				log("连接池" + name + "创建一个新的连接");

			} catch (SQLException e) {
				log(e, "无法创建下列URL的连接: " + URL);
				return null;
			}
			return con;
		}
	}
}