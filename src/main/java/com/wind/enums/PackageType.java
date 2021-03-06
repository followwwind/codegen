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
public enum PackageType implements BaseEnum {

    ROOT_PACKAGE("rootPackage", FtlConst.FTL_PACKAGE),

    CONTROLLER("controller", "controller"),

    ENTITY_PO("po", "entity.po"),

    ENTITY_QUERY("query", "entity.query"),

    ENTITY_SEARCH("search", "entity.search"),

    ENTITY_VO("vo", "entity.vo"),

    DAO("dao", "dao"),

    SERVICE("service", "service"),

    IMPL("impl", "service.impl"),


    ;

    private final String key;

    private final String value;

    PackageType(String key, String value) {
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
