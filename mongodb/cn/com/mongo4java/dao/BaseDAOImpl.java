package cn.com.mongo4java.dao;
 
import java.util.List;
 





import com.mongodb.BasicDBObject;
import com.mongodb.DB;
 



 
//数据库基本操作实现
public class BaseDAOImpl implements BaseDAO {
 
    @Override
    public boolean insert(String collectionName, BasicDBObject bean) {
        DB db = MongoDBUtil.getDB();
        db.getCollection(collectionName).insert(bean);
        return false;
    }
 
    @Override
    public boolean delete(String collectionName, BasicDBObject bean) {
        DB db = MongoDBUtil.getDB();
        db.getCollection(collectionName).remove(bean);
        return false;
    }
 
    @SuppressWarnings( "rawtypes" )
	@Override
    public List find(String collectionName, BasicDBObject bean) {
        DB db = MongoDBUtil.getDB();
        List list = db.getCollection(collectionName).find(bean).toArray();
        return list ;
    }
 
    @Override
    public boolean update(String collectionName, BasicDBObject oldBean, BasicDBObject newBean) {
        DB db = MongoDBUtil.getDB();
        db.getCollection(collectionName).update(oldBean, newBean);
        return false;
    }
 
}