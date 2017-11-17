package com.wind.entity.freemarker;

/**
 * 类成员属性描述
 * @author wind
 */
public class Field extends Attribute{

    public Field(String name, String type) {
        this(null, name, type, "");
    }

    public Field(String scope, String name, String type, String remark) {
        super(scope, name, type, remark);
    }
}
