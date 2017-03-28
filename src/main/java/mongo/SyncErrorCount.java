package mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * Created by yiban on 2016/11/8.
 */
public class SyncErrorCount {

	private static MongoClient mongoClient = null;

	private static String dbName = "ssjlog";// 数据库名

	private static String hostName = "10.201.3.33";// 主机名 192.168.241.224

	private static String username = "yibanzhi";//root

	private static String password = "d6_33#58cdffbdef2b#H8d77#22f";//kingdee

	private static int port = 27017;// 端口号

	private static int poolSize = 10;// 连接池大小

	private static int blockSize = 20; // 等待队列长度


    //初始化数据库
    private static void init(){
        try {
            MongoClientOptions opt = MongoClientOptions.builder()
                    .connectionsPerHost(poolSize)
                    .threadsAllowedToBlockForConnectionMultiplier(blockSize)
                    .build();
            MongoCredential credential = MongoCredential.createCredential(username, dbName, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);
            mongoClient = new MongoClient(new ServerAddress(hostName, port), credentials, opt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main( String[] args ) {
        try {
            init();
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
            Document bson= Document.parse("{ distinct: 'sys_logs', key: 'bookId', query: {  detail: {   $regex: '标准表t_budget_event同步异常.*'  } }}");//标准表t_budget_event同步异常
            System.out.println(mongoDatabase.runCommand(bson));;
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

}
