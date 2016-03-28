package cn;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonObject;

public class Test {// extends Date

	private String code = null;
	private String name = null;

	public static void main( String[] args ) {
//		String datePartten = "^2016-(0[1-9]|(10|11|12))-(0[1-9]|[1-2][0-9]|3[0-1]) (([0-1]?[0-9])|([2][0-3])):([0-5][0-9]):([0-5]?[0-9]).*";
//		System.out.println(datePartten.substring(1));
//		System.out.println(WeekDay.Mon);
//		System.out.println("WeekDay.Mon.name():"+WeekDay.Mon.name());
//		System.out.println(WeekDay.Mon.values());
//		System.out.println(WeekDay.valueOf("1"));
		//		String srt[] = "|FD|A".split("\\|");
//		for( int i = 0; i < srt.length; i++ ) {
//			System.out.print(srt[i]+",");
//		}
//		System.out.println("  "+srt.length);
		
//		System.out.println(System.getProperty( "java.io.tmpdir" ));
		System.out.println(new Date(1458755271948l));
		
//		try {
//			File file=new File("C:\\c.txt");
//			System.out.println(file.exists());
//			file.delete();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		JsonObject jsObj=new JsonObject();
		
		
		
//		String str="YmxvZ0B6aWh1YS5saQo=";
//		try {
//			
//			System.out.println(new String(Base64.getDecoder().decode(str),"utf-8"));
////			System.out.println();
//			System.out.println(new String(Base64.getEncoder().encode("blog@zihua.li".getBytes()),"utf-8"));
//			System.out.println(new String(Base64.getDecoder().decode("YmxvZ0B6aWh1YS5saQ=="),"utf-8"));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Base64.getDecoder("");
//		System.out.println("out log!");
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("Runnable log");
//				
//			}
//		}).start();
		
//		TestObject obj=new TestObject("hahaha");
//		changeTestObj(obj);
//		System.out.println(obj.context);
//		
	}

	
	 private static void changeTestObj(final TestObject obj) {
		obj.context="aaaaaa";
		System.out.println(obj.context);
		
	}


	static void testMapEntry() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("01", "ab");
		map.put("05", "b");
		map.put("02", "c");
		Set<Entry<String, String>> entryset = map.entrySet();
		for (Entry<String, String> entry : entryset) {
			System.out.println("Key:" + entry.getKey() + ",Value:"
					+ entry.getValue());
		}
	}

	static void testHashcode() {
		Test t1 = new Test();
		Test t2 = new Test();
		Test t3 = t1;
		System.out.println(t1.hashCode());
		System.out.println(t2.hashCode());
		System.out.println(t3.hashCode());

		// System.out.println(t1.code);
		// System.out.println(t1==t2);
		// System.out.println(t1.equals(t2));
		// System.out.println(t1.equals(t3));

		// t3.setCode("11");
		// System.out.println(t1.equals(t3));
	}

	protected static void testStringHashcode() {

		String data = "Hello";
		String data2 = "Hello";
		System.out.println(data.hashCode());
		System.out.println(data2.hashCode());

		String a = new String("i love you");
		String b = new String("i love you");

		System.out.println(a == b);
		System.out.println(a.equals(b));

		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
	}

	protected static void renameFile(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			for (File tempfile : file.listFiles()) {
				String name = tempfile.getName();
				String newname = name.replaceAll("尚学堂_高淇_java300集最全视频教程_", "");
				System.out.println(path + "/" + newname);
				tempfile.renameTo(new File(path + "/" + newname));
			}
		}

	}

	protected static void compareIntAndInteger() {
		int i = 0;
		Integer j = null;
		System.out.println(i);
		System.out.println(j);

	}

	public void test() {
		System.out.println(super.getClass().getName());
	}

	private static final char[] data = new char[] { '零', '壹', '贰', '叁', '肆',
			'伍', '陆', '柒', '捌', '玖' };
	private static final char[] units = new char[] { '元', '拾', '佰', '仟', '万',
			'拾', '佰', '仟', '亿' };

	public static String convert(int money) {
		StringBuffer sbf = new StringBuffer();
		int unit = 0;
		while (money != 0) {
			sbf.insert(0, units[unit++]);
			int number = money % 10;
			sbf.insert(0, data[number]);
			money /= 10;
		}

		return sbf.toString();
	}

	public enum WeekDay {
		Sun(1), Mon, Tue;

		private WeekDay() {
			System.out.println("!!!!");
		}

		private WeekDay(int i) {
			System.out.println("******");
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class TestObject{
	String context;
	
	TestObject(String context){
		this.context=context;
	}
}
