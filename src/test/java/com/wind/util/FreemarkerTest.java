package com.wind.util;

import com.wind.entity.freemarker.Attribute;
import com.wind.entity.freemarker.ClassInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;

public class FreemarkerTest {

    public static Map<String, Object> getMap(ClassInfo classInfo){
        // Create the root hash
        Map<String, Object> map = null;
        if(classInfo != null){
            /*map = new HashMap<>();*/

            /*map.put("packageName", classInfo.getPackageName());
            map.put("imports", classInfo.getImports());
            map.put("classRemark", classInfo.getClassRemark());
            map.put("scope", classInfo.getScope());
            map.put("classType", classInfo.getClassType());
            map.put("className", classInfo.getClassName());
            map.put("attrs", classInfo.getAttrs());*/

            map = ReflectUtil.beanToMap(classInfo);
        }
        return map;
    }

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
            temp.process(getMap(classInfo), out);

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
        genCode();
    }
}
