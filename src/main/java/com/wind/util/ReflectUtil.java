package com.wind.util;

import com.wind.entity.Const;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 类反射工具类
 * @author wind
 */
public class ReflectUtil {

    public void parseClass(Object obj){
        if(obj != null){
            Class c = obj.getClass();
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = c.getDeclaredFields();
            for(Field field : fields){

            }

            Method[] methods = c.getDeclaredMethods();
            for(Method method : methods){

            }
        }
    }

    /**
     * bean实体类转换成map，字段名为key，值为value
     * @param obj
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj){
        Map<String, Object> map = null;
        if(obj != null){
            map = new HashMap<>(Const.MAP_SIZE);
            Class c = obj.getClass();
            Field[] fields = c.getDeclaredFields();
            for(Field field : fields){
                try {
                    String key = field.getName();
                    String methodName = key.substring(0, 1).toUpperCase() + key.substring(1);
                    Method m = c.getDeclaredMethod("get" + methodName);
                    Object value = m.invoke(obj);
                    map.put(key, value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(map);
        }
        return map;
    }

    public static void main(String[] args) {

    }
}
