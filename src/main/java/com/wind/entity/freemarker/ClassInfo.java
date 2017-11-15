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
     * 成员属性
     */
    private List<Attribute> attrs;
    /**
     * 成员方法
     */
    private List<Method> methods;

    private String type;

    public ClassInfo() {

    }

    public ClassInfo(String packageName, String className, List<Attribute> attrs) {
        this(Const.SCOPE_PRIVATE, packageName, "", className, Const.CLASS, null, attrs, null, Const.BEAN);
    }

    public ClassInfo(String scope, String packageName, String classRemark, String className, String classType, List<String> imports, List<Attribute> attrs, List<Method> methods, String type) {
        this.scope = scope;
        this.packageName = packageName;
        this.classRemark = classRemark;
        this.className = className;
        this.classType = classType;
        this.attrs = attrs;
        this.methods = methods;
        if(Const.BEAN.equals(type) && imports == null){
            this.imports = initImports(attrs);
        }else{
            this.imports = imports;
        }

        this.type = type;
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

    /**
     * 添加import导入语句
     * @param importStr
     */
    public void addImport(String importStr){
        if(imports == null){
            imports = new ArrayList<>();
        }

        imports.add(importStr);
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

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
