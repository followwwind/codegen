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
public class HikaricpUtil {
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
    public static Table getTable(String tableName){
        Connection con = null;
        List<Column> columns = new ArrayList<>();
        try {
            con = getConn();
            DatabaseMetaData db = con.getMetaData();
            ResultSet colRs = db.getColumns(null,"%", tableName,"%");
            while(colRs.next()) {
                Column column = new Column();
                column.setColumnName(colRs.getString("COLUMN_NAME"));
                column.setColumnType(colRs.getString("TYPE_NAME"));
                column.setColumnSize(colRs.getInt("COLUMN_SIZE"));
                column.setRemarks(colRs.getString("REMARKS"));
                int digits = colRs.getInt("DECIMAL_DIGITS");
                int nullable = colRs.getInt("NULLABLE");
                columns.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
        }

        return new Table(columns);
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
