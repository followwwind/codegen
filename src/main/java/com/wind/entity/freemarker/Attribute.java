package com.wind.entity.freemarker;

import com.wind.entity.Const;

/**
 * java 类成员属性或方法信息描述
 * @author wind
 */
public class Attribute {

    /**
     * 字段包名
     */
    private String packageName;

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
    /**
     * 作用类型， 比如描述成员属性， 成员方法， 参数
     */
    private ClassType classType;

    public Attribute(String name, String type, ClassType classType) {
        this((classType == ClassType.FIELD ? Const.SCOPE_PRIVATE : Const.NULL_STR), name, type, Const.NULL_STR, classType);
    }

    public Attribute(String scope, String name, String type, String remark, ClassType classType) {
        this.scope = scope;
        this.name = name;
        this.type = type;
        this.remark = remark;
        this.classType = classType;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
