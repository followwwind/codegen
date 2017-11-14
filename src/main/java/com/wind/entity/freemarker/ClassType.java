package com.wind.entity.freemarker;

public enum ClassType {
    /**
     * dao层
     */
    DAO("dao"),

    /**
     * bean
     */
    BEAN("bean"),

    /**
     * service 业务层
     */
    SERVICE("service");

    private String name;

    ClassType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
