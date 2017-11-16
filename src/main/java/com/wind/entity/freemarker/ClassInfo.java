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
    /**
     * 类类型， bean类型等
     */
    private String type;

    public ClassInfo(String className) {
        this.className = className;
    }

    public ClassInfo(String packageName, String className, List<Attribute> attrs) {
        this(Const.NULL_STR, packageName, "", className, Const.CLASS, null, attrs, null, Const.BEAN);
    }

    public ClassInfo(String scope, String packageName, String classRemark, String className, String classType, List<String> imports, List<Attribute> attrs, List<Method> methods, String type) {
        this.scope = scope;
        this.packageName = packageName;
        this.classRemark = classRemark;
        this.className = className;
        this.classType = classType;
        this.attrs = attrs;
        this.methods = methods;
        this.type = type;
        initImports();
    }

    /**
     * 初始化import导入语句
     */
    public void initImports(){
        if(attrs != null || methods != null){
            this.imports = this.imports != null ? this.imports : new ArrayList<>();
            addImport(attrs);
            addImport(methods);
        }
    }

    private void addImport(List<? extends Attribute> attrs){
        if(attrs != null){
            attrs.forEach(attr -> {
                if(attr instanceof Method){
                    Method m = (Method) attr;
                    addImport(m.getArgs());
                }
                String type = attr.getType() != null ? attr.getType().toLowerCase() : Const.NULL_STR;
                if(type.contains(Const.LIST)){
                    this.imports.add("java.util.List");
                }

                if(type.contains(Const.DATE)){
                    this.imports.add("java.util.Date");
                }

                if(type.contains(Const.MAP)){
                    this.imports.add("java.util.Map");
                }
            });
        }
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
