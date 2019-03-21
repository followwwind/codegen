package com.wind.util;

import com.wind.config.*;

import java.util.HashMap;
import java.util.Map;

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
     * 获取import引入
     * @return
     */
    public static String getImport(PackageType type, String name){
        return getPackage(type) + Const.POINT_STR + name;
    }

    /**
     * 获取生成文件路径
     * @param type
     * @return
     */
    public static String getPath(PathType type){
        String path = get(type);
        if(StringUtil.isNotEmpty(path)){
            return path;
        }
        return getValOrDefault(PathType.ROOT_PATH) + Const.FILE_SEPARATOR + type.getValue();
    }


    /**
     * 获取包名
     * @param type
     * @return
     */
    public static String getPackage(PackageType type){
        String packageName = get(type);
        if(StringUtil.isNotEmpty(packageName)){
            return packageName;
        }
        return getValOrDefault(PackageType.ROOT_PACKAGE) + Const.POINT_STR + type.getValue();
    }

    public static String getSearch(String name){
        return EnvUtil.getPackage(PackageType.ENTITY_QUERY) + Const.POINT_STR + name + FtlConst.SEARCH;
    }

    public static String getQuery(String name){
        return EnvUtil.getPackage(PackageType.ENTITY_QUERY) + Const.POINT_STR + name + FtlConst.Q;
    }

    public static String getVO(String name){
        return EnvUtil.getPackage(PackageType.ENTITY_VO) + Const.POINT_STR + name + FtlConst.VO;
    }

    /**
     * 获取环境变量
     * @param type
     * @return
     */
    public static String get(BaseEnum type){
        return get(type.getKey());
    }

    /**
     * 获取环境变量
     * @param type
     * @return
     */
    public static String getValOrDefault(BaseEnum type){
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
