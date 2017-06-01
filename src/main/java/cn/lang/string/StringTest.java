package cn.lang.string;
public class StringTest {

	public static void main(String[] args) {
		String str="abcddac";
		System.out.println(str.replace("a", "h"));
		System.out.println(str);

		System.out.println(str.replaceAll("a","_"));
	}

}
