package com.wind.config;

/**
 * 包常量
 * @author wind
 */
public interface PackageConst {

    /**
     * java代码包名
     */
    String FTL_PACKAGR = "com.wind";
    /**
     * ibatis公共类包
     */
    String FTL_IBATIS_COMMON_PACKAGE = FTL_PACKAGR + ".entity.base";

    String FTL_CONTROLLER_PACKAGE = FTL_PACKAGR + ".controller";

    String FTL_DAO_BASE_PACKAGE = FTL_PACKAGR + ".dao.base";

    String FTL_DAO_PACKAGE = FTL_PACKAGR + ".dao";

    String FTL_PAGE_PACKAGE = FTL_PACKAGR + ".entity.base";

    String FTL_ENTITY_PACKAGE = FTL_PACKAGR + ".entity";

    String FTL_ENTITY_EXTEND_PACKAGE = FTL_PACKAGR + ".entity.extend";

    String FTL_ENTITY_EXAMPLE_PACKAGE = FTL_PACKAGR + ".entity.example";

    String FTL_SERVICE_PACKAGE = FTL_PACKAGR + ".service";

    String FTL_SERVICE_IMPL_PACKAGE = FTL_PACKAGR + ".service.impl";

    String FTL_SERVICE_BASE_PACKAGE = FTL_PACKAGR + ".service.base";

    String FTL_TEST_PACKAGE = FTL_PACKAGR + "dao";
}
