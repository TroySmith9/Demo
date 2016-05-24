package cn.partern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		testSpilt();
	}

	private static void testSpilt() {
		String modelPattern="^*.[iPad|iPhone].*";
//		String versionPattern="^*.OS .*like .*";
		String str1="Mozilla/5.0 (iPhone; CPU iPhone OS 9_2_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13C75";
		
		Pattern p=Pattern.compile(modelPattern);
		Matcher m=p.matcher(str1);
		System.out.println(m.matches());
		System.out.println(m.group());
	}
}
