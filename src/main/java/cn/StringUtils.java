package cn;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 描述:
 * 
 * @author emil date:Jan 12, 2006
 *         <p>
 * @version 2.0
 */
public class StringUtils {

	/**
	 * 分号分隔符
	 */
	public static final String SEMICOLON = ";";

	/**
	 * 逗号分隔符
	 */
	public static final String COMMA = ",";

	/**
	 * 是否为空，null和""都认为是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null || str.trim().length() == 0)
			return true;
		return false;
	}
	/**
	 * 左面填充，当字符串长度不够的时候，使用padStr，否则直接返回
	 * @param text 原字符串
	 * @param length 长度
	 * @param padStr 填充字符
	 * @return
	 */
	public static String leftPadStr(String text,int length,String padStr){
		if(text.length() >= length)
			return text;
		StringBuilder sb = new StringBuilder();
		int len = length - text.length();
		for(int i=0;i<len;i++){
			sb.append(padStr);
		}
		sb.append(text);
		
		return sb.toString();
	}
	/**
	 * 替换分割符。
	 * 
	 * @param str 字符串
	 * @param oldSeparator 原分隔符
	 * @param newSeparator 新分隔符
	 */
	public static String replace(String str, String oldSeparator, String newSeparator) {
		int len;
		int pos;
		int lastPos;

		len = newSeparator.length();
		pos = str.indexOf(oldSeparator);
		lastPos = pos;

		while (pos > -1) {
			String firstPart;
			String lastPart;

			firstPart = str.substring(0, pos);
			lastPart = str.substring(pos + oldSeparator.length(), str.length());
			str = firstPart + newSeparator + lastPart;
			lastPos = pos + len;
			pos = str.indexOf(oldSeparator, lastPos);
		}

		return str;
	}

	public static String handleString(String input, String replace) {
		if (input == null || input.equals("null")) return replace;
		else
			return input;
	}

	public static String replaceString(Object condition, String input, String replace) {
		if (condition == null) return replace;
		else
			return input;
	}

	public static boolean isFloat(String input) {
		if (input == null) return false;
		int length = input.trim().length();
		if (length <= 0) return false;
		int count = 0;
		for (int i = 0; i < length; i++) {
			char temp = input.charAt(i);
			if (temp < '0' || temp > '9') {
				if (i == 0 && (temp == '-' || temp == '+')) continue;
				if (temp == '.' && count < 1) count++;
				else
					return false;
			}
		}
		return true;
	}

