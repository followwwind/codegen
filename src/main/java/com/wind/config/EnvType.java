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

    TARGET("target", FtlConst.FTL_DIR_PATH),

    COMMON_SQL_PACKAGE("SqlMapper", "com.wind.boot.config.persistence.annotation.SqlMapper"),

    JSON_RESULT("JsonResult", "com.wind.boot.config.message.JsonResult"),

    PERSISTENCE_PO("PersistencePO", "com.wind.boot.config.persistence"),

    BASE_PO("BasePO", "com.wind.boot.config.persistence.BasePO"),

    BASE_VO("BaseVO", "com.wind.boot.config.persistence.BaseVO"),

    BASE_QUERY("BaseQuery", "com.wind.boot.config.persistence.BaseQuery"),

    PAGE_QUERY("PageQuery", "com.wind.boot.config.persistence.PageQuery"),

    HTTP_CODE("HttpCode", "com.wind.boot.config.message.HttpCode"),

    BEAN_UTIL("BeanUtil", "com.wind.boot.util.BeanUtil"),

    PAGE("Page", "com.wind.boot.config.persistence.Page"),
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
