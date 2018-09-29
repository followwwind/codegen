package com.wind.config;

/**
 * 包常量
 * @author wind
 */
public interface PackageConst {

    /**
     * java代码包名
     */
	String FTL_PACKAGR = "com.wind.springboot";
	
    /**
     * ibatis公共类包
     */

    String FTL_CONTROLLER_PACKAGE = FTL_PACKAGR + ".controller";

    String FTL_DAO_PACKAGE = FTL_PACKAGR + ".dao";

    String FTL_ENTITY_PACKAGE = FTL_PACKAGR + ".entity.po";

    String FTL_ENTITY_EXTEND_PACKAGE = FTL_PACKAGR + ".entity.extend";

    String FTL_ENTITY_EXAMPLE_PACKAGE = FTL_PACKAGR + ".entity.example";

    String FTL_SERVICE_PACKAGE = FTL_PACKAGR + ".service";

    String FTL_SERVICE_IMPL_PACKAGE = FTL_PACKAGR + ".service.impl";

    String FTL_SERVICE_BASE_PACKAGE = FTL_PACKAGR + ".service.base";

    String FTL_TEST_PACKAGE = FTL_PACKAGR + ".dao";
    
    String FTL_COMMON_PACKAGE = FTL_PACKAGR + ".common";
    
    String FTL_COMMON_PERSISTENCE_PACKAGE = FTL_PACKAGR + ".common.persistence";
    
    String FTL_COMMON_CONSTANT_PACKAGE = FTL_PACKAGR + ".common.constant";
    
    String FTL_COMMON_SQL_PACKAGE = FTL_PACKAGR + ".common.annotation";
}
