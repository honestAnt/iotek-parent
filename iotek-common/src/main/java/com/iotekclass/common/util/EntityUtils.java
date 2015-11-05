package com.iotekclass.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EntityUtils {
protected final transient Log log = LogFactory.getLog(getClass());
	
	private static Map<String, Method> readMethodList = new ConcurrentHashMap<String, Method>();
	private static Map<String, Method> writeMethodList = new ConcurrentHashMap<String, Method>();
	
	public static void doTrans(Object sour, Object dest){
		
		if(sour == null){
			dest = null;
			return;
		}
		
		Class<?> sourClass = sour.getClass();
		Class<?> destClass = dest.getClass();
		
		for(Field modelField : sourClass.getDeclaredFields()){
			
			String fieldName = modelField.getName();
			Object value;
			//不为序列化id
			if(fieldName.equals("serialVersionUID")){
				continue;
			}
			
			String key = sourClass.getName() + "_" + fieldName;
			
			try {
				Method readMethod = readMethodList.get(key);
				
				if(readMethod == null){
					PropertyDescriptor pdModel = new PropertyDescriptor(fieldName, sourClass);
					readMethod = pdModel.getReadMethod();
					readMethodList.put(key, readMethod);
				}
				value = readMethod.invoke(sour);
			} catch (Exception e) {
//				if(log.isDebugEnabled()){
//					log.debug(sour.getClass().getName() + " - " + dest.getClass().getName() + " - " + fieldName + " - " + e.getMessage());
//				}
				continue;
			}
			
			if(value == null){
				continue;
			}else{
				key = destClass.getName() + "_" + fieldName;
			}

			try {
				Method writeMethod = writeMethodList.get(key);
				
				if(writeMethod == null){
					PropertyDescriptor pdDto = new PropertyDescriptor(fieldName, destClass);
					writeMethod = pdDto.getWriteMethod();
					writeMethodList.put(key, writeMethod);
				}
				writeMethod.invoke(dest, value);
			} catch (Exception e) {
//				if(log.isDebugEnabled()){
//					log.debug(sour.getClass().getName() + " - " + dest.getClass().getName() + " - " + fieldName + " - " + e.getMessage());
//				}
				continue;
			}
		}
	}
}
