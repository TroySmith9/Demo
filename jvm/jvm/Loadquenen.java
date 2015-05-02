package jvm;

public class Loadquenen {

	public static void main(String[] args) {
		// System.out.println("A.MAX:"+A.MAX);
		// System.out.println("A.id:"+A.id);
		// A a = new A();
		// System.out.println("main");
		// a.test();
		
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader().getParent());
		System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
		System.out.println(System.getProperty("java.class.path"));
	}
}

class A extends A_father {

	public static int id = 1;

	public static final int MAX = 1;

	static {
		System.out.println("静态区");
	}

	public A() {
		System.out.println("构造方法");
	}

	public void test() {
		System.out.println("test()");
	}
}

class A_father {

	static {
		System.out.println("A_father静态区");
	}

	public A_father() {
		System.out.println("A_father");
	}
}
