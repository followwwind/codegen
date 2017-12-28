package com.wind.entity.clazz;

import java.util.List;

/**
 * 类成员方法描述
 * @author wind
 */
public class ClassMethod extends Attribute{
    /**
     * 返回结果
     */
    private String result;

    /**
     * 参数
     */
    private List<Attribute> args;

    /**
     * 方法体
     */
    private String body;


    public ClassMethod(String name, String type) {
        super(name, type);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Attribute> getArgs() {
        return args;
    }

    public void setArgs(List<Attribute> args) {
        this.args = args;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
