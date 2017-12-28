package com.wind.util;

import com.wind.entity.clazz.Attribute;
import com.wind.entity.clazz.ClassMethod;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class JsonTest {

    @Test
    public void toMap(){
        Class c = ClassMethod.class;
        System.out.println(c instanceof Class);
        ClassMethod classMethod = new ClassMethod("method", "String");
        classMethod.setArgs(Arrays.asList(new Attribute()));
        Map<String, Object> map = JsonUtil.beanToMap(classMethod, true);
        System.out.println(map);
        System.out.println(JsonUtil.toJson(map));
    }

    @Test
    public void toEntity(){
        String jsonStr = "{\"name\":\"name\", \"type\":\"String\"}";
        Object obj = JsonUtil.toEntity(jsonStr, Attribute.class);
        System.out.println(JsonUtil.toJson(obj));

        System.out.println(null instanceof Integer);
    }
}
