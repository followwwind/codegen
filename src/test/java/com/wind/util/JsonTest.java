package com.wind.util;

import com.wind.entity.freemarker.Attribute;
import com.wind.entity.freemarker.ClassMethod;
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
        Map<String, Object> map = JsonUtils.beanToMap(classMethod, true);
        System.out.println(map);
        System.out.println(JsonUtils.toJson(map));
    }

    @Test
    public void toEntity(){
        String jsonStr = "{\"name\":\"name\", \"type\":\"String\"}";
        Object obj = JsonUtils.toEntity(jsonStr, Attribute.class);
        System.out.println(JsonUtils.toJson(obj));

        System.out.println(null instanceof Integer);
    }
}
