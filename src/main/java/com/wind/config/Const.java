package com.wind.config;

import java.io.File;

/**
 * 常量字符集合
 * @author wind
 */
public interface Const {

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

    /**********************************************编码格式************************************************/

    String UTF8 = "UTF-8";

    /**
     * flt模板根路径
     */
    String FTL_JAVA = "src/main/resources/ftl/java";

    /**
     * java代码生成根路径
     */
    String FTL_DIR = "E:/work/freemarker/";

    int MAP_SIZE = 16;

    /**********************************************java************************************************/
    String SCOPE_PUBLIC = "public";

    String SCOPE_PRIVATE = "private";

    String SCOPE_PROTECTED = "protected";

    String JAVA = "java";

    String CLASS = "class";

    String ABSTRACT = "abstract";

    String INTERFACE = "interface";

    String ENUM = "enum";

    String ANNOTATION = "annotation";

    String PACKAGE = "package";

    String JAVA_LANG = "java.lang.";

    String DATE = "java.util.Date";

    String LIST = "java.util.List";

    String MAP = "java.util.Map";

    String BIGDECIMAL = "BigDecimal";

    String INT = "int";

    String STRING = "string";

    String VOID = "void";


}
