package com.wind.util;

import com.wind.entity.clazz.Attribute;
import com.wind.entity.clazz.ClassMethod;
import org.junit.Test;

import java.util.*;

public class JsonTest {

    @Test
    public void toMap(){
        ClassMethod classMethod = new ClassMethod("method", "String");
        Set<Attribute> list = Collections.singleton(new Attribute());
        classMethod.setArgs(new ArrayList<>(list));
        Map<String, Object> map = ReflectUtil.beanToMap(classMethod, true);
        System.out.println(map);
        System.out.println(JsonUtil.toJson(map));
    }

    @Test
    public void toEntity(){
        String jsonStr = "{\"name\":\"name\", \"type\":\"String\"}";
        Attribute obj = JsonUtil.toBean(jsonStr, Attribute.class);
        System.out.println(JsonUtil.toJson(obj));
        System.out.println(null instanceof Integer);
    }
}
