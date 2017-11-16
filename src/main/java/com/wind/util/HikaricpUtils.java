package com.wind.util;

import com.wind.entity.Const;
import com.wind.entity.db.Column;
import com.wind.entity.db.MyBatisTable;
import com.wind.entity.db.Table;
import com.wind.entity.freemarker.Attribute;
import com.wind.entity.freemarker.ClassInfo;
import com.wind.entity.freemarker.ClassType;
import com.wind.entity.freemarker.Method;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hikaricp连接池工具类
 * @author wind
 */
public class HikaricpUtils {
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConn(){
        Connection conn = null;
        try {
            conn =  HikaricpDs.DATASOURCE.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获取所有数据库实例
     * @return
     */
    public static List<String> getAllDb(){
        Connection con = getConn();
        List<String> dbs = new ArrayList<>();
        try {
            DatabaseMetaData db = con.getMetaData();
            ResultSet rs = db.getCatalogs();
            while(rs.next()){
                String dbName = rs.getString("TABLE_CAT");
                System.out.println(dbName);
                dbs.add(dbName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return dbs;
    }

    /**
     * 获取数据库所有表
     * @param catalog
     * @return
     */
    public static List<String> getTables(String catalog){
        Connection con = getConn();
        List<String> tables = new ArrayList<>();
        try {
            DatabaseMetaData db = con.getMetaData();
            ResultSet tablesResultSet = db.getTables(catalog,null,null, new String[]{"TABLE"});
            while(tablesResultSet.next()){
                String tableName = tablesResultSet.getString("TABLE_NAME");
                System.out.println(tableName);
                tables.add(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return tables;
    }

    /**
     * 获取数据库表的描述信息
     * @param tableName
     */
    public static void setTable(String tableName, Table table){
        Connection con = null;
        List<Column> columns = new ArrayList<>();
        try {
            con = getConn();
            DatabaseMetaData db = con.getMetaData();
            ResultSet pkRSet = db.getPrimaryKeys(null, null, tableName);
            while(pkRSet.next()) {
                System.err.println("TABLE_CAT : "+pkRSet.getObject(1));
                System.err.println("TABLE_SCHEM: "+pkRSet.getObject(2));
                System.err.println("TABLE_NAME : "+pkRSet.getObject(3));
                System.err.println("COLUMN_NAME: "+pkRSet.getObject(4));
                System.err.println("KEY_SEQ : "+pkRSet.getObject(5));
                System.err.println("PK_NAME : "+pkRSet.getObject(6));
                table.setTableCat(pkRSet.getString("TABLE_CAT"));
                table.setTableName(tableName);
                table.setAlias(tableName.substring(0, 1));
                table.setProperty(StringUtils.getCamelCase(tableName, true));
                table.setPkName(pkRSet.getString("COLUMN_NAME"));
            }
            ResultSet colRs = db.getColumns(null,"%", tableName,"%");
            while(colRs.next()) {
                Column column = new Column();
                String colName = colRs.getString("COLUMN_NAME");
                String property = StringUtils.getCamelCase(colName, false);
                column.setColumnName(colName);
                column.setProperty(property);
                String typeName = colRs.getString("TYPE_NAME");
                column.setColumnType(("INT".equalsIgnoreCase(typeName) ? "INTEGER":typeName));
                column.setColumnSize(colRs.getInt("COLUMN_SIZE"));
                column.setRemarks(colRs.getString("REMARKS"));
                column.setDigits(colRs.getInt("DECIMAL_DIGITS"));
                column.setNullable(colRs.getInt("NULLABLE"));
                columns.add(column);
            }

            table.setColumns(columns);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
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
            classInfo = new ClassInfo(table.getProperty());
            List<Attribute> attrs = new ArrayList<>();
            List<Column> columns = table.getColumns();
            if(columns != null && !columns.isEmpty()){
                columns.forEach(column -> {
                    String columnType = column.getColumnType();
                    String property = column.getProperty();
                    Attribute attribute = new Attribute(property, getFieldType(columnType), ClassType.FIELD);
                    attribute.setRemark(column.getRemarks());
                    attrs.add(attribute);
                });
            }

            classInfo.setAttrs(attrs);
            classInfo.setType(Const.BEAN);
            classInfo.setClassType(Const.CLASS);
            classInfo.initImports();
        }
        return classInfo;
    }

    /**
     * 获取mybatis mapper接口
     * @param table
     * @return
     */
    public static ClassInfo getMapper(Table table){
        ClassInfo classInfo = null;
        if(table != null){
            String property = table.getProperty();
            classInfo = new ClassInfo(property + Const.MAPPER);
            List<Method> methods = new ArrayList<>();

            List<Column> columns = table.getColumns();
            String id = "id";
            String idType = "String";
            if(columns.size() > 1){
                Column idCol = columns.get(0);
                id = idCol.getProperty();
                idType = getFieldType(idCol.getColumnType());
            }

            Attribute attr = new Attribute(StringUtils.getFirst(property, false), property, ClassType.ARG);
            List<Attribute> args = Arrays.asList(attr);

            Method insert = new Method("insert", "void");
            insert.setArgs(args);
            insert.setRemark("添加记录");
            methods.add(insert);

            Method selectByPrimaryKey = new Method("selectByPrimaryKey", property);
            selectByPrimaryKey.setArgs(Arrays.asList(new Attribute(id, idType, ClassType.ARG)));
            selectByPrimaryKey.setRemark("id查询单条记录");
            methods.add(selectByPrimaryKey);


            Method selectByCondition = new Method("selectByCondition", "List<" + property + ">");
            selectByCondition.setArgs(args);
            selectByCondition.setRemark("条件批量查询记录");
            methods.add(selectByCondition);

            Method deleteByPrimaryKey = new Method("deleteByPrimaryKey", "int");
            deleteByPrimaryKey.setArgs(Arrays.asList(new Attribute(id, idType, ClassType.ARG)));
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

            classInfo.setPackageName("com.wind.dao");
            classInfo.setScope("public");
            classInfo.setClassType("interface");
            classInfo.setType("interface");
            classInfo.setMethods(methods);
            classInfo.setType(Const.INTERFACE);
            classInfo.setClassType(Const.INTERFACE);
            classInfo.initImports();
        }
        return classInfo;
    }

    /**
     * 表字段类型转换成java类型
     * @param columnType
     * @return
     */
    public static String getFieldType(String columnType){
        String result;
        columnType = columnType != null ? columnType : "";
        switch (columnType){
            case "VARCHAR" : result = "String";break;
            case "INT" : result = "Integer";break;
            case "TIMESTAMP" : result = "Date";break;
            default: result = "String"; break;
        }
        return result;
    }

    /**
     *
     * @param con
     */
    public static void close(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭数据库连接池
     * @param dataSource
     */
    public static void close(HikariDataSource dataSource){
        if(dataSource != null){
            dataSource.close();
        }
    }
}
