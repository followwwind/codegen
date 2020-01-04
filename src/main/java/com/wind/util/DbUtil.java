package com.wind.util;

import com.wind.callback.DbCallBack;
import com.wind.enums.SqlConst;
import com.wind.entity.db.Column;
import com.wind.entity.db.PrimaryKey;
import com.wind.entity.db.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Title: DbUtil
 * @Package com.wind.util
 * @Description: Db工具类
 * @author wind
 * @date 2020/1/4 18:03
 * @version V1.0
 */
public class DbUtil {

    private static Properties props;

    private DbUtil(){

    }

//    private static Connection conn;

    static {
        props = PropUtil.getProp(DbUtil.class.getResourceAsStream("/jdbc.properties"));
        props.forEach((k, v) -> {
            String value = EnvUtil.get(String.valueOf(k));
            if(StringUtil.isNotEmpty(value)){
                props.put(k, value);
            }
        });
        try {
            Class.forName(props.getProperty("driverClass"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接
     * @return
     * @throws SQLException 
     */
    private static Connection getConn() throws SQLException{
        return DriverManager.getConnection(props.getProperty("jdbcUrl"), props);
    }



    /**
     * 获取元数据信息
     * @param callBack
     */
    private static void exec(DbCallBack callBack){
    	Connection con = null;
        try {
        	con = getConn();
            DatabaseMetaData db = con.getMetaData();
            callBack.call(db);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
    }

    /**
     *
     * @param con
     */
    private static void close(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取所有数据库实例
     * @return
     */
    public static List<String> getAllDb(){
        List<String> dbs = new ArrayList<>();
        exec(db -> {
            ResultSet rs = db.getCatalogs();
            while(rs.next()){
                String dbName = rs.getString("TABLE_CAT");
                dbs.add(dbName);
            }
            rs.close();
        });
        return dbs;
    }
    
    /**
     * 获取数据库所有表
     * @param catalog
     * @return
     */
    public static List<Table> getTables(String catalog){
        List<Table> tables = new ArrayList<>();
        exec(db -> {
            ResultSet rs = db.getTables(catalog, null, null, new String[]{"TABLE"});
            while(rs.next()){
            	Table table = getTable(db, rs);
            	if(table != null) {
            		tables.add(table);
            	}
            }
            rs.close();
        });
        return tables;
    }

    public static List<Table> getTable(String tableName){
        Connection con = null;
        List<Table> tables = new ArrayList<>();
        try {
            con = getConn();
            DatabaseMetaData db = con.getMetaData();
            ResultSet rs = db.getTables(con.getCatalog(), null, "%" + tableName + "%", new String[]{"TABLE"});
            while(rs.next()) {
                Table t = getTable(db, rs);
                if(t != null){
                    tables.add(t);
                }
            }
            rs.close();
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
     * @return
     * @throws Exception 
     */
    public static List<Table> getTable(String catalog, String tableName) {
        Connection con = null;
        List<Table> tables = new ArrayList<>();
        try {
        	con = getConn();
            DatabaseMetaData db = con.getMetaData();
            ResultSet rs = db.getTables(catalog, null, "%" + tableName + "%", new String[]{"TABLE"});
            while(rs.next()) {
                Table t = getTable(db, rs);
                if(t != null){
                    tables.add(t);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return tables;
    }

    /**
     * 组装table数据
     * @param rs
     * @return
     * @throws Exception 
     */
    private static Table getTable(DatabaseMetaData db, ResultSet rs) {
        Table table = null;
        if(rs != null){
            try {
				table = new Table();
				String tableName = rs.getString("TABLE_NAME");
				table.setTableName(tableName);
				table.setProperty(StringUtil.getCamelCase(tableName, true));
				String catalog = rs.getString("TABLE_CAT");
				table.setTableCat(catalog);
				table.setRemarks(rs.getString("REMARKS"));
				table.setPrimaryKeys(getPrimaryKeys(db, catalog, tableName));
				table.setColumns(getColumns(db, catalog, tableName));
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} 
        }
        return table;
    }


    
    /**
     * 获取数据库列信息
     * @param catalog
     * @param tableName
     * @return
     */
    public static List<Column> getColumns(String catalog, String tableName){
        List<Column> columns = new ArrayList<>();
        exec(db -> {
			try {
				columns.addAll(DbUtil.getColumns(db, catalog, tableName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
        return columns;
    }

    /**
     * 组装column数据
     * @param db
     * @param catalog
     * @param tableName
     * @return
     * @throws SQLException 
     * @throws Exception 
     */
    private static List<Column> getColumns(DatabaseMetaData db, String catalog, String tableName) throws SQLException{
        List<Column> columns = new ArrayList<>();
        ResultSet rs = db.getColumns(catalog, "%", tableName, "%");
		while (rs.next()){
		    Column column = new Column();
		    String colName = rs.getString("COLUMN_NAME");
		    String typeName = rs.getString("TYPE_NAME");
		    column.setColumnName(colName);
		    column.setColumnType(typeName);
		    column.setProperty(StringUtil.getCamelCase(colName, false));
		    String type = SqlConst.getFieldType(typeName);
		    if(type == null) {
		    	throw new SQLException("表字段类型转换成java类型，找不到类型,catalog:" + catalog +
                        ",tableName:" + tableName + ",typeName:" + typeName + ",colName:" + colName);
		    }
		    column.setType(type);
		    column.setColumnSize(rs.getInt("COLUMN_SIZE"));
		    column.setNullable(rs.getInt("NULLABLE"));
		    column.setRemarks(rs.getString("REMARKS"));
		    column.setDigits(rs.getInt("DECIMAL_DIGITS"));
		    columns.add(column);
		}
		rs.close();

        return columns;
    }

    /**
     * TABLE_CAT String => 表类别（可为 null）
     * TABLE_SCHEM String => 表模式（可为 null）
     * TABLE_NAME String => 表名称
     * COLUMN_NAME String => 列名称
     * KEY_SEQ short => 主键中的序列号
     * PK_NAME String => 主键的名称（可为 null）
     * @param catalog
     * @param tableName
     * @return
     */
    public static List<PrimaryKey> getPrimaryKeys(String catalog, String tableName){
       List<PrimaryKey> keys = new ArrayList<>();
       exec(db -> keys.addAll(DbUtil.getPrimaryKeys(db, catalog, tableName)));
       return keys;
    }

    /**
     * 组装primaryKey数据
     * @param db
     * @param catalog
     * @param tableName
     * @return
     */
    private static List<PrimaryKey> getPrimaryKeys(DatabaseMetaData db, String catalog, String tableName){
        List<PrimaryKey> keys = new ArrayList<>();
        try {
            ResultSet rs = db.getPrimaryKeys(catalog, null, tableName);
            while(rs.next()) {
                PrimaryKey primaryKey = new PrimaryKey();
                primaryKey.setColName(rs.getString("COLUMN_NAME"));
                primaryKey.setPkName(rs.getString("PK_NAME"));
                primaryKey.setKeySeq(rs.getShort("KEY_SEQ"));
                keys.add(primaryKey);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keys;
    }
}
