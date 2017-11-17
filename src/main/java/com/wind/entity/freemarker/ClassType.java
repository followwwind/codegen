package com.wind.entity.freemarker;

/**
 * 类型
 * @author wind
 */
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
    SERVICE("service"),

    /**
     * 类成员属性
     */
    FIELD("field"),

    /**
     * 类成员方法
     */
    METHOD("method"),

    /**
     * 参数
     */
    ARG("arg"),

    /**
     * 接口
     */
    INTERFACE("interface"),

    /**
     * 类
     */
    CLASS("class"),
    ;

    private String name;

    ClassType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
