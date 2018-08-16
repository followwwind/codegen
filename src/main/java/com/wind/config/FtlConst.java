package com.wind.config;

/**
 * ftl模板常量
 * @author wind
 */
public interface FtlConst {
    /**
     * flt模板根路径
     */
    String FTL_JAVA = "src/main/resources/ftl/java";

    /**
     * java代码生成根路径
     */
    String FTL_DIR = "D:/work/freemarker/src";
    
    String FTL_SWAGGER = "swagger";

    String FTL_CONTROLLER = "controller";

    String FTL_PACKAGR_NAME = "packageName";

    String FTL_IMPORT = "imports";

    String FTL_ENTITY = "entity";

    String FTL_DAO = "dao";

    String FTL_MAPPER = "mapper";

    String FTL_SERVICE = "service";

    String FTL_BEAN = "bean";

    String FTL_IMPL = "impl";

    String FTL_BASE = "base";

    String FTL_CALLBACK = "callback";

    String FTL_EXTEND = "extend";

    String FTL_EXAMPLE = "example";

    String FTL_UTIL = "util";

    String FTL_TEST = "test";

    String FTL_HTML = "html";

    String FTL_JSP = "jsp";
    /**
     * java jdk版本
     */
    int JDK_VERSION = 8;
    
    Version MYBATIS_VERSION = Version.V2;
}
