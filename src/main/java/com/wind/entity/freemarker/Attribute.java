package com.wind.entity.freemarker;


import com.wind.util.Const;

/**
 * java 类,成员属性或方法基础信息描述
 * @author wind
 */
public class Attribute {

    /**
     * 作用域
     */
    private String scope;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 注释
     */
    private String remark;

    public Attribute() {
    }

    public Attribute(String name) {
        this.name = name;
    }

    public Attribute(String name, String type) {
        this(null, name, type, Const.BLANK_STR);
    }

    public Attribute(String scope, String name, String type, String remark) {
        this.scope = scope;
        this.name = name;
        this.type = type;
        this.remark = remark;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
