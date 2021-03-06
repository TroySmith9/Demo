package mongo;
 
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
 
 
//数据库工具类
public class MongoDBUtil {
    private static Mongo mongo = null;
     
    private static String DBString = "test";//数据库名
    private static String hostName = "localhost";//主机名
    private static int port = 27017;//端口号
    private static int poolSize = 10;//连接池大小

    //获取数据库连接
    public static DB getDB(){
    	if(mongo == null){
    		init();
    	}
    	return mongo.getDB(DBString);
    }

    //初始化数据库
    @SuppressWarnings( "deprecation" )
    private static void init(){
        try {
            //实例化Mongo
            mongo = new Mongo(hostName, port);
            MongoOptions opt = mongo.getMongoOptions();
            //设置连接池大小
            opt.connectionsPerHost = poolSize;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void main( String[] args ) {
    	try {
    		BaseDAO baseDAOImpl = new BaseDAOImpl();
    		BasicDBObject beanOne = new BasicDBObject();
    		beanOne.put("name", "kakakaka");
    		beanOne.put("sex", "男");
    		beanOne.put("age", 20);
    		baseDAOImpl.insert("test", beanOne);
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
     
}