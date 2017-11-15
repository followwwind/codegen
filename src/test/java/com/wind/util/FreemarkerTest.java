package com.wind.util;

import com.wind.entity.db.MyBatisTable;
import com.wind.entity.db.Table;
import com.wind.entity.freemarker.Attribute;
import com.wind.entity.freemarker.ClassInfo;
import com.wind.entity.freemarker.ClassType;
import com.wind.entity.freemarker.Method;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;

public class FreemarkerTest {

    public static void genCode() {

        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/freemarker/java"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template temp = cfg.getTemplate("class.ftl");

            List<Attribute> attrs = new ArrayList<>();
            attrs.add(new Attribute("private", "name", "String", "姓名"));
            attrs.add(new Attribute("age", "int"));
            attrs.add(new Attribute("skills", "List<String>"));
            ClassInfo classInfo = new ClassInfo("com.wind.entity", "Person", attrs);


            File dir = new File("E:/Work/Freemarker/src/");
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, "Person.java")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            temp.process(ReflectUtil.beanToMap(classInfo, true), out);

            fos.flush();
            fos.close();

            System.out.println("gen code success!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void genInterface() {

        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/freemarker/java"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template temp = cfg.getTemplate("class.ftl");

            List<Method> methods = new ArrayList<>();
            methods.add(new Method("bbb", "void"));
            ClassInfo classInfo = new ClassInfo();
            classInfo.setPackageName("com.wind.dao");
            classInfo.setScope("public");
            classInfo.setClassType("interface");
            classInfo.setClassName("AAA");
            classInfo.setType("interface");
            classInfo.setMethods(methods);

            File dir = new File("E:/Work/Freemarker/src/");
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, "AAA.java")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            temp.process(ReflectUtil.beanToMap(classInfo, true), out);

            fos.flush();
            fos.close();

            System.out.println("gen code success!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void genMapper(){
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/freemarker/xml"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template temp = cfg.getTemplate("mapper.ftl");

            Table table = HikaricpUtil.getTable("user");
            MyBatisTable myBatisTable = null;
            if(table instanceof MyBatisTable){
                myBatisTable = (MyBatisTable) table;
                myBatisTable.setNamespace("com.wind.dao.userDao");
                myBatisTable.setType("com.wind.entity.User");
            }

            File dir = new File("E:/Work/Freemarker/src/");
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, "mapper.xml")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            temp.process(ReflectUtil.beanToMap(myBatisTable, true), out);

            fos.flush();
            fos.close();

            System.out.println("gen code success!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        genCode();
//        genMapper();
        genInterface();
    }
}
