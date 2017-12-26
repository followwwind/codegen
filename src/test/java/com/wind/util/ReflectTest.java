package com.wind.util;

import com.wind.entity.freemarker.ClassMethod;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wind
 */
public class ReflectTest {

    @Test
    public void test(){

        Class c = ClassMethod.class;
        List<Field> fields = ReflectUtils.getFields(c, true);
        for(Field field : fields){
            System.out.println(field.getName());
        }

    }
}
