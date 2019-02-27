package com.wind.config;

/**
 * @Title: EnvType
 * @Package com.wind.config
 * @Description: TODO
 * @author wind
 * @date 2019/2/27 15:23
 * @version V1.0
 */
public enum EnvType {

    CONTROLLER("controller", ""),

    PACKAGE_NAME("packageName", ""),

    IMPORT("imports", ""),

    ENTITY("entity", ""),

    DAO("dao", ""),

    MAPPER("mapper", ""),

    SERVICE("service", ""),

    BEAN("bean", ""),

    IMPL("impl", ""),

    BASE("base", ""),

    CALLBACK("callback", ""),

    EXTEND("extend", ""),

    EXAMPLE("example", ""),

    UTIL("util", ""),

    TEST("test", ""),

    HTML("html", ""),

    JSP("jsp", ""),

    /**
     * java jdk版本
     */
    JDK_VERSION("jdk_version", "8"),

    SWAGGER("swagger", "1"),

    ROOT_PACKAGE("rootPackage", FtlConst.FTL_PACKAGE),

    ROOT_PATH("rootPath", FtlConst.FTL_DIR_PATH),

    TABLE_PATTERN("tablePattern", ""),

    REPLACE_HEAD("replaceHead", ""),
    ;

    private final String key;

    private final String value;

    EnvType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
