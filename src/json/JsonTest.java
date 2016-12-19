package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;


public class JsonTest {
    
//	JSON json=com.kingdee.youshang.json.JSON.parse( "" );
	
	public static void main( String[] args ) {

//        String str="{deviceHash:9876543210,sdkVersion: 1025,model:{ hash:123456789, model:12345, cpu_count:8, device_total_memory:1024000, app_max_memory:102400000, screen_with:1092, screen_height:786} }";
//        System.out.println(str);
//		String str = "{\tname: 'jack',\tno: 10,\tids: [10, 11, 12, 13],\tmemo: ''}";
        String str = "{\tname: 'jack',\tno: 10,\tids: [10, 11, 12, 13] ''}";
		Entity entity = JSON.parseObject(str, Entity.class);
        System.out.println(entity.toString());
		System.out.println(entity.getMemo() == null);

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

class Entity{

    @JSONField
    private String name;

    @JSONField
    private Integer no;

    @JSONField
    private Set<Long> ids;

    @JSONField
    private String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        String back=" ";
		return getName() +back+getIds()+back + getNo() +back+ getMemo();
    }
}