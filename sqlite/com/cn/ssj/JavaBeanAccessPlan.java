package com.cn.ssj;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取javabean中的属性值
 * @author xiong.xing
 * @date 2011-2-23
 * @version 0.1
 */
public class JavaBeanAccessPlan {

	private static Map<Class<?>,ClassInfo> classInfos = new HashMap<Class<?>, ClassInfo>();
	/**
	 * 获取对象的某个字段值
	 * @param obj
	 * @param propertyName
	 * @return
	 */
	public static Object getValue(Object obj,String propertyName){
		// 为了处理多级属性调用，需递归进行调用
		int position = propertyName.indexOf(".");
		if(position == -1)
			position = propertyName.length();
		
		// 获取值
		String name = propertyName.substring(0,position);
		Class<?> clazz = obj.getClass();
		ClassInfo classInfo = classInfos.get(clazz);
		if(classInfo == null)
			classInfo = initClassInfo(clazz);
		
		Object value = classInfo.getValue(obj, name);
		if(value == null)
			return null;
		
		// 判断是否需要递归
		if(position < propertyName.length()){
			String nextName = propertyName.substring(position + 1);
			value = getValue(value, nextName);
		}
			
		
		return value;
	}
	
	private static ClassInfo initClassInfo(Class<?> clazz){
		synchronized (clazz) {
			ClassInfo classInfo = classInfos.get(clazz);
			if(classInfo == null){
				classInfo = new ClassInfo(clazz);
				classInfos.put(clazz, classInfo);
			}
			
			return classInfo;
		}
	}
}
