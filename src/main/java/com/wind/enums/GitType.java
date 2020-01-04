package com.wind.enums;

import java.util.stream.Stream;

public enum GitType {

    /**
     * ssm
     */
    SSM("ssm", "https://gitee.com/lovecici/ssm-system"),

    /**
     * spring boot
     */
    BOOT("boot", "")
    ;

    private String name;

    private String url;

    GitType(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public boolean equalName(String name){
        return this.name.equals(name);
    }

    /**
     * 获取git类型
     * @param name
     * @return
     */
    public GitType getByName(String name){
        return Stream.of(GitType.values()).filter(v -> v.equalName(name)).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
