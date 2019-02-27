package com.wind.util;

import com.wind.config.Const;
import com.wind.config.EnvType;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Title: EnvUtil
 * @Package com.wind.config
 * @Description: 初始化环境变量
 * @author wind
 * @date 2019/2/27 15:12
 * @version V1.0
 */
public class EnvUtil {

    private EnvUtil(){

    }

    private static class SingletonHolder {
        private static final Map<String, String> INSTANCE = new HashMap<>(16);
    }

    /**
     * 获取
     * @return
     */
    private static Map<String, String> getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取生成文件路径
     * @param type
     * @return
     */
    public static String getPath(EnvType type){
        return get(EnvType.ROOT_PATH) + Const.FILE_SEPARATOR + type.getKey();
    }

    /**
     * 获取生成文件路径
     * @param type
     * @return
     */
    public static String getPath(EnvType type, EnvType... arr){
        String path = get(EnvType.ROOT_PATH) + Const.FILE_SEPARATOR + type.getKey();
        if(arr != null){
            path += Const.FILE_SEPARATOR;
            path += Stream.of(arr).map(EnvType::getKey).reduce((a, b) -> a + Const.FILE_SEPARATOR + b).orElse("");
        }
        return path;
    }

    /**
     * 获取包名
     * @param type
     * @return
     */
    public static String getPackage(EnvType type){
        return get(EnvType.ROOT_PACKAGE) + Const.POINT_STR + type.getKey();
    }

    /**
     * 获取包名
     * @param type
     * @return
     */
    public static String getPackage(EnvType type, EnvType... arr){
        String path = get(EnvType.ROOT_PATH) + Const.POINT_STR + type.getKey();
        if(arr != null){
            path += Const.POINT_STR;
            path += Stream.of(arr).map(EnvType::getKey).reduce((a, b) -> a + Const.POINT_STR + b).orElse("");
        }
        return path;
    }

    /**
     * 获取环境变量
     * @param type
     * @return
     */
    public static String get(EnvType type){
        String value = get(type.getKey());
        return StringUtil.isNotEmpty(value) ? value : type.getValue();
    }

    /**
     * 获取
     * @param key
     * @return
     */
    public static String get(String key){
        return getInstance().get(key);
    }

    /**
     * 设置
     * @param key
     * @param value
     */
    public static void set(String key, String value){
        getInstance().put(key, value);
    }
}
