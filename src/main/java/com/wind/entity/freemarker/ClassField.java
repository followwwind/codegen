package com.wind.entity.freemarker;

/**
 * 类成员属性描述
 * @author wind
 */
public class ClassField extends Attribute{

    public ClassField(String name, String type) {
        this(null, name, type, "");
    }

    public ClassField(String scope, String name, String type, String remark) {
        super(scope, name, type, remark);
    }
}
