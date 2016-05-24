package org.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
//		Pattern pattern = Pattern.compile( "^b*g" );
//		Matcher matcher = pattern.matcher( "abbg" );
//		System.out.println( matcher.matches() );
//		
//		System.out.println( Pattern.matches( "b*g", "bbg" ) );
		// 验证邮政编码
//		System.out.println( Pattern.matches( "[0-9]{6}", "200038" ) );
//		System.out.println( Pattern.matches( "//d{6}", "200038" ) );
//		// 验证电话号码
//		charReplace();
		// 验证身份证:判断一个字符串是不是身份证号码，即是否是15或18位数字。
//		System.out.println( Pattern.matches( "^//d{15}|//d{18}$", "123456789009876" ) );
//		getString( "D:/dir1/test.txt" );
//		getChinese( "welcome to china,江西奉新,welcome,你!" );
//		validateEmail( "luosijin123@163.com" );
		
		String pattern="^*.[iPad|iPhone].*OS ().*like .*$";
		String str1="Mozilla/5.0 (iPhone; CPU iPhone OS 9_2_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13C75 ";
		Pattern p=Pattern.compile(pattern);
		System.out.println(Pattern.matches(pattern, str1));
		
		Matcher m = p.matcher(str1);
		System.out.println(m.groupCount());
		System.out.println(m.group());
//		for (int i = 0; i < m.groupCount(); i++) {
//			System.out.println(m.group(i));
//		}
		
	}

	public static void getDate( String str ) {
		String regEx = "([a-zA-Z]+)|//s+[0-9]{1,2},//s*[0-9]{4}";
		Pattern pattern = Pattern.compile( regEx );
		Matcher matcher = pattern.matcher( str );
		if( !matcher.find() ) {
			System.out.println( "日期格式错误!" );
			return;
		}
		System.out.println( matcher.group( 1 ) ); // 分组的索引值是从1开始的，所以取第一个分组的方法是m.group(1)而不是m.group(0)。
	}

	public static void charReplace() {
		String regex = "a+";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( "okaaaa LetmeAseeaaa aa booa" );
		String s = matcher.replaceAll( "A" );
		System.out.println( s );
	}

	public static void getString( String str ) {
		String regex = ".+/(.+)$";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( str );
		if( !matcher.find() ) {
			System.out.println( "文件路径格式不正确！" );
			return;
		}
		System.out.println( matcher.group( 1 ) );
	}

	/**
	 * 中文提取
	 * @param str
	 * @date 2009-11-10 上午12:27:17
	 */
	public static void getChinese( String str ) {
		String regex = "[//u4E00-//u9FFF]";// [//u4E00-//u9FFF]为汉字
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( str );
		StringBuffer sb = new StringBuffer();
		while( matcher.find() ) {
			sb.append( matcher.group() );
		}
		System.out.println( sb );
	}

	/**
	 * 验证Email
	 * @param email
	 * @date 2009-11-10 上午12:34:50
	 */
	public static void validateEmail( String email ) {
		String regex = "[0-9a-zA-Z]+@[0-9a-zA-Z]+//.[0-9a-zA-Z]+";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( email );
		if( matcher.matches() ) {
			System.out.println( "这是合法的Email" );
		} else {
			System.out.println( "这是非法的Email" );
		}
	}
}
