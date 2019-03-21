package com.wind.entity.ibatis;

import com.wind.entity.db.Table;

/**
 * MyBatis对应的table实体类
 * @author wind
 */
public class MyBatis{
    /**
     * mybatis对应命名空间
     */
    private String namespace;
    /**
     * mybatis对应实体类全名
     */
    private String type;


    private String listParam;

    private String listReturn;

    /**
     * 表数据
     */
    private Table table;

    public MyBatis() {
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getListParam() {
        return listParam;
    }

    public void setListParam(String listParam) {
        this.listParam = listParam;
    }

    public String getListReturn() {
        return listReturn;
    }

    public void setListReturn(String listReturn) {
        this.listReturn = listReturn;
    }
}
