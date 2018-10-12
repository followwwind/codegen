package com.wind.util;

import com.wind.entity.clazz.ClassInfo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wind
 */
public class ReflectTest {

    @Test
    public void test(){
        Class<?> c = ClassInfo.class;
        List<Field> fields = ReflectUtil.getFields(c, true);
        for(Field field : fields){
            System.out.println(field.getName());
        }
    }

    @Test
    public void beanToMap(){
        ClassInfo classInfo = new ClassInfo("123");
        System.out.println(ReflectUtil.beanToMap(classInfo, true));
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
