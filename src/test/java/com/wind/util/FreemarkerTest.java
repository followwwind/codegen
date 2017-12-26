package com.wind.util;

import com.wind.entity.Const;
import com.wind.entity.ftl.MyBatis;
import com.wind.entity.freemarker.*;
import org.junit.Test;

import java.util.*;

public class FreemarkerTest {

    @Test
    public void genCode() {
        List<ClassField> fields = new ArrayList<>();
        fields.add(new ClassField("private", "name", "String", "姓名"));
        fields.add(new ClassField("age", "int"));
        fields.add(new ClassField("skills", "List<String>"));
        ClassInfo classInfo = new ClassInfo("Person", ClassType.BEAN, Const.CLASS);
        classInfo.setFields(fields);
        classInfo.initImports();
        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "Person.java");
        freeMarker.setMap(ReflectUtils.beanToMap(classInfo, true));
        FtlUtils.genCode(freeMarker);
    }

    @Test
    public void genMapper(){
        MyBatis myBatisTable = new MyBatis();
        myBatisTable.setNamespace("com.wind.dao.UserDao");
        myBatisTable.setType("com.wind.entity.User");
        myBatisTable.setTable(DbUtils.getTable("follow", "user"));

        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/xml", "mapper.ftl",
                "E:/Work/Freemarker/src/", "UserMapper.xml");
        freeMarker.setMap(ReflectUtils.beanToMap(myBatisTable, true));
        FtlUtils.genCode(freeMarker);

    }

    @Test
    public void genBaseMapper(){

        String idType = "PK";
        String rType = "R";
        Attribute id = new Attribute("id", idType);
        Attribute r = new Attribute("r", rType);

        ClassInfo classInfo = FtlUtils.getMapper("BaseMapper<R, PK>", id, r);

        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "BaseMapper.java");
        freeMarker.setMap(ReflectUtils.beanToMap(classInfo, true));
        FtlUtils.genCode(freeMarker);

        ClassInfo userMapper = new ClassInfo("UserMapper", ClassType.INTERFACE, Const.INTERFACE);
        classInfo.setName("BaseMapper<User, String>");
        userMapper.setExtend(classInfo);
        FreeMarker freeMarker2 = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "UserMapper.java");
        freeMarker2.setMap(ReflectUtils.beanToMap(userMapper, true));
        FtlUtils.genCode(freeMarker2);
    }

    @Test
    public void genService(){

        String idType = "PK";
        String rType = "R";
        Attribute id = new Attribute("id", idType);
        Attribute r = new Attribute("r", rType);

        ClassInfo classInfo = FtlUtils.getMapper("BaseService<R, PK>", id, r);

        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "BaseService.java");
        freeMarker.setMap(ReflectUtils.beanToMap(classInfo, true));
        FtlUtils.genCode(freeMarker);

        FreeMarker freeMarker2 = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "BaseServiceImpl.java");

        ClassInfo impl = new ClassInfo("BaseServiceImpl<R, PK>", ClassType.CLASS, Const.ABSTRACT);
        impl.setImpls(Arrays.asList(classInfo));
        impl.setMethods(classInfo.getMethods());

        freeMarker2.setMap(ReflectUtils.beanToMap(impl, true));
        FtlUtils.genCode(freeMarker2);

        ClassInfo iservice = new ClassInfo("UserService", ClassType.INTERFACE, Const.INTERFACE);
        classInfo.setName("BaseService<User, String>");
        iservice.setExtend(classInfo);

        FreeMarker freeMarker3 = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "UserService.java");

        freeMarker3.setMap(ReflectUtils.beanToMap(iservice, true));
        FtlUtils.genCode(freeMarker3);

        ClassInfo service = new ClassInfo("UserServiceImpl", ClassType.CLASS, Const.CLASS);
        impl.setName("BaseServiceImpl<User, String>");
        service.setExtend(impl);

        FreeMarker freeMarker4 = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "UserServiceImpl.java");

        freeMarker4.setMap(ReflectUtils.beanToMap(service, true));
        FtlUtils.genCode(freeMarker4);

    }

    @Test
    public void genEntity(){

    }
}
