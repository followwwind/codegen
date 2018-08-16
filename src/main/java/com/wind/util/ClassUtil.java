package com.wind.util;

import com.wind.entity.clazz.ClassField;
import com.wind.entity.clazz.ClassInfo;
import com.wind.entity.enums.ClassType;
import com.wind.entity.db.Column;
import com.wind.entity.db.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * 类工具集合
 * @author wind
 */
public class ClassUtil {

    /**
     * db table转换成java实体类
     * @param table
     * @return
     */
    public static ClassInfo getBean(Table table){
        ClassInfo classInfo = null;
        if(table != null){
            classInfo = new ClassInfo(table.getProperty(), ClassType.BEAN, JavaConst.CLASS);
            classInfo.setRemark(table.getRemarks());
            List<ClassField> fields = new ArrayList<>();
            List<Column> columns = table.getColumns();
            if(columns != null && !columns.isEmpty()){
                columns.forEach(column -> {
                    String property = column.getProperty();
                    String type = column.getType();
                    ClassField field = new ClassField(property, type);
                    field.setRemark(column.getRemarks());
                    fields.add(field);
                });
            }
            classInfo.setFields(fields);
        }
        return classInfo;
    }
}
