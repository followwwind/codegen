package com.wind.config;

/**
 * @Title: EnvType
 * @Package com.wind.config
 * @Description: TODO
 * @author wind
 * @date 2019/2/27 15:23
 * @version V1.0
 */
public enum EnvType implements BaseEnum{

    /**
     * java jdk版本
     */
    JDK_VERSION("jdk_version", "8"),

    SWAGGER("swagger", "1"),

    TABLE_PATTERN("tablePattern", ""),

    REPLACE_HEAD("replaceHead", ""),

    COMMON_SQL_PACKAGE("sqlMapper", "com.wind.boot.config.persistence.annotation.SqlMapper"),

    JSON_RESULT("jsonResult", "com.wind.boot.config.message.JsonResult"),

    HTTP_CODE("httpCode", "com.wind.boot.config.message.HttpCode"),

    TARGET("target", FtlConst.FTL_DIR_PATH),
    ;

    private final String key;

    private final String value;

    EnvType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }
}
