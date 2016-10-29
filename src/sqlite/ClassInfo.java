package sqlite;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

//import com.kingdee.mobile.framework.Log;

/**
 * class信息类 
 * @author xiong.xing
 * @date 2011-2-23
 * @version 0.1
 */
public class ClassInfo {

	private Class<?> clazz;
	/**
	 * 字段属性映射method
	 */
	private Map<String,Method> getMethods = new HashMap<String, Method>();
	
	public ClassInfo(Class<?> clazz){
		this.clazz = clazz;
		
		getGetMethods();
	}
	
	public Object getValue(Object obj,String propertyName){
		Assert.notNull(obj, "object instance must not be null");
		
		if(!obj.getClass().equals(this.clazz)){
			throw new IllegalArgumentException(obj.getClass().getName() + " not equals " + clazz.getName());
		}
		
		Method m = getMethods.get(propertyName);
		if(m == null){
			throw new RuntimeException("can't find " + propertyName + " get method of " + clazz.getName());
		}
		
		try {
			return m.invoke(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取所有get方法
	 */
	private void getGetMethods(){
		Method[] methods =  clazz.getMethods();
		for(Method method : methods){
			// 仅保存public方法
			if(method.getModifiers() != Modifier.PUBLIC)
				continue;
			
			if(!isGetMethod(method.getName()))
				continue;
			
			String propertyName = getPropertyName(method.getName()); 
			getMethods.put(propertyName, method);
		}
	}
	
	private boolean isGetMethod(String methodName){
		if(methodName.startsWith("get") && methodName.length() > 3)
			return true;
		else if(methodName.startsWith("is") && methodName.length() > 2)
			return true;
		
		return false;
	}

	private String getPropertyName(String methodName){
		if(methodName.startsWith("get")){
			return methodName.substring(3).toLowerCase();
		}else if(methodName.startsWith("is")){
			return methodName.substring(2).toLowerCase();
		}else{
			return null;
		}
	}
}
