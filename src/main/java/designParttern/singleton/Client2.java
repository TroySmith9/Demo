package designParttern.singleton;

import java.io.*;

/**
 * 测试反射和反序列化破解单例模式

 *
 */
public class Client2 {
	
	public static void main(String[] args) throws Exception {
//		SingletonDemo6 s1 = SingletonDemo6.getInstance();
//		SingletonDemo6 s2 = SingletonDemo6.getInstance();

		SingletonDemo4 s1 = SingletonDemo4.getInstance();
		SingletonDemo4 s2 = SingletonDemo4.getInstance();

		System.out.println(s1);
		System.out.println(s2);
		
		//通过反射的方式直接调用私有构造器
//		Class<SingletonDemo6> clazz = (Class<SingletonDemo6>) Class.forName("designParttern.singleton.SingletonDemo6");
//		Constructor<SingletonDemo6> c = clazz.getDeclaredConstructor(null);
//		c.setAccessible(true);
//		SingletonDemo6  s3 = c.newInstance();
//		SingletonDemo6  s4 = c.newInstance();
//		System.out.println(s3);
//		System.out.println(s4);
		
		//通过反序列化的方式构造多个对象 
		FileOutputStream fos = new FileOutputStream("d:/a.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s1);
		oos.close();
		fos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/a.txt"));
		SingletonDemo4 s3 =  (SingletonDemo4) ois.readObject();
//		SingletonDemo6 s3 =  (SingletonDemo6) ois.readObject();
		ois.close();
		System.out.println(s3);
		
		
	}
	
	public class StatusExposingServletResponse {
		private int httpStatus;



		public int getStatus() {
			return this.httpStatus;
		}
	}

}

