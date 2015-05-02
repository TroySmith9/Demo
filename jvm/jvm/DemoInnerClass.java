package jvm;

import jvm.StaticInnerClass.StaticInnerClss;

public class DemoInnerClass {

	public static void main(String[] args) {

		StaticInnerClss sic = new StaticInnerClss();
		sic.test();
		
//		InnerClass ic=new InnerClass();//不可用
		
//		DemoInnerClass.InnerClass ic=new DemoInnerClass.
		DemoInnerClass dic=new DemoInnerClass();

	}

}

class StaticInnerClass {
	static int a = 3;
	int b = 4;

	static class StaticInnerClss {
		public void test() {
			System.out.println(a);
			// System.out.println(b);
		}
	}

	class InnerClass {
		int a = 3;
		class InnerClss {

			public void test() {
				System.out.println(a);
			}
		}
	}

}