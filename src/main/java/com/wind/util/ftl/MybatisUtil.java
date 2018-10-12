package com.wind.util.ftl;

import com.wind.config.*;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.entity.ftl.MyBatis;
import com.wind.util.JsonUtil;
import com.wind.util.ReflectUtil;
import com.wind.util.StringUtil;

import java.util.*;

/**
 * mybatis代码生成工具类
 * @author wind
 */
public class MybatisUtil {
	
	/**
     * 生成基础工具类
     */
	public static void genCommon(){
        FreeMarker freeMarker = new FreeMarker(PathConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_ENTITY_BASE_PATH);
        freeMarker.addMap(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE);
        freeMarker.setData("mybatis/v1/attrType.ftl",  "AttrType.java");
        FtlUtil.genCode(freeMarker);

        freeMarker.setData("mybatis/v1/attr.ftl",  "Attribute.java");
        FtlUtil.genCode(freeMarker);

        freeMarker.setData("mybatis/v1/baseBuilder.ftl",  "BaseBuilder.java");
        FtlUtil.genCode(freeMarker);

        freeMarker.setData("mybatis/v1/condition.ftl",  "Condition.java");
        FtlUtil.genCode(freeMarker);

        freeMarker.setData("mybatis/v1/example.ftl",  "Example.java");
        FtlUtil.genCode(freeMarker);
	}

    /**
     * 生成通用dao操作类
     */
    public static void genBaseMapper(){
        FreeMarker freeMarker = new FreeMarker(PathConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_DAO_BASE_PATH);
        freeMarker.setData("mybatis/v1/baseMapper.ftl",  "BaseMapper.java");
        Map<String, Object> map = new HashMap<>(16);
        map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE);
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Page"));
        map.put(FtlConst.FTL_IMPORT, imports);
        freeMarker.addMap(map);
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成jdbc dao操作模板类
     * @param table
     * @param flag true表示重新生成rMapper接口， false则否
     */
    public static void genMapper(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(PathConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_DAO_PATH);
        String property = table.getProperty();
        if(flag){
            freeMarker.setMap(ReflectUtil.beanToMap(table, true));
            Map<String, Object> map = new HashMap<>(16);
            map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_DAO_PACKAGE);
            List<String> imports = new ArrayList<>();
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "BaseMapper"));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_SQL_PACKAGE, "SqlMapper"));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE, property + "Example"));
            freeMarker.setData("mybatis/rMapper.ftl", property + "Mapper.java");
            map.put(FtlConst.FTL_IMPORT, imports);
            freeMarker.addMap(map);
            FtlUtil.genCode(freeMarker);
        }

        MyBatis myBatis = new MyBatis();
        myBatis.setNamespace(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_DAO_PACKAGE, property + "Mapper"));
        myBatis.setType(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
        myBatis.setTable(table);
        myBatis.setExample(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE, property + "Example"));
        freeMarker.setData("mybatis/mapper.ftl", property + "Mapper.xml");
        freeMarker.initMap();
        freeMarker.addMap(ReflectUtil.beanToMap(myBatis, true));
        freeMarker.setFileDir(PathConst.FTL_MAPPER_PATH);
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成Service接口和实现类
     * @param table
     * @param flag
     */
    public static void genService(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(PathConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_SERVICE_PATH);
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        String property = table.getProperty();
        if(flag){
            Map<String, Object> map = new HashMap<>(16);
            map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_SERVICE_PACKAGE);
            List<String> imports = new ArrayList<>();
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_SERVICE_BASE_PACKAGE, "BaseService"));
            map.put(FtlConst.FTL_IMPORT, imports);
            freeMarker.addMap(map);
            freeMarker.setData("mybatis/rService.ftl", property + "Service.java");
            FtlUtil.genCode(freeMarker);
        }

        //生成service实现类
        freeMarker.initMap();
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        Map<String, Object> map = new HashMap<>(16);
        map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_SERVICE_IMPL_PACKAGE);
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_DAO_PACKAGE, property + "Mapper"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_SERVICE_PACKAGE, property + "Service"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE, property + "Example"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_SERVICE_BASE_PACKAGE, "BaseServiceImpl"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Page"));
        freeMarker.setData("mybatis/rServiceImpl.ftl", property + "ServiceImpl.java");
        
        map.put(FtlConst.FTL_IMPORT, imports);
        freeMarker.addMap(map);
        freeMarker.setFileDir(PathConst.FTL_SERVICE_IMPL_PATH);
        FtlUtil.genCode(freeMarker);
    }
    
    /**
     * 生成example
     * @param table
     */
    public static void genExample(Table table){
    	if(FtlConst.MYBATIS_VERSION == Version.V1){
    		FreeMarker freeMarker = new FreeMarker(PathConst.FTL_JAVA);
            freeMarker.setFileDir(PathConst.FTL_ENTITY_EXAMPLE_PATH);
            freeMarker.setMap(ReflectUtil.beanToMap(table, true));
            String property = table.getProperty();
            Map<String, Object> map = new HashMap<>(16);
            map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE);
            List<String> imports = new ArrayList<>();
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "AttrType"));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Attribute"));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "BaseBuilder"));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Example"));
            map.put(FtlConst.FTL_IMPORT, imports);
            freeMarker.addMap(map);
            freeMarker.setData("mybatis/rExample.ftl", property + "Example.java");
            FtlUtil.genCode(freeMarker);
    	}
    }

    /**
     * 生成测试类
     * @param table
     */
    public static void genTest(Table table){
        FreeMarker freeMarker = new FreeMarker(PathConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_TEST_PATH);
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        String property = table.getProperty();
        Map<String, Object> map = new HashMap<>(16);
        map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_TEST_PACKAGE);
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_DAO_PACKAGE, property + "Mapper"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Page"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE, property + "Example"));
        freeMarker.setData("mybatis/testMapper.ftl", property + "MapperTest.java");
        map.put(FtlConst.FTL_IMPORT, imports);
        freeMarker.addMap(map);
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成controller
     * @param table
     */
    public static void genController(Table table, boolean swaggerFlag){
        FreeMarker freeMarker = new FreeMarker(PathConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_CONTROLLER_PATH);
        String property = table.getProperty();
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        Map<String, Object> map = new HashMap<>(16);
        map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_CONTROLLER_PACKAGE);
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_SERVICE_PACKAGE, property + "Service"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Page"));
        freeMarker.setData("mybatis/controller.ftl", property + "Controller.java");
        map.put(FtlConst.FTL_IMPORT, imports);
        map.put(FtlConst.FTL_SWAGGER, swaggerFlag);
        freeMarker.addMap(map);
        FtlUtil.genCode(freeMarker);
    }
}
