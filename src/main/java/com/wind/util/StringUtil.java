package com.wind.util;

import com.wind.config.Const;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * @Title: StringUtil
 * @Package com.wind.util
 * @Description: 字符串工具类
 * @author huanghy
 * @date 2018/8/16 17:43
 * @version V1.0
 */
public class StringUtil {

    /**
     * 判断字符串不为null且空字符串
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
        return str != null && "".equals(str.trim());
    }


    /**
     * 字符串分隔 StringTokenizer效率是三种分隔方法中最快的
     * @param str
     * @param sign 符号
     * @return
     */
    public static String[] split(String str, String sign){
        if(str == null){
            return new String[]{};
        }
        StringTokenizer token = new StringTokenizer(str,sign);
        String[] strArr = new String[token.countTokens()];
        int i = 0;
        while(token.hasMoreElements()){
            strArr[i] = token.nextElement().toString();
            i++;
        }
        return strArr;
    }

    /**
     * 字符串拼接
     * @param sign
     * @param arr
     * @return
     */
    public static String joinStr(String sign, String... arr){
        StringBuilder sb = new StringBuilder();
        Optional<String> optional  = Arrays.stream(arr).reduce((a, b) -> a + sign + b);
        optional.ifPresent(sb::append);
        return sb.toString();
    }

    /**
     * 将数据库列名翻译成java驼峰命名的类成员字段名
     * @param colName 数据库列名
     * @param flag 首字母小写为false， 大写为true
     * @return
     */
    public static String getCamelCase(String colName, boolean flag){
        StringBuilder sb = new StringBuilder();
        if(colName != null){
            String[] arr = StringUtil.split(colName, Const.UNDERLINE);
            for(int i = 0; i < arr.length; i++){
                String s = arr[i];
                if(i == 0){
                    sb.append(getFirst(s, flag));
                }else{
                    sb.append(getFirst(s, true));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将单词首字母变大小写
     * @param str
     * @param flag true变大写， false变小写
     * @return
     */
    public static String getFirst(String str, boolean flag){
        StringBuilder sb = new StringBuilder();
        int length = str != null ? str.length() : 0;
        if(length >= 1){
            if(flag){
                sb.append(str.substring(0, 1).toUpperCase());
            }else{
                sb.append(str.substring(0, 1).toLowerCase());
            }
            if(length > 1){
                sb.append(str.substring(1));
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {

    }

}
