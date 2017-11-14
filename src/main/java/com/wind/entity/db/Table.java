package com.wind.entity.db;

import java.util.List;

/**
 * 数据库表详情类
 * @author wind
 */
public class Table {

    List<Column> columns;

    public Table(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
