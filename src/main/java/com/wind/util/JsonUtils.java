package com.wind.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.wind.entity.Const;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json工具
 * @author wind
 *
 */
public class JsonUtils {
	
	/**
	 * 将实体类对象转换成字符串
	 * @param data
	 * @return
	 */
	public static String toJson(Object data){
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
			
			@Override
			public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
				return process(arg1);
			}
			
			@Override
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return process(arg0);
			}
			
			private Object process(Object value){  
		        if(value instanceof Date){    
		            return DateUtils.getDateStr((Date)value, "yyyy-MM-dd HH:mm:ss");
		        }    
		        return value == null ? "" : value.toString();    
		    }  
		});  
		// 解析内容
		String str = "";
        
        if(data != null){
        	if(data instanceof List){
        		JSONArray jsonArray = JSONArray.fromObject(data, jsonConfig);
        		str = jsonArray.toString();
        	}else{
        		JSONObject obj = JSONObject.fromObject(data, jsonConfig);
        		str = obj != null ? obj.toString() : "";
        	}
        }
        
        return str;
	}

	/**
	 * bean实体类转换成map，字段名为key，值为value
	 * @param obj
	 * @param flag 若为false,则不获取父类成员属性，true则获取
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj, boolean flag){
		Map<String, Object> map = null;
		if(obj != null){
			List<Field> fields = ReflectUtils.getFields(obj.getClass(), flag);
			fields.forEach(field -> {
				field.setAccessible(true);

			});
		}
		return map;
	}
	
	/**
	 * json字符串转换成实体类
	 * @param jsonStr
	 * @param c
	 * @return
	 */
	public static Object toEntity(String jsonStr, Class<?> c){
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Iterator<?> it = jsonObject.keys();
		Object instance = null; 
		try {
			if(c != null){
				instance = c.newInstance();
			}	
			while(it.hasNext() && instance != null){
				Object key = it.next();
				Object value = jsonObject.get(key);
				if(value == null){
					continue;
				}
				if(value instanceof JSONObject){
					System.out.println("jsonobject");
				}else if(value instanceof JSONArray){
					System.out.println("jsonarray");
				}else{
					Field field = c.getDeclaredField(key.toString());
					// 参数值为true，禁止访问控制检查
					Class<?> clazz = field.getType();
					field.setAccessible(true);
					String val = value.toString();
					try {
						if(clazz.equals(Integer.class)){
							field.set(instance, Integer.valueOf(val));
						}else if(clazz.equals(Double.class)){
							field.set(instance, Double.valueOf(val));
						}else if(clazz.equals(String.class)){
							field.set(instance, val);
						}
					} catch(Exception e) {
						if(e instanceof NumberFormatException){
							field.set(instance, -1);
						}
						continue;
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	
		
		return instance;
	}
	
	
	
	
	public static void main(String[] args) {

	}
	
}
