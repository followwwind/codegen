package com.wind.util;

import com.wind.config.Const;

import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * @author wind
 */
public class RegexUtil {

    /**
     * 判断是否为数字
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        Pattern pattern = Pattern.compile(Const.NUMBER);
        return pattern.matcher(value).matches();
    }

    /**
     * 判断是否为浮点数
     * @param value
     * @return
     */
    public static boolean isFloat(String value) {
        Pattern pattern = Pattern.compile(Const.FLOAT);
        return pattern.matcher(value).matches();
    }
}
