package com.wind.util;

import com.wind.entity.db.MyBatisTable;
import com.wind.entity.freemarker.*;
import java.util.*;

public class FreemarkerTest {

    public static void genCode() {
        List<Attribute> attrs = new ArrayList<>();
        attrs.add(new Attribute("private", "name", "String", "姓名", ClassType.FIELD));
        attrs.add(new Attribute("age", "int", ClassType.FIELD));
        attrs.add(new Attribute("skills", "List<String>", ClassType.FIELD));
        ClassInfo classInfo = new ClassInfo("com.wind.entity", "Person", attrs);
        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "Person.java");
        freeMarker.setMap(ReflectUtils.beanToMap(classInfo, true));
        FreeMarkerUtils.genCode(freeMarker);
    }

    public static void genInterface() {
        List<Method> methods = new ArrayList<>();
        Attribute attr = new Attribute("user", "User", ClassType.ARG);
        List<Attribute> args = Arrays.asList(attr);

        Method insert = new Method("insert", "void");
        insert.setArgs(args);
        methods.add(insert);

        Method selectByPrimaryKey = new Method("selectByPrimaryKey", "User");
        selectByPrimaryKey.setArgs(Arrays.asList(new Attribute("userId", "String", ClassType.ARG)));
        methods.add(selectByPrimaryKey);


        Method selectByCondition = new Method("selectByCondition", "List<User>");
        selectByCondition.setArgs(args);
        methods.add(selectByCondition);

        Method deleteByPrimaryKey = new Method("deleteByPrimaryKey", "int");
        deleteByPrimaryKey.setArgs(Arrays.asList(new Attribute("userId", "String", ClassType.ARG)));
        methods.add(deleteByPrimaryKey);

        Method updateByPrimaryKeySelective = new Method("updateByPrimaryKeySelective", "int");
        updateByPrimaryKeySelective.setArgs(args);
        methods.add(updateByPrimaryKeySelective);

        Method countByCondition = new Method("countByCondition", "int");
        countByCondition.setArgs(args);
        methods.add(countByCondition);

        ClassInfo classInfo = new ClassInfo("UserDao");
        classInfo.setPackageName("com.wind.dao");
        classInfo.setScope("public");
        classInfo.setClassType("interface");
        classInfo.setType("interface");
        classInfo.setMethods(methods);
        classInfo.initImports();
        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "UserDao.java");
        freeMarker.setMap(ReflectUtils.beanToMap(classInfo, true));
        FreeMarkerUtils.genCode(freeMarker);
    }

    public static void genMapper(){
        MyBatisTable myBatisTable = new MyBatisTable();
        myBatisTable.setNamespace("com.wind.dao.UserDao");
        myBatisTable.setType("com.wind.entity.User");
        HikaricpUtils.setTable("user", myBatisTable);



        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/xml", "mapper.ftl",
                "E:/Work/Freemarker/src/", "UserMapper.xml");
        freeMarker.setMap(ReflectUtils.beanToMap(myBatisTable, true));


        FreeMarker freeMarker2 = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "User.java");

        FreeMarker freeMarker3 = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "UserDao.java");

        ClassInfo classInfo = HikaricpUtils.getBean(myBatisTable);
        freeMarker2.setMap(ReflectUtils.beanToMap(classInfo, true));

        ClassInfo classInfo2 = HikaricpUtils.getMapper(myBatisTable);
        freeMarker3.setMap(ReflectUtils.beanToMap(classInfo2, true));


        FreeMarkerUtils.genCode(freeMarker);
        FreeMarkerUtils.genCode(freeMarker2);
        FreeMarkerUtils.genCode(freeMarker3);
    }

    public static void main(String[] args) {
//        genCode();
        genMapper();
//        genInterface();
    }
}
