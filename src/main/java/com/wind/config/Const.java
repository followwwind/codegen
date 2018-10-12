package com.wind.config;

import java.io.File;

/**
 * 常量字符集合
 * @author wind
 */
public interface Const {
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
}
