package com.wind.config;

import java.io.File;

/**
 * 常量字符集合
 * @author wind
 */
public interface Const {

    String SCOPE_PUBLIC = "public";

    String SCOPE_PRIVATE = "private";

    String SCOPE_PROTECTED = "protected";

    String CLASS = "class";

    String ABSTRACT = "abstract class";

    String INTERFACE = "interface";

    String ENUM = "enum";

    String ANNOTATION = "annotation";


    String DATE = "date";

    String LIST = "list";

    String MAP = "map";

    String INT = "int";

    String STRING = "string";

    String VOID = "void";


    String BEAN = "bean";

    String MAPPER = "mapper";


    /**
     * 空字符串
     */
    int MAP_SIZE = 16;

    /**********************************************分隔符常量************************************************/

    String POINT_STR = ".";

    String BLANK_STR = "";

    String SPACE_STR = " ";

    String SYS_SEPARATOR = File.separator;

    String FILE_SEPARATOR = "/";

    String BRACKET_LEFT = "[";

    String BRACKET_RIGHT = "]";

    String UNDERLINE = "_";

    String USER_DIR = "user.dir";

    /**********************************************日期时间常量************************************************/

    String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    String DATE_STR = "yyyy-MM-dd";

    int SECOND = 1000;

    int MINUTE = 60 * SECOND;

    int HOUR = 60 * MINUTE;

    int DAY = 24 * HOUR;

    /**********************************************编码格式************************************************/

    String UTF8 = "UTF-8";


    /**********************************************正则表达式************************************************/
    String NUMBER = "^[0-9]*$";

    String FLOAT = "^\\d+\\.\\d+$";


    String FTL_JAVA = "src/main/resources/ftl/java";

    String FTL_DIR = "E:/work/freemarker/src";

    String FTL_CONTROLLER = "/controller";

    String FTL_ENTITY = "/entity";

    String FTL_DAO = "/dao";

    String FTL_MAPPER = "/mapper";

    String FTL_SERVICE = "/service";

    String FTL_IMPL = "/impl";

    String FTL_BASE = "/base";

    String FTL_CALLBACK = "/callback";
    
    String FTL_EXTEND = "/extend";
    
    String FTL_EXAMPLE = "/example";

    String FTL_UTIL = "/util";

    String FTL_TEST = "/test";
    
    String FTL_HTML = "/html";
    
    String FTL_JSP = "/jsp";

    int JDK_VERSION = 7;

}
