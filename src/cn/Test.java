package cn;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.slf4j.LoggerFactory;

public class Test {// extends Date

	private String code = null;

	private String name = null;

	protected static SimpleDateFormat labeldtf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	
	public static void main(String[] args) {
		String sql="select sum(case when FLogType=0 then 1 else 0 end) syncStep0,"
				+" sum(case when FLogType=1 then 1 else 0 end) syncStep1,"
				+" sum(case when FLogType=2 then 1 else 0 end) syncStep2,"
				+" sum(case when FLogType=3 then 1 else 0 end) syncStep3,"
				+" sum(case when FLogType=4 then 1 else 0 end) syncStep4,"
				+" sum(case when FLogType=5 then 1 else 0 end) syncStep5,"
				+" sum(case when FLogType=6 then 1 else 0 end) syncStep6,"
				+" sum(case when FLogType=9 then 1 else 0 end) abortCount"
				+" from t_sync_log where FCreateTime>=? and FCreateTime<=? and FSyncCategory=1";
		System.out.println(sql);
//		String [] strs={"aa","bb"};
//		System.out.println(String.join("&",strs));
//		Object str="";
//		System.out.println((Long) str);
//		String syncLabel1=labeldtf.format(new Date(1467365592107l))+String.valueOf(25076939832l);
////		String syncLabel2=labeldtf.format(new Date(1467365594278l))+String.valueOf(25076939832l);
//		System.out.println(syncLabel1);
////		System.out.println(syncLabel2);
//		System.out.println("syncLabel1:"+syncLabel1+" syncLabel2:"+syncLabel2);
		// String datePartten =
		// "^2016-(0[1-9]|(10|11|12))-(0[1-9]|[1-2][0-9]|3[0-1]) (([0-1]?[0-9])|([2][0-3])):([0-5][0-9]):([0-5]?[0-9]).*";
		// System.out.println(datePartten.substring(1));
		// System.out.println(WeekDay.Mon);
		// System.out.println("WeekDay.Mon.name():"+WeekDay.Mon.name());
		// System.out.println(WeekDay.Mon.values());
		// System.out.println(WeekDay.valueOf("1"));
		// String srt[] = "|FD|A".split("\\|");
		// for( int i = 0; i < srt.length; i++ ) {
		// System.out.print(srt[i]+",");
		// }
		// System.out.println("  "+srt.length);
		// System.out.println("<appVersion>".length());
		// System.out.println("appVersion:".length());
		//
		// org.dom4j.Document document = DocumentHelper.createDocument();
		// Element root = document.addElement("PFService");
		// root.addAttribute("cmd", "syncUpload");
		// Element eleErrorCode = root.addElement("ErrorCode");
		// eleErrorCode.setText("");
		// Element eleErrorMsg = root.addElement("ErrorMsg");
		// eleErrorMsg.setText(null);
//		System.out.println("Handling of [com.feidee.money.sync.exception.RequestException]".length());

//		 try {
//             a();
//         } catch(HighLevelException e) {
//        	 
//        	 System.out.println(e.getMessage());
//        	 ThrowableInformation ti=new ThrowableInformation(e);
//        	 for (String string : ti.getThrowableStrRep()) {
//				System.out.println(string);
//			}
//         }

		// Date logday=new Date();
		// Calendar dayStart = Calendar.getInstance();//当日凌晨开始
		// dayStart.setTime(logday);
		// dayStart.set(Calendar.HOUR_OF_DAY, 0);
		// dayStart.set(Calendar.MINUTE, 0);
		// dayStart.set(Calendar.SECOND, 0);
		// dayStart.set(Calendar.MILLISECOND, 0);
		// Calendar dayEnd = Calendar.getInstance();//当日最后一毫秒
		// dayEnd.setTime(logday);
		// dayEnd.set(Calendar.HOUR_OF_DAY, 23);
		// dayEnd.set(Calendar.MINUTE, 59);
		// dayEnd.set(Calendar.SECOND, 59);
		// dayEnd.set(Calendar.MILLISECOND, 999);
		//
		// System.out.println(DateUtil.format(dayStart.getTime(),
		// "yyyy-MM-dd HH:mm:ss SSS"));
		// System.out.println(DateUtil.format(dayEnd.getTime(),
		// "yyyy-MM-dd HH:mm:ss SSS"));

		// String
		// str="PFService cmd=syncUpload><Provider><UserName>43923792@qq.com</UserName><Password encode=\"v2\">4e483bf8193051b13382f91636d08632</Password><SyncAccountBookID>20760925304</SyncAccountBookID><id>iphone</id><version>7.1</version><appName>MyMoney</appName><appVersion>10.1.6</appVersion><UDID>";
		// int versionSt=0;
		// if ((versionSt = str.indexOf("<appVersion>")) >= 1) {// 老增量同步
		// int versionEnd=str.lastIndexOf("</appVersion>");
		// System.out.println(str.substring(versionSt+12,versionEnd));;
		// }

		// testJsonParse();
		// Pattern p=Pattern.compile("");
		// Matcher m=p.matcher("");
		// System.out.println(m.groupCount());
		// while(m.find()){
		// System.out.println(m.group());
		// }

	}

