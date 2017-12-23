package com.wind.util;

import com.wind.entity.db.Column;
import com.wind.entity.db.Table;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            conn =  DsUtils.DATASOURCE.getDataSource().getConnection();
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
     * @param catalog
     * @param tableName
     * @param table
     */
    public static void setTable(String catalog, String tableName, Table table){
        Connection con = null;
        List<Column> columns = new ArrayList<>();
        try {
            con = getConn();
            DatabaseMetaData db = con.getMetaData();
            ResultSet pkRSet = db.getPrimaryKeys(catalog, null, tableName);
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
            ResultSet colRs = db.getColumns(catalog,"%", tableName,"%");
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
     * 表字段类型转换成java类型
     * @param columnType
     * @return
     */
    public static String getFieldType(String columnType){
        String result;
        columnType = columnType != null ? columnType : "";
        switch (columnType){
            case "CHAR" : result = "String";break;
            case "VARCHAR" : result = "String";break;
            case "INT" : result = "Integer";break;
            case "INTEGER" : result = "Integer";break;
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
