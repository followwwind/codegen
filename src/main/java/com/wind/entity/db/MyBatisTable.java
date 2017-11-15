package com.wind.entity.db;

/**
 * MyBatis对应的table实体类
 * @author wind
 */
public class MyBatisTable extends Table{
    /**
     * mybatis对应命名空间
     */
    private String namespace;
    /**
     * mybatis对应实体类全名
     */
    private String type;

    public MyBatisTable() {
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
}
