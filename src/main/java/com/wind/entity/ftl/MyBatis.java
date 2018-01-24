package com.wind.entity.ftl;

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
    
    /**
     * mybatis对应实体类查询条件
     */
    private String example;

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

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}
}
