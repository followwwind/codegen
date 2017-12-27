package com.wind.util;

import com.wind.entity.freemarker.ClassInfo;
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
        Class c = ClassInfo.class;
        List<Field> fields = ReflectUtils.getFields(c, true);
        for(Field field : fields){
            System.out.println(field.getName());
        }
    }

    @Test
    public void setField(){
        char c = '*';
    }

    @Test
    public void testDefault(){
        Clazz clazz = new Clazz();
        System.out.println(clazz.getInteger());
        System.out.println(clazz.getI());

        System.out.println(clazz.getCh());
    }


}
