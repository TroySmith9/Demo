package jvm;

public class LangTest {
	
	private int i=1;

	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getContextClassLoader());
		ClassLoader cl=Thread.currentThread().getContextClassLoader();
		try {
		Class lt=	cl.loadClass("jvm.LangTest");
		lt.forName("jvm.LangTest");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// String a = "tt";
		// System.out.println(a.toString());
	}
}
