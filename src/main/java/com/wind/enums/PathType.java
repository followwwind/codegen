package com.wind.enums;

import com.wind.config.BaseEnum;
import com.wind.config.FtlConst;

/**
 * @Title: EnvType
 * @Package com.wind.config
 * @Description: TODO
 * @author wind
 * @date 2019/2/27 15:23
 * @version V1.0
 */
public enum PathType implements BaseEnum {

    ROOT_PATH("rootPath", FtlConst.FTL_DIR_PATH),

    CONTROLLER("controller", "controller"),

    ANGULAR_TABLE("angular", "angular/table"),

    ANGULAR_JS("angular", "angular/js"),

    ENTITY_PO("po", "entity/po"),

    ENTITY_QUERY("query", "entity/query"),

    ENTITY_SEARCH("search", "entity/search"),

    ENTITY_VO("vo", "entity/vo"),

    DAO("dao", "dao"),

    SERVICE("service", "service"),

    IMPL("impl", "service/impl"),

    MAPPER("mapper", "mapper"),
    ;

    private final String key;

    private final String value;

    PathType(String key, String value) {
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
