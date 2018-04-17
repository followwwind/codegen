package com.wind.entity.ftl;

import java.util.HashMap;
import java.util.Map;

/**
 * freeMarker实体类描述
 * @author wind
 */
public class FreeMarker {
    /**
     * 模板文件目录
     */
    private String cfgDir;
    /**
     * 模板文件名
     */
    private String cfgName;
    /**
     * 数据
     */
    private Map<String, Object> map;
    /**
     * 生成文件目录
     */
    private String fileDir;
    /**
     * 生成文件名
     */
    private String fileName;

    public FreeMarker() {
    }

    public FreeMarker(String cfgDir) {
        this.cfgDir = cfgDir;
    }

    public FreeMarker(String cfgDir, String fileDir) {
        this.cfgDir = cfgDir;
        this.fileDir = fileDir;
    }

    public String getCfgDir() {
        return cfgDir;
    }

    public void setCfgDir(String cfgDir) {
        this.cfgDir = cfgDir;
    }

    public String getCfgName() {
        return cfgName;
    }

    public void setCfgName(String cfgName) {
        this.cfgName = cfgName;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public void setData(String cfgName, Map<String, Object> map, String fileName){
        this.cfgName = cfgName;
        this.map = map;
        this.fileName = fileName;
    }

    public void setData(String cfgName, String fileName){
        this.cfgName = cfgName;
        this.fileName = fileName;
    }

    public void setData(Map<String, Object> map, String fileName){
        this.map = map;
        this.fileName = fileName;
    }

    /**
     * 追加模板数据
     * @param key
     * @param obj
     */
    public void addMap(String key, Object obj){
        if(map == null){
            map = new HashMap<>();
        }
        map.put(key, obj);
    }
}
