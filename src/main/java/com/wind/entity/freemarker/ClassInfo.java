package com.wind.entity.freemarker;

import com.wind.entity.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * freemarker 类模板描述
 * @author wind
 */
public class ClassInfo {
    /**
     * 作用域
     */
    private String scope;

    /**
     * 包名
     */
    private String packageName;
    /**
     * 类注释
     */
    private String classRemark;
    /**
     * 类名
     */
    private String className;

    /**
     * 类修饰类型，比如class,interface
     */
    private String classType;

    /**
     * import语句
     */
    private List<String> imports;
    /**
     * 字段
     */
    private List<Attribute> attrs;


    public ClassInfo(String packageName, String className, List<Attribute> attrs) {
        this(Const.SCOPE_PUBLIC, packageName, Const.NULL_STR, className, Const.CLASS, attrs);
    }



    public ClassInfo(String scope, String packageName, String classRemark, String className, String classType, List<Attribute> attrs) {
        this.scope = scope;
        this.packageName = packageName;
        this.classRemark = classRemark;
        this.className = className;
        this.classType = classType;
        this.attrs = attrs;
        this.imports = initImports(attrs);
    }

    private List<String> initImports(List<Attribute> attrs){
        List<String> list = new ArrayList<>();
        if(attrs != null){
            attrs.forEach(attr -> {
                String type = attr.getType() != null ? attr.getType().toLowerCase() : Const.NULL_STR;
                if(type.contains(Const.LIST)){
                    list.add("java.util.List");
                }

                if(type.contains(Const.DATE)){
                    list.add("java.util.Date");
                }

                if(type.contains(Const.MAP)){
                    list.add("java.util.Map");
                }
            });
        }
        return list;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassRemark() {
        return classRemark;
    }

    public void setClassRemark(String classRemark) {
        this.classRemark = classRemark;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public List<Attribute> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attribute> attrs) {
        this.attrs = attrs;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
