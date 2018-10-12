package com.wind.config;

/**
 * 包常量
 * @author wind
 */
public interface PackageConst {

    /**
     * java代码包名
     */
	String FTL_PACKAGE = "com.wind";
	
    /**
     * ibatis公共类包
     */

    String FTL_CONTROLLER_PACKAGE = FTL_PACKAGE + ".controller";

    String FTL_DAO_PACKAGE = FTL_PACKAGE + ".dao";

    String FTL_ENTITY_PACKAGE = FTL_PACKAGE + ".entity.po";

    String FTL_ENTITY_EXTEND_PACKAGE = FTL_PACKAGE + ".entity.extend";

    String FTL_ENTITY_EXAMPLE_PACKAGE = FTL_PACKAGE + ".entity.example";

    String FTL_SERVICE_PACKAGE = FTL_PACKAGE + ".service";

    String FTL_SERVICE_IMPL_PACKAGE = FTL_PACKAGE + ".service.impl";

    String FTL_SERVICE_BASE_PACKAGE = FTL_PACKAGE + ".service.base";

    String FTL_TEST_PACKAGE = FTL_PACKAGE + ".dao";

    String FTL_COMMON_PACKAGE = FTL_PACKAGE + ".common";
    
    String FTL_COMMON_PERSISTENCE_PACKAGE = FTL_PACKAGE + ".common.persistence";
    
    String FTL_COMMON_CONSTANT_PACKAGE = FTL_PACKAGE + ".common.constant";
    
    String FTL_COMMON_SQL_PACKAGE = FTL_PACKAGE + ".common.annotation";
}
