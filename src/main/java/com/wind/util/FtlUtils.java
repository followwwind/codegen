package com.wind.util;

import com.wind.entity.Const;
import com.wind.entity.db.Column;
import com.wind.entity.db.Table;
import com.wind.entity.freemarker.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * freeMarker工具生成类
 * @author wind
 */
public class FtlUtils {

    /**
     * 生成文件
     * @param freeMarker
     */
    public static void genCode(FreeMarker freeMarker){
        try {
            File dir = new File(freeMarker.getFileDir());
            boolean sign = true;
            if(!dir.exists()){
                sign = dir.mkdirs();
            }

            if(sign){
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
                cfg.setDirectoryForTemplateLoading(new File(freeMarker.getCfgDir()));
                cfg.setDefaultEncoding(Const.UTF8);
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                Template temp = cfg.getTemplate(freeMarker.getCfgName());

                OutputStream fos = new FileOutputStream( new File(dir, freeMarker.getFileName()));
                Writer out = new OutputStreamWriter(fos);
                temp.process(freeMarker.getMap(), out);
                fos.flush();
                out.close();
                System.out.println("gen code success!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * db table转换成java实体类
     * @param table
     * @return
     */
    public static ClassInfo getBean(Table table){
        ClassInfo classInfo = null;
        if(table != null){
            classInfo = new ClassInfo(table.getProperty(), ClassType.BEAN, Const.CLASS);
            List<ClassField> fields = new ArrayList<>();
            List<Column> columns = table.getColumns();
            if(columns != null && !columns.isEmpty()){
                columns.forEach(column -> {
                    String property = column.getProperty();
                    ClassField field = new ClassField(property, column.getType());
                    field.setRemark(column.getRemarks());
                    fields.add(field);
                });
            }
            classInfo.setFields(fields);
            classInfo.initImports();
        }
        return classInfo;
    }

    public static ClassInfo getMapper(String name, Attribute id, Attribute r){
        ClassInfo classInfo = new ClassInfo(name, ClassType.INTERFACE, Const.INTERFACE);

        List<Attribute> ids = new ArrayList<>();
        ids.add(id);

        List<Attribute> rs = new ArrayList<>();
        rs.add(r);

        List<ClassMethod> methods = new ArrayList<>();
        String rType = r.getType();

        ClassMethod insert = new ClassMethod("insert", "void");
        insert.setArgs(rs);
        insert.setRemark("添加记录");
        methods.add(insert);

        ClassMethod selectByPrimaryKey = new ClassMethod("selectByPrimaryKey", rType);
        selectByPrimaryKey.setArgs(ids);
        selectByPrimaryKey.setRemark("id查询单条记录");
        methods.add(selectByPrimaryKey);


        ClassMethod selectByCondition = new ClassMethod("selectByCondition", "List<" + rType + ">");
        selectByCondition.setArgs(rs);
        selectByCondition.setRemark("条件批量查询记录");
        methods.add(selectByCondition);

        ClassMethod deleteByPrimaryKey = new ClassMethod("deleteByPrimaryKey", "int");
        deleteByPrimaryKey.setArgs(ids);
        deleteByPrimaryKey.setRemark("删除记录");
        methods.add(deleteByPrimaryKey);

        ClassMethod updateByPrimaryKeySelective = new ClassMethod("updateByPrimaryKeySelective", "int");
        updateByPrimaryKeySelective.setArgs(rs);
        updateByPrimaryKeySelective.setRemark("更新记录");
        methods.add(updateByPrimaryKeySelective);

        ClassMethod countByCondition = new ClassMethod("countByCondition", "int");
        countByCondition.setArgs(rs);
        countByCondition.setRemark("查询批量记录条数");
        methods.add(countByCondition);

        classInfo.setMethods(methods);
        classInfo.initImports();

        return classInfo;
    }
}