	public static boolean isTimestamp(String input) {
		try {
			Timestamp.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isLong(String input) {
		try {
			Long.parseLong(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isBoolean(String input) {
		if (input == null || input.trim().length() == 0) return false;
		if (input.trim().equals("1") || input.trim().equals("0")) return true;
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] split(String line, String seperator) {
		if (line == null) return new String[0];

		line = line.trim();

		if (line.length() == 0) return new String[0];

		java.util.Vector v = new java.util.Vector();
		int i = 0;
		int j;

		while ((j = line.indexOf(seperator, i)) >= 0) {
			String str = line.substring(i, j).trim();
			if (str.trim().length() > 0) v.addElement(str);
			i = j + seperator.length();
		}
		String str = line.substring(i).trim();
		if (str.trim().length() > 0) v.addElement(str);

		int size = v.size();
		String[] ps = new String[size];

		if (size > 0) v.copyInto(ps);

		return ps;
	}

	public static String iso8859ToGbk(String temp) {
		if (temp == null || temp.length() == 0) return "";
		else
			try {
				return new String(temp.getBytes("ISO8859_1"), "GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return "";
	}

	/**
	 * 将字符串中的全角字符转化为半角字符
	 * 
	 * @param title
	 * @return
	 */
	public static final String fullAngleToHalfAngle(String title) {
		char chars[] = new char[title.length()];
		for (int i = 0; i < title.length(); i++) {
			char c = title.charAt(i);
			switch (c) {
			case 'ａ':
				chars[i] = 'a';
				break;
			case 'ｂ':
				chars[i] = 'b';
				break;
			case 'ｃ':
				chars[i] = 'c';
				break;
			case 'ｄ':
				chars[i] = 'd';
				break;
			case 'ｅ':
				chars[i] = 'e';
				break;
			case 'ｆ':
				chars[i] = 'f';
				break;

			case 'ｇ':
				chars[i] = 'g';
				break;
			case 'ｈ':
				chars[i] = 'h';
				break;
			case 'ｉ':
				chars[i] = 'i';
				break;
			case 'ｊ':
				chars[i] = 'j';
				break;
			case 'ｋ':
				chars[i] = 'k';
				break;
			case 'ｌ':
				chars[i] = 'l';
				break;

			case 'ｍ':
				chars[i] = 'm';
				break;
			case 'ｎ':
				chars[i] = 'n';
				break;
			case 'ｏ':
				chars[i] = 'o';
				break;
			case 'ｐ':
				chars[i] = 'p';
				break;
			case 'ｑ':
				chars[i] = 'q';
				break;
			case 'ｒ':
				chars[i] = 'r';
				break;

			case 'ｓ':
				chars[i] = 's';
				break;
			case 'ｔ':
				chars[i] = 't';
				break;
			case 'ｕ':
				chars[i] = 'u';
				break;
			case 'ｖ':
				chars[i] = 'v';
				break;
			case 'ｗ':
				chars[i] = 'w';
				break;
			case 'ｘ':
				chars[i] = 'x';
				break;
			case 'ｙ':
				chars[i] = 'y';
				break;
			case 'ｚ':
				chars[i] = 'z';
				break;

			case '？':
				chars[i] = '?';
				break;
			case '！':
				chars[i] = '!';
				break;
			case '；':
				chars[i] = ';';
				break;
			case '：':
				chars[i] = ':';
				break;
			case '，':
				chars[i] = ',';
				break;
			case '＂':
				chars[i] = '"';
				break;
			case '＇':
				chars[i] = '\'';
				break;

			case '＃':
				chars[i] = '#';
				break;
			case '＄':
				chars[i] = '$';
				break;
			case '＠':
				chars[i] = '@';
				break;
			case '　':
				chars[i] = ' ';
				break;

			case '＋':
				chars[i] = '+';
				break;
			case '－':
				chars[i] = '-';
				break;
			case '／':
				chars[i] = '/';
				break;
			case '＊':
				chars[i] = '*';
				break;
			case '（':
				chars[i] = '(';
				break;
			case '）':
				chars[i] = ')';
				break;
			case '｛':
				chars[i] = '{';
				break;
			case '｝':
				chars[i] = '}';
				break;
			case '［':
				chars[i] = '[';
				break;
			case '］':
				chars[i] = ']';
				break;
			default:
				chars[i] = c;
			}
		}
		return new String(chars);
	}

	public static String fillZeroStr(String strTemp, int length) {
		StringBuilder sbTemp = new StringBuilder();
		if (strTemp == null) {
			for (int i = 0; i < length; i++)
				sbTemp.append("0");
			return sbTemp.toString();
		}
		int temp = length - strTemp.length();
		if (temp > 0) {
			for (int i = 0; i < temp; i++)
				sbTemp.append("0");
			sbTemp.append(strTemp);
			return sbTemp.toString();
		} else
			return strTemp.substring(0, length);
	}

	/**
	 * 替换特殊字符以使javascript正常运行
	 * 
	 * @param input
	 * @return
	 */
	public static String replaceSpecialChar(String input) {
		if (input == null) return input;
		StringBuffer filtered = new StringBuffer(input.length());
		char prevChar = '\u0000';
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (c == '"') filtered.append("\\\"");
			else if (c == '\'') filtered.append("\\'");
			else if (c == '\\') filtered.append("\\\\");
			else if (c == '\t') filtered.append("\\t");
			else if (c == '\n') {
				if (prevChar != '\r') filtered.append("\\n");
			} else if (c == '\r') filtered.append("\\n");
			else if (c == '\f') filtered.append("\\f");
			else if (c == '/') filtered.append("\\/");
			else
				filtered.append(c);
			prevChar = c;
		}
		return filtered.toString();
	}

	/**//*
		 * 将数值转换成人民币大写，根据网上一搜索整理修改而成 仍然有两个Bug 1、万亿以上可能出错 2、连续0可能出错
		 */
	public static String getNumberToRMB(String rmb) {
		/**//*
			 * 人民币表达式中使用的中文字符(num)及计量单位位字符(dw)
			 */
		String num = "零壹贰叁肆伍陆柒捌玖";
		String dw = "元拾佰仟万亿";
		// 补齐小数位，多加“0”不影响计算和转换
		rmb += rmb.indexOf(".") == -1 ? ".00" : "00";
		String mm[] = rmb.split("\\.");
		String money = mm[0];
		/**//*
			 * 转换小数部分
			 */
		String result = num.charAt(Integer.parseInt("" + mm[1].charAt(0))) + "角" + num.charAt(Integer.parseInt("" + mm[1].charAt(1))) + "分";
		/**//*
			 * 设置循环，从整数最低位开始转换
			 */
		for (int i = 0; i < money.length(); i++) {// i=位数，从个位开始取值
			String str = "";// 设置人民币大写字符初值
			int n = Integer.parseInt(money.substring(money.length() - i - 1, money.length() - i));// 当前位的数值＝n
			str = str + num.charAt(n);// 根据数值取出人民币中文大写字符
			if (i == 0) str = str + dw.charAt(i);// 加上元
			else if ((i + 4) % 8 == 0) str = str + dw.charAt(4);// 加上万
			else if (i % 8 == 0) str = str + dw.charAt(5);// 加上亿
			else
				str = str + dw.charAt(i % 4);// 不为整就求余
			result = str + result;// 把当前字符加到最前面（最高位）
		}
		result = result.replaceAll("零([^亿万元角分])", "零");
		result = result.replaceAll("亿零+万", "亿零");
		result = result.replaceAll("零+", "零");
		result = result.replaceAll("零([亿万元])", "$1");
		result = result.replaceAll("壹拾", "拾");
		result = result.replaceAll("^元", "");
		result = result.replaceAll("零角零分", "整");
		result = result.replaceAll("零分", "整");
		return result;
	}

	/**
	 * 判断字符串是否含有中文字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasChineseChar(String str) {
		if (str == null || str.equals("")) return false;
		char[] allChars = str.toCharArray();
		for (char allChar : allChars)
			if (Pattern.matches("[\u4e00-\u9fa5]", String.valueOf(allChar))) return true;
		return false;
	}

	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}
	
	/**
	 * 解析为Integer List;
	 * @param var
	 * @param seperator
	 * @return
	 */
	public static List<Integer> getIntegerList(String var, String seperator) {
		
		if (var == null || var.length() <= 0) {
			return null;
		}
		
		String[] array = var.trim().split(seperator);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			list.add(Integer.parseInt(array[i]));
		}
		
		return list;
	}
	
	/**
	 * 解析为String List;
	 * @param var
	 * @param seperator
	 * @return
	 */
	public static List<String> getStringList(String var, String seperator) {
		
		if (var == null || var.length() <= 0) {
			return null;
		}
		
		String[] array = var.trim().split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		
		return list;
	}
	
	/**
	 * 判断是否为合法邮箱
	 * 123245@vip.qq.com 也为合法邮箱 2012.02.22 JinJian
	 * @param email
	 * @return
	 */
	public static boolean isVaildEmail(String email){
	     //String emailPattern="[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+|[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+.[a-zA-Z0-9]+";
	     String emailPattern= "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	     boolean result=Pattern.matches(emailPattern, email);
	     return result;
	}
	
	/**
	 * 将输入流信息转换为字符串
	 * @param is
	 * @return
	 */
	public static String valueOf(InputStream is) {
        StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8 * 1024);
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			sb.delete(0, sb.length());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();		
	}
	
	/**
	 * 从异常信息中获取详细错误信息
	 * @param e
	 * @return
	 */
	public static String valueOf(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		return sw.toString();
	}
	
	/**
	 * 隐藏手机号码处理
	 * @param phone 1333333333
	 * @return 133****3333
	 */
	public static String handlePhone(String phone){
		if(StringUtils.isEmpty(phone)) return null;
		if(phone.length() < 7) return phone;
		return phone.substring(0, 3) + "****" + phone.substring(7);
	}
	
	/**
	 * 隐藏邮箱处理
	 * @param email 
	 * @return 
	 */
	public static String handleEmail(String email){
		if(StringUtils.isEmpty(email)) return null;
		
		String prefix = email;
		String endWith = "";
		if (email.indexOf("@") > 0) {
			prefix = email.substring(0, email.indexOf("@"));
			endWith = email.substring(email.indexOf("@"));
		}
		
		if (prefix.length() <= 2)
			prefix = prefix.substring(0,1) + "****";
		else
			prefix = prefix.substring(0,1) + "****" + prefix.substring(prefix.length() - 1);
		
		return prefix + endWith;
	}
}
