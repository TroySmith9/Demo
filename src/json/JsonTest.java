package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



public class JsonTest {
    
//	JSON json=com.kingdee.youshang.json.JSON.parse( "" );
	
	public static void main( String[] args ) {
		
		String str="{deviceHash:9876543210,sdkVersion: 1025,model:{ hash:123456789, model:12345, cpu_count:8, device_total_memory:1024000, app_max_memory:102400000, screen_with:1092, screen_height:786} }";
		System.out.println(str);
//		JSONObject js=JSONObject.parseObject(str);
//		convertStr2Json();
//		buildJson();
		
		
	}


	public static void buildJson() {
		DataSyncProvider provider=new DataSyncProvider();
		provider.setAccountBookID( 1000l );
		
		JSONObject jsonobj=new JSONObject();
		jsonobj.put( "111", "46dafa" );
		jsonobj.put("provider", provider);
		System.out.println(jsonobj.toString());
		
		// JsonObject jso=new JsonObject();
		// jso.a
		
		
	}


	public static void convertStr2Json() {
		String jsonstr=loadFromFile( "D://workspace/workspaceForssj/show-money/src/main/java/com/kingdee/youshang/money/server/datasync2/upload.json" );
		System.out.println(jsonstr);
		JSONObject jsonObj=JSON.parseObject(jsonstr);
		Map<?, ?> providermap=( Map<?, ?> )jsonObj.get( "provider" );
		System.out.println(providermap.get( "userName" ));
		DataSyncProvider provider=JSON.parseObject( jsonObj.getString( "provider" ), DataSyncProvider.class );
		System.out.println(provider.getAccountBookID());
//		SyncData syncdata=JSON.parseObject( jsonObj.getString( "sync_data" ), SyncData.class );
	}

	
	public static String loadFromFile( String filePath ) {
		File result = new File( filePath );
		char[] buff = new char[ 64 ];
		int len = 0;
		String resultStr = "";
		try {
			FileReader fd = new FileReader( result );
			while( ( len = fd.read( buff ) ) != -1 ) {
				resultStr += new String( buff, 0, len );
			}
			fd.close();
			return resultStr;
		} catch( FileNotFoundException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return "";
	}
}
