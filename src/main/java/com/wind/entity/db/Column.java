package com.wind.entity.db;

/**
 * 数据库列详情类
 * @author wind
 */
public class Column {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 类型
     */
    private String columnType;
    /**
     * 占用字节
     */
    private int columnSize;
    /**
     * 是否为空
     */
    private int nullable;

    private int digits;
    /**
     * 描述
     */
    private String remarks;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getNullable() {
        return nullable;
    }

    public void setNullable(int nullable) {
        this.nullable = nullable;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
