package com.wind.util.ftl;

import com.wind.config.*;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.entity.ftl.MyBatis;
import com.wind.util.EnvUtil;
import com.wind.util.JsonUtil;
import com.wind.util.ReflectUtil;
import com.wind.util.StringUtil;

import java.util.*;

/**
 * @Title: MybatisUtil
 * @Package com.wind.util.ftl
 * @Description: mybatis代码生成工具类
 * @author wind
 * @date 2019/2/27 15:44
 * @version V1.0
 */
public class MybatisUtil {

    private MybatisUtil(){

    }
	
	/**
     * 生成基础工具类
     */
	public static void genCommon(){

	}

    /**
     * 生成通用dao操作类
     */
    public static void genBaseMapper(){
//        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
//        freeMarker.setFileDir(PathConst.FTL_DAO_BASE_PATH);
//        freeMarker.setData("mybatis/baseMapper.ftl",  "BaseMapper.java");
//        Map<String, Object> map = new HashMap<>(16);
//        map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE);
//        List<String> imports = new ArrayList<>();
//        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Page"));
//        map.put(FtlConst.FTL_IMPORT, imports);
//        freeMarker.addMap(map);
//        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成jdbc dao操作模板类
     * @param table
     * @param flag true表示重新生成rMapper接口， false则否
     */
    public static void genMapper(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(EnvUtil.getPath(PathType.DAO));
        String property = table.getProperty();
        if(flag){
            freeMarker.setMap(ReflectUtil.beanToMap(table, true));
            Map<String, Object> map = new HashMap<>(16);
            map.put(FtlConst.PACKAGE_NAME, EnvUtil.getPackage(PackageType.DAO));
            List<String> imports = new ArrayList<>();
            imports.add(EnvUtil.getImport(PackageType.ENTITY, property));
            imports.add(EnvUtil.getValOrDefault(EnvType.COMMON_SQL_PACKAGE));
            freeMarker.setData("mybatis/rMapper.ftl", property + "Mapper.java");
            map.put(FtlConst.FTL_IMPORT, imports);
            freeMarker.addMap(map);
            FtlUtil.genCode(freeMarker);
        }
        MyBatis myBatis = new MyBatis();
        myBatis.setNamespace(StringUtil.joinStr(Const.POINT_STR, EnvUtil.getPackage(PackageType.DAO), property + "Mapper"));
        myBatis.setType(StringUtil.joinStr(Const.POINT_STR, EnvUtil.getPackage(PackageType.ENTITY), property));
        myBatis.setTable(table);
        freeMarker.setData("mybatis/mapper.ftl", property + "Mapper.xml");
        freeMarker.initMap();
        freeMarker.addMap(ReflectUtil.beanToMap(myBatis, true));
        freeMarker.setFileDir(EnvUtil.getPath(PathType.MAPPER));
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成Service接口和实现类
     * @param table
     * @param flag
     */
    public static void genService(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(EnvUtil.getPath(PathType.SERVICE));
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        String property = table.getProperty();
        if(flag){
            Map<String, Object> map = new HashMap<>(16);
            map.put(FtlConst.PACKAGE_NAME, EnvUtil.getPackage(PackageType.SERVICE));
            List<String> imports = new ArrayList<>();
//            imports.add(StringUtil.joinStr(Const.POINT_STR, EnvUtil.getPackage(PackageType.ENTITY), property));
            imports.add(EnvUtil.getValOrDefault(EnvType.JSON_RESULT));
            map.put(FtlConst.FTL_IMPORT, imports);
            freeMarker.addMap(map);
            freeMarker.setData("mybatis/rService.ftl", property + "Service.java");
            FtlUtil.genCode(freeMarker);
        }
        //生成service实现类
        freeMarker.initMap();
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        Map<String, Object> map = new HashMap<>(16);
        map.put(FtlConst.PACKAGE_NAME, EnvUtil.getPackage(PackageType.IMPL));
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, EnvUtil.getPackage(PackageType.ENTITY), property));
        imports.add(StringUtil.joinStr(Const.POINT_STR, EnvUtil.getPackage(PackageType.DAO), property + "Mapper"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, EnvUtil.getPackage(PackageType.SERVICE), property + "Service"));
        imports.add(EnvUtil.getValOrDefault(EnvType.JSON_RESULT));
        imports.add(EnvUtil.getValOrDefault(EnvType.HTTP_CODE));
        freeMarker.setData("mybatis/rServiceImpl.ftl", property + "ServiceImpl.java");
        map.put(FtlConst.FTL_IMPORT, imports);
        freeMarker.addMap(map);
        freeMarker.setFileDir(EnvUtil.getPath(PathType.IMPL));
        FtlUtil.genCode(freeMarker);
    }


    /**
     * 生成测试类
     * @param table
     */
    public static void genTest(Table table){
//        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
//        freeMarker.setFileDir(PathConst.FTL_TEST_PATH);
//        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
//        String property = table.getProperty();
//        Map<String, Object> map = new HashMap<>(16);
//        map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_TEST_PACKAGE);
//        List<String> imports = new ArrayList<>();
//        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
//        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_DAO_PACKAGE, property + "Mapper"));
//        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Page"));
//        freeMarker.setData("mybatis/testMapper.ftl", property + "MapperTest.java");
//        map.put(FtlConst.FTL_IMPORT, imports);
//        freeMarker.addMap(map);
//        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成controller
     * @param table
     */
    public static void genController(Table table, boolean swaggerFlag){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(EnvUtil.getPath(PathType.CONTROLLER));
        String property = table.getProperty();
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        Map<String, Object> map = new HashMap<>(16);
        map.put(FtlConst.PACKAGE_NAME, EnvUtil.getPackage(PackageType.CONTROLLER));
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, EnvUtil.getPackage(PackageType.SERVICE), property + "Service"));
        imports.add(EnvUtil.getValOrDefault(EnvType.JSON_RESULT));
//        imports.add(EnvUtil.getValOrDefault(EnvType.HTTP_CODE));
        freeMarker.setData("mybatis/controller.ftl", property + "Controller.java");
        map.put(FtlConst.FTL_IMPORT, imports);
        map.put(EnvType.SWAGGER.getKey(), swaggerFlag);
        freeMarker.addMap(map);
        FtlUtil.genCode(freeMarker);
    }
}