	static void a() throws HighLevelException {
        try {
            b();
        } catch(MidLevelException e) {
            throw new HighLevelException(e);
        }
    }
    static void b() throws MidLevelException {
        c();
    }   
    static void c() throws MidLevelException {
        try {
            d();
        } catch(LowLevelException e) {
            throw new MidLevelException(e);
        }
    }
    static void d() throws LowLevelException { 
       e();
    }
    static void e() throws LowLevelException {
        throw new LowLevelException();
    }

	protected static void testJsonParse() {
		String memo = "'~!@#$%^&*(()_+{}|:'\\',\\daflek,,a;fje'';;\"\"ijap'''',,\'";
		String update = "{$set:{memo:'" + escapseSpecil(memo) + "'}}";
		try {
			System.out.println(Document.parse(update));
			;
		} catch (Exception e) {
			org.slf4j.Logger log = LoggerFactory.getLogger(Test.class);
			log.error(update);
			e.printStackTrace();

		}
	}

	/**
	 * 处理转义符
	 * @param str
	 * @return
	 */
	protected static String escapseSpecil(String str) {
		return str.replace("\\", "\\\\").replace("'", "\\'");
	}

	protected static void changeTestObj(final TestObject obj) {
		obj.context = "aaaaaa";
		System.out.println(obj.context);

	}

	protected static String getErrorByException(Exception e) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(e.getMessage())) {
			sb.append(e.getMessage()).append("\r\n");
		}
		sb.append(e).append("\r\n");
		StackTraceElement[] trace = e.getStackTrace();
		for (int i = 0; i < trace.length; i++) {
			sb.append("\tat " + trace[i]).append("\r\n");
		}

//		Throwable ourCause = e.getCause();
		return sb.toString();
	}

	static void callback() {
		try {
			URL url =
					new URL(
							"http://test.feidee.net/stat/weixin_ad.do?muid=f05d122eac30681772a62812b23df2f3&click_time=1431403499&appid=372353614&click_id=007210548a030059ccdfd1d4&app_type=ios&advertiser_id=1606583");
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line = null;
			StringBuffer result2 = new StringBuffer();
			while ((line = br.readLine()) != null) {
				result2.append(line);
			}
			System.out.println(result2);

			StringBuffer result = new StringBuffer();
			while (br.readLine() != null) {
				result.append(br.readLine());
			}
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void testMapEntry() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("01", "ab");
		map.put("05", "b");
		map.put("02", "c");
		Set<Entry<String, String>> entryset = map.entrySet();
		for (Entry<String, String> entry : entryset) {
			System.out.println("Key:" + entry.getKey() + ",Value:" + entry.getValue());
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

	private static final char[] data = new char[] { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };

	private static final char[] units = new char[] { '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿' };

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

class HighLevelException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4092517673855300016L;

	HighLevelException(Throwable cause) {
		super(cause);
	}
}

class MidLevelException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8789342203581617742L;

	MidLevelException(Throwable cause) {
		super(cause);
	}
}

class LowLevelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3718606650959329222L;
}

class TestObject {
	String context;

	TestObject(String context) {
		this.context = context;
	}
}
