package memcache;


/**
 * 测试用例
 * @author Administrator
 *
 */
  
public class ExerciseDemo {
//	public static void main(String[] args) {
//		MemCachedClient mcc = null;
//		try {
//			User  u = null;
//			mcc = MemcachedUtil.getInitMemCached();
//			u = (User) mcc.get("user");
//			if(u != null){
//				System.out.println("缓存中读取："+u);
//			}else{
//				u = (User) new UserDao().queryInfoByName("lisi");
//				if(u == null){
//					System.out.println("该用户不存在！！");
//				}else{
//					System.out.println("数据库中查询："+u);
//				}
//				/*
//				 * 放入缓存中
//				 *  add( '键', '值', '期限' );
//					add	仅当存储空间中不存在键相同的数据时才保存
//
//					replace( '键', '值', '期限' );
//					replace	仅当存储空间中存在键相同的数据时才保存
//
//					set( '键', '值', '期限' );
//					set	与add和replace不同，无论何时都保存
//				 */
//				if(u != null){
//					mcc.set("user", u,3600);
//				}
//		 }
//	 		//删除缓存中的对象
//			//mcc.delete("user");
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//
//	}
}
