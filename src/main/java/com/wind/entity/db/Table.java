package com.wind.entity.db;

import java.util.List;

/**
 * 数据库表详情类
 * @author wind
 */
public abstract class Table {
    /**
     * 数据库实例名
     */
    private String tableCat;
    /**
     * 表名
     */
    private String tableName;

    /**
     * 表对应驼峰类名
     */
    private String property;

    /**
     * 表别名
     */
    private String alias;

    /**
     * 主键名称
     */
    private String pkName;
    /**
     * 所有列的信息
     */
    private List<Column> columns;

    public Table() {
    }

    public Table(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
