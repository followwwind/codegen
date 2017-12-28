package com.wind.entity.clazz;

import com.wind.util.Const;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * freemarker 类模板描述
 * @author wind
 */
public class ClassInfo extends Attribute{

    /**
     * 包名
     */
    private String packageName;

    /**
     * import语句
     */
    private Set<String> imports;
    /**
     * 成员属性
     */
    private List<ClassField> fields;
    /**
     * 成员方法
     */
    private List<ClassMethod> methods;

    /**
     * 继承
     */
    private ClassInfo extend;

    /**
     * 实现
     */
    private List<ClassInfo> impls;

    /**
     * 类类型，比如bean，dao，service等
     */
    private ClassType classType;

    public ClassInfo(String className) {
        super(className);
    }

    public ClassInfo(String name, ClassType classType, String type) {
        super(name, type);
        this.classType = classType;
    }

    /**
     * 初始化import导入语句
     */
    public void initImports(){
        if(fields != null || methods != null){
            this.imports = this.imports != null ? this.imports : new HashSet<>();
            addImport(fields);
            addImport(methods);
        }
    }

    private void addImport(List<? extends Attribute> attrs){
        if(attrs != null){
            attrs.forEach(attr -> {
                if(attr instanceof ClassMethod){
                    ClassMethod m = (ClassMethod) attr;
                    addImport(m.getArgs());
                }
                String type = attr.getType() != null ? attr.getType().toLowerCase() : Const.BLANK_STR;
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

    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }

    public List<ClassField> getFields() {
        return fields;
    }

    public void setFields(List<ClassField> fields) {
        this.fields = fields;
    }

    public List<ClassMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<ClassMethod> methods) {
        this.methods = methods;
    }

    public ClassInfo getExtend() {
        return extend;
    }

    public void setExtend(ClassInfo extend) {
        this.extend = extend;
    }

    public List<ClassInfo> getImpls() {
        return impls;
    }

    public void setImpls(List<ClassInfo> impls) {
        this.impls = impls;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }
}
