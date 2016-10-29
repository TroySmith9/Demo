package junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 单元测试用例基类
 * @author ting
 * @version 1.0
 */
public class SyncServiceTestBase {

	public @Rule TestBeforeRule testBeforeRule = new TestBeforeRule();

	public @Rule TestAfterRule testAfterRule = new TestAfterRule();
	
//	public ServerDataSyncService<SyncData> service;

	protected static final String TESTCASE_FILE_PATH = ClassLoader.getSystemResource( "" ).getFile() + "testCase" + File.separator;

	@BeforeClass
	public static void setUpBeforeClass0() {

	}

	@AfterClass
	public static void tearDownAfterClass0() {}

	@Before
	public void setUpBeforeClass() throws Exception {
//		System.out.println( this.getClass().getName() + "测试开始！" );
	}

	@After
	public void tearDownAfterClass() throws Exception {
//		System.out.println( this.getClass().getName() + "测试完毕！" );
	}
	
	/**
	 * 从文件中读取字符串，读取的文件地址为src/test/resources/testCase/testCaseType/methodName/fileName
	 * @param filepath
	 * @return
	 */
	public static String loadFromFile( String filepath ) {
		String filePath = TESTCASE_FILE_PATH + filepath ;;
		File resultFile = new File( filePath );
		char[] buff = new char[ 64 ];
		int len = 0;
		String resultStr = "";
		try {
			FileReader fd = new FileReader( resultFile );
			while( ( len = fd.read( buff ) ) != -1 ) {
				resultStr += new String( buff, 0, len );
			}
			fd.close();
		} catch( FileNotFoundException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return resultStr;
	}

	/**
	 * 从文件中读取字符串，读取的文件地址为src/test/resources/testCase/testCaseType/methodName/fileName
	 * @param testCaseType Service类型
	 * @param methodName 测试方法名
	 * @param fileName 文件名
	 * @return
	 */
	public static String loadFromFile( String testCaseType, String methodName, String fileName ) {
		String path =  testCaseType + File.separator + methodName + File.separator + fileName;;
		return loadFromFile( path );
	}

	/**
	 * 从文件读取，并尝试转成JsonObject
	 * @param testCaseType Service类型
	 * @param methodName 测试方法名
	 * @param fileName 文件名
	 * @return
	 */
	protected JSONObject loadJsonObjectFromFile( String testCaseType, String methodName, String fileName ){
		return JSON.parseObject( loadFromFile( testCaseType, methodName, fileName ) );
	}
	
	
	
	/**
	 * 判断List集合是否相同
	 * @param expected
	 * @param actual
	 */
	protected <T> void assertEquals(List<T> expected, List<T> actual){
		if(expected ==  null && actual == null){
			return ;
		}
		Assert.assertEquals( actual.size(), expected.size() );
		if( actual.size() == 0 ){
			return ;
		}
//		if( actual.get( 0 ) instanceof SyncData ){
//			for( int i = 0 ; i < actual.size() ; i ++ ) {
//				assertEquals( (SyncData)expected.get( i ), (SyncData)actual.get( i ) );
//			}
//		}else if( actual.get( 0 ) instanceof SyncDataAbout ){
//			for( int i = 0 ; i < actual.size() ; i ++ ) {
//				assertEquals( (SyncDataAbout)expected.get( i ), (SyncDataAbout)actual.get( i ) );
//			}
//		}else{
//			Assert.assertTrue(false);
//		}
	}
	
	
}
