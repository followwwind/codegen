package com.wind.util;

import com.wind.config.Const;
import com.wind.config.FtlConst;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 字符串工具类
 * @author wind
 */
public class StringUtil {

    private static AtomicLong next = new AtomicLong(1);

    /**
     * 判断字符串是否为null或空字符串
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str != null && "".equals(str.trim());
    }

    /**
     * 获取32位的UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成一个13位数的唯一id
     * @return
     */
    public static long getPKNum(){
        return next.getAndIncrement() + System.currentTimeMillis();
    }

    /**
     * url编码
     * @param str
     * @return
     */
    public static String encodeUrl(String str){
        String s = null;
        try {
            s = URLEncoder.encode(str, Const.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return s;
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
     * 由于String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患，现调整如下：
     *
     * @param src
     * 要截取的字符串
     * @param startIdx
     * 开始坐标（包括该坐标)
     * @param endIdx
     * 截止坐标（包括该坐标）
     * @return
     */
    public static String substring(String src, int startIdx, int endIdx) {
        byte[] b = src.getBytes();
        StringBuilder sb = new StringBuilder();
        for (int i = startIdx; i <= endIdx; i++) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    /**
     * url解码
     * @param str
     * @return
     */
    public static String decodeUrl(String str){
        String s = null;
        try {
            s = URLDecoder.decode(str, Const.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 字符串拼接
     * @param sign
     * @param strs
     * @return
     */
    public static String joinStr(String sign, String... strs){
        StringBuilder sb = new StringBuilder();
        Optional<String> optional  = Arrays.stream(strs).reduce((a, b) -> a + sign + b);
        if(optional.isPresent()){
            sb.append(optional.get());
        }
        return sb.toString();
    }

    /**
     * 将数据库列名翻译成java驼峰命名的类成员字段名
     * @param colName 数据库列名
     * @param flag 首字母小写为false， 大写为true
     * @return
     */
    public static String getCamelCase(String colName, boolean flag){
        String str = colName;
        StringBuilder sb = new StringBuilder();
        if(colName != null){
            String[] strs = StringUtil.split(colName, Const.UNDERLINE);
            for(int i = 0; i < strs.length; i++){
                String s = strs[i];
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
        /*String orginStr = null;
        String[] strArr = split(orginStr, ".");
        System.out.println(Arrays.asList(strArr));*/

        String[] strs = split("java.util.Date", Const.POINT_STR);
        System.out.println(strs.length);

        //System.out.printf(StringUtil.joinStr(Const.POINT_STR, FtlConst.FTL_PACKAGR, FtlConst.FTL_CONTROLLER));
    }

}
