package com.wind.entity.clazz;

import com.wind.config.Const;
import com.wind.config.JavaConst;
import com.wind.util.StringUtil;

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
    private List<ClassInfo> implList;

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
        
        if(extend != null){
        	this.imports.add(extend.getPackageName() + Const.POINT_STR + extend.getName());
        }
    }
    
    /**
     * 添加import
     * @param importStr
     */
    public void addImport(String importStr) {
    	this.imports = this.imports != null ? this.imports : new HashSet<>();
    	this.imports.add(importStr);
    }

    private void addImport(List<? extends Attribute> attrs){
        if(attrs != null){
            attrs.forEach(attr -> {
                if(attr instanceof ClassMethod){
                    ClassMethod m = (ClassMethod) attr;
                    addImport(m.getArgs());
                }
                
                String type = attr.getType();
                String[] strs = StringUtil.split(type, Const.POINT_STR);
                int length = strs.length;             
                if(!type.contains(JavaConst.JAVA_LANG) && length > 1) {
                	this.imports.add(type);
                }
                
                if(length >= 1) {
                	attr.setType(strs[length - 1]);        
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

    public List<ClassInfo> getImplList() {
        return implList;
    }

    public void setImplList(List<ClassInfo> implList) {
        this.implList = implList;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }
}
