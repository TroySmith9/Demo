package cn;

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
//		convertStr2Json();
//		buildJson();
		
//		Map map=new HashMap<String, String>();
		net.sf.json.JSONObject json=new net.sf.json.JSONObject();
		
		json.put( "11", "aa" );
		json.put( "11", "bb" );
		json.accumulate( "11", "cc" );
		json.accumulate( "22", "cc" );
		json.accumulate( "22", "" );
		json.accumulate( "22", null );
		
		json.put( "33", new JSONObject() );
//		json.accumulate( "33", "dd" );
//		System.out.println("json.opt11:"+json.opt("11"));
//		System.out.println("json.opt44:"+json.opt("44"));
//		System.out.println(json.get("44"));
		
		net.sf.json.JSONObject  newJs=new net.sf.json.JSONObject ();
		newJs.put("55", json.opt("44"));
		newJs.put("66", json.get("44"));
//		json.getJSONObject( "33" ).put( "333", "ccc" );
//		System.out.println(json);
		
//		String group="{'$group':{'_id':{'syncDate':{'$dateToString':{'format':'%Y-%m-%d','date':'$createTime'}},'serverId':'$serverId'},'count':{'$sum':1}}}";
		String group="{'$group':{'_id':{'syncDate':{'$dateToString':{'format':'%Y-%m-%d','date':'$createTime'}},'serverId':'$serverId'},'count':{'$sum':1}}}";
		System.out.println(JSONObject.parse(group));
		System.out.println(JSONObject.parse("{'$dateToString':{'format':'%Y-%m-%d','date':'$createTime'}}"));;
//		System.out.println("{'$dateToString':{'format':'%Y-%m-%d','date':'$createTime'}}");
		
	}


	public static void buildJson() {
		DataSyncProvider provider=new DataSyncProvider();
		provider.setAccountBookID( 1000l );
		
		JSONObject jsonobj=new JSONObject();
		jsonobj.put( "111", "46dafa" );
		jsonobj.put("provider", provider);
		System.out.println(jsonobj.toString());
		
		net.sf.json.JSONObject js=new net.sf.json.JSONObject();
		js.accumulate( "111", "46dafa" );
		js.accumulate("sync_data", provider);
		System.out.println(js.toString());
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
