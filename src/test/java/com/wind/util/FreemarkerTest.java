package com.wind.util;

import com.wind.entity.Const;
import com.wind.entity.db.MyBatisTable;
import com.wind.entity.freemarker.*;
import java.util.*;

public class FreemarkerTest {

    public static void genCode() {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("private", "name", "String", "姓名"));
        fields.add(new Field("age", "int"));
        fields.add(new Field("skills", "List<String>"));
        ClassInfo classInfo = new ClassInfo("Person", ClassType.BEAN, Const.CLASS);
        classInfo.setFields(fields);
        classInfo.initImports();
        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "Person.java");
        freeMarker.setMap(ReflectUtils.beanToMap(classInfo, true));
        FtlUtils.genCode(freeMarker);
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

        ClassInfo classInfo = FtlUtils.getBean(myBatisTable);
        freeMarker2.setMap(ReflectUtils.beanToMap(classInfo, true));

        ClassInfo classInfo2 = FtlUtils.getMapper(myBatisTable, "UserDao");
        freeMarker3.setMap(ReflectUtils.beanToMap(classInfo2, true));


        FtlUtils.genCode(freeMarker);
        FtlUtils.genCode(freeMarker2);
        FtlUtils.genCode(freeMarker3);
    }

    public static void genBaseMapper(){
        List<Method> methods = new ArrayList<>();
        Attribute attr = new Attribute("r", "R");
        List<Attribute> args = Arrays.asList(attr);

        Method insert = new Method("insert", "void");
        insert.setArgs(args);
        methods.add(insert);

        Method selectByPrimaryKey = new Method("selectByPrimaryKey", "User");
        selectByPrimaryKey.setArgs(Arrays.asList(new Attribute("userId", "PK")));
        methods.add(selectByPrimaryKey);


        Method selectByCondition = new Method("selectByCondition", "List<User>");
        selectByCondition.setArgs(args);
        methods.add(selectByCondition);

        Method deleteByPrimaryKey = new Method("deleteByPrimaryKey", "int");
        deleteByPrimaryKey.setArgs(Arrays.asList(new Attribute("userId", "PK")));
        methods.add(deleteByPrimaryKey);

        Method updateByPrimaryKeySelective = new Method("updateByPrimaryKeySelective", "int");
        updateByPrimaryKeySelective.setArgs(args);
        methods.add(updateByPrimaryKeySelective);

        Method countByCondition = new Method("countByCondition", "int");
        countByCondition.setArgs(args);
        methods.add(countByCondition);

        ClassInfo classInfo = new ClassInfo("BaseMapper<R, PK>", ClassType.INTERFACE, Const.INTERFACE);
        classInfo.setPackageName("com.wind.dao");
        classInfo.setMethods(methods);
        classInfo.initImports();

        FreeMarker freeMarker = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "BaseMapper.java");
        freeMarker.setMap(ReflectUtils.beanToMap(classInfo, true));
        FtlUtils.genCode(freeMarker);

        ClassInfo classInfo2 = new ClassInfo("UserMapper", ClassType.DAO, Const.INTERFACE);
        classInfo.setName("BaseMapper<User, String>");
        classInfo2.setExtend(classInfo);
        classInfo2.setPackageName("com.wind.dao");
        classInfo2.initImports();
        FreeMarker freeMarker2 = new FreeMarker("src/main/resources/freemarker/java", "class.ftl",
                "E:/Work/Freemarker/src/", "UserMapper.java");
        freeMarker2.setMap(ReflectUtils.beanToMap(classInfo2, true));
        FtlUtils.genCode(freeMarker2);
    }

    public static void main(String[] args) {
//        genCode();
//        genMapper();
        genBaseMapper();
    }
}
