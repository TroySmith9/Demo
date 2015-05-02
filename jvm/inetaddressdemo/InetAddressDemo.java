package inetaddressdemo;

import java.net.*;

/**
 * 演示InetAddress类的基本使用
 */
public class InetAddressDemo {
	public static void main(String[] args) {
		try {
			// 使用域名创建对象-InetAddress类:代表一个IP地址，并且将IP地址和域名相关的操作方法包含在该类的内部。
			InetAddress inet1 = InetAddress.getByName("www.163.com");
			System.out.println("inet1:"+inet1);
			// 使用IP创建对象
			InetAddress inet2 = InetAddress.getByName("127.0.0.1");
			System.out.println("inet2:"+inet2);
			// 获得本机地址对象
			InetAddress inet3 = InetAddress.getLocalHost();
			System.out.println("inet3:"+inet3);
			// 获得对象中存储的域名
			String host = inet3.getHostName();
			System.out.println("域名：" + host);
			// 获得对象中存储的IP
			String ip = inet3.getHostAddress();
			System.out.println("IP:" + ip);
		} catch (Exception e) {
		}
	}
}