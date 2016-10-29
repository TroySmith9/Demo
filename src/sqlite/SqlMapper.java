package sqlite;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;


/**
 * 类似ibatis的sqlite sql映射 
 * @author xiong.xing
 * @date 2011-2-23
 * @version 0.1
 */
public class SqlMapper {
	
	private String id;
	/**
	 * 原始sql，把##内容替换成?的，
	 */
	private String sql;
	
	private float version;
	/**
	 * 参数对应数组
	 */
	private List<String> mapperParams;
	
	public SqlMapper(String id,float version){
		this.id = id;
		this.version = version;
	}

	/**
	 * 设置PreparedStatement的参数
	 * @param ps
	 * @param params
	 * @return 返回正在的文件数组
	 * @throws SQLException
	 */
	public Object[] prepareParams(Object[] params) throws SQLException{
		if(CollectionUtils.isEmpty(mapperParams)){
			//prepareOriginalParams(ps, params);
			return params;
		}else{
			return prepareMapperParams(params);
		}
	}
	/**
	 * 原始，没有使用mapper的参数设置
	 * @param ps
	 * @param params
	 * @throws SQLException
	 */
//	private void prepareOriginalParams(PreparedStatement ps,Object[] params) throws SQLException{
//		if(params != null){
//            for(int i=0;i<params.length;i++){
//                ps.setObject(i+1, params[i]);
//            }
//        }
//	}
	/**
	 * 设置了参数映射
	 * @param ps
	 * @param params
	 * @throws SQLException
	 */
	private Object[] prepareMapperParams(Object[] params) throws SQLException{
		if(params==null)
			throw new IllegalArgumentException("参数 params 不能为空!");
		
		Object obj = params[0];	
		Object[] returnParams = new Object[mapperParams.size()];
		try {
			//StringBuilder sb = new StringBuilder();
			//sb.append("params:[");
			
			for(int i=0;i<mapperParams.size();i++){
				String propertyName = mapperParams.get(i); 
				Object value = JavaBeanAccessPlan.getValue(obj, propertyName);
				returnParams[i] = value;
				//ps.setObject(i+1, value);
				
				//sb.append(value).append(",");
			}
			//sb.append("]");
			//Log.info(sb.toString());
			
			return returnParams;
		} catch (Exception e) {
		}
		return null;
	}

	public String getId() {
		return id;
	}
	public String getSql() {
		return sql;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<String> getMapperParams() {
		return mapperParams;
	}

	public void setMapperParams(List<String> mapperParams) {
		this.mapperParams = mapperParams;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

}
