package com.wind.entity;

import java.io.File;

/**
 * java类字符串常量
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
    String NULL_STR = "";

    int MAP_SIZE = 16;

    String UTF8 = "utf-8";

    String UNDERLINE = "_";

    /**********************************************分隔符常量************************************************/

    String POINT_STR = ".";

    String SPACE_STR = " ";

    String SYS_SEPARATOR = File.separator;

    String FILE_SEPARATOR = "/";

    String BRACKET_LEFT = "[";

    String BRACKET_RIGHT = "]";

    String USER_DIR = "user.dir";
}
