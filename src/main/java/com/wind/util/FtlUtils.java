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
import java.util.Arrays;
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
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(new File(freeMarker.getCfgDir()));
            cfg.setDefaultEncoding(Const.UTF8);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template temp = cfg.getTemplate(freeMarker.getCfgName());
            File dir = new File(freeMarker.getFileDir());
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, freeMarker.getFileName()));
            Writer out = new OutputStreamWriter(fos);
            temp.process(freeMarker.getMap(), out);
            fos.flush();
            out.close();
            System.out.println("gen code success!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化classinfo信息，包括类型，import导入语句等
     * @param classInfo
     */
    public static void initClassInfo(ClassInfo classInfo){
        if(classInfo != null){
            classInfo.initImports();
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
            List<Field> fields = new ArrayList<>();
            List<Column> columns = table.getColumns();
            if(columns != null && !columns.isEmpty()){
                columns.forEach(column -> {
                    String columnType = column.getColumnType();
                    String property = column.getProperty();
                    Field field = new Field(property, HikaricpUtils.getFieldType(columnType));
                    field.setRemark(column.getRemarks());
                    fields.add(field);
                });
            }
            classInfo.setFields(fields);
            classInfo.initImports();
        }
        return classInfo;
    }

    /**
     * 获取mybatis mapper接口
     * @param table
     * @return
     */
    public static ClassInfo getMapper(Table table, String name){
        ClassInfo classInfo = null;
        if(table != null){
            String property = table.getProperty();
            classInfo = new ClassInfo(name, ClassType.DAO, Const.INTERFACE);
            List<Method> methods = new ArrayList<>();

            List<Column> columns = table.getColumns();
            String id = "id";
            String idType = "String";
            if(columns.size() > 1){
                Column idCol = columns.get(0);
                id = idCol.getProperty();
                idType = HikaricpUtils.getFieldType(idCol.getColumnType());
            }

            Attribute attr = new Attribute(StringUtils.getFirst(property, false), property);
            List<Attribute> args = Arrays.asList(attr);

            Method insert = new Method("insert", "void");
            insert.setArgs(args);
            insert.setRemark("添加记录");
            methods.add(insert);

            Method selectByPrimaryKey = new Method("selectByPrimaryKey", property);
            selectByPrimaryKey.setArgs(Arrays.asList(new Attribute(id, idType)));
            selectByPrimaryKey.setRemark("id查询单条记录");
            methods.add(selectByPrimaryKey);


            Method selectByCondition = new Method("selectByCondition", "List<" + property + ">");
            selectByCondition.setArgs(args);
            selectByCondition.setRemark("条件批量查询记录");
            methods.add(selectByCondition);

            Method deleteByPrimaryKey = new Method("deleteByPrimaryKey", "int");
            deleteByPrimaryKey.setArgs(Arrays.asList(new Attribute(id, idType)));
            deleteByPrimaryKey.setRemark("删除记录");
            methods.add(deleteByPrimaryKey);

            Method updateByPrimaryKeySelective = new Method("updateByPrimaryKeySelective", "int");
            updateByPrimaryKeySelective.setArgs(args);
            updateByPrimaryKeySelective.setRemark("更新记录");
            methods.add(updateByPrimaryKeySelective);

            Method countByCondition = new Method("countByCondition", "int");
            countByCondition.setArgs(args);
            countByCondition.setRemark("查询批量记录条数");
            methods.add(countByCondition);

            classInfo.setMethods(methods);
            classInfo.initImports();
        }
        return classInfo;
    }
}
