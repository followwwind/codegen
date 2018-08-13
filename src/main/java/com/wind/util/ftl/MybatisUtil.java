package com.wind.util.ftl;

import com.wind.config.*;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.entity.ftl.MyBatis;
import com.wind.util.JsonUtil;
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
        if(FtlConst.MYBATIS_VERSION == Version.V1){
        	FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
            freeMarker.setFileDir(PathConst.FTL_ENTITY_BASE_PATH);
            freeMarker.addMap(FtlConst.FTL_PACKAGR_NAME, PackageConst.FTL_IBATIS_COMMON_PACKAGE);
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
	}

    /**
     * 生成通用dao操作类
     */
    public static void genBaseMapper(){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_DAO_BASE_PATH);
        if(FtlConst.MYBATIS_VERSION == Version.V1){
        	freeMarker.setData("mybatis/v1/baseMapper.ftl",  "BaseMapper.java");
        }else if(FtlConst.MYBATIS_VERSION == Version.V2){
        	freeMarker.setData("mybatis/v2/baseMapper.ftl",  "BaseMapper.java");
        }
        Map<String, Object> map = new HashMap<>();
        map.put(FtlConst.FTL_PACKAGR_NAME, PackageConst.FTL_DAO_BASE_PACKAGE);
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_PAGE_PACKAGE, "Page"));
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
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_DAO_PATH);
        String property = table.getProperty();
        if(flag){
            freeMarker.setMap(JsonUtil.beanToMap(table, true));
            Map<String, Object> map = new HashMap<>();
            map.put(FtlConst.FTL_PACKAGR_NAME, PackageConst.FTL_DAO_PACKAGE);
            List<String> imports = new ArrayList<>();
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_DAO_BASE_PACKAGE, "BaseMapper"));
            
            if(FtlConst.MYBATIS_VERSION == Version.V1){
            	imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE, property + "Example"));
            	freeMarker.setData("mybatis/v1/rMapper.ftl", property + "Mapper.java");
            }else if(FtlConst.MYBATIS_VERSION == Version.V2){
            	freeMarker.setData("mybatis/v2/rMapper.ftl", property + "Mapper.java");
            }
            map.put(FtlConst.FTL_IMPORT, imports);
            freeMarker.addMap(map);
            FtlUtil.genCode(freeMarker);
        }

        MyBatis myBatis = new MyBatis();
        myBatis.setNamespace(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_DAO_PACKAGE, property + "Mapper"));
        myBatis.setType(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
        myBatis.setExample(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE, property + "Example"));
        myBatis.setTable(table);
        if(FtlConst.MYBATIS_VERSION == Version.V1){
        	freeMarker.setData("mybatis/v1/mapper.ftl", property + "Mapper.xml");
        }else if(FtlConst.MYBATIS_VERSION == Version.V2){
        	freeMarker.setData("mybatis/v2/mapper.ftl", property + "Mapper.xml");
        }
        freeMarker.initMap();
        freeMarker.addMap(JsonUtil.beanToMap(myBatis, true));
        freeMarker.setFileDir(PathConst.FTL_MAPPER_PATH);
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成Service接口和实现类
     * @param table
     * @param flag
     */
    public static void genService(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_SERVICE_PATH);
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        if(flag){
            Map<String, Object> map = new HashMap<>();
            map.put(FtlConst.FTL_PACKAGR_NAME, PackageConst.FTL_SERVICE_PACKAGE);
            List<String> imports = new ArrayList<>();
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_SERVICE_BASE_PACKAGE, "BaseService"));
            map.put(FtlConst.FTL_IMPORT, imports);
            freeMarker.addMap(map);
            freeMarker.setData("rService.ftl", property + "Service.java");
            FtlUtil.genCode(freeMarker);
        }
        //生成service实现类
        freeMarker.initMap();
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        Map<String, Object> map = new HashMap<>();
        map.put(FtlConst.FTL_PACKAGR_NAME, PackageConst.FTL_SERVICE_IMPL_PACKAGE);
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_DAO_PACKAGE, property + "Mapper"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_PAGE_PACKAGE, "Page"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_SERVICE_PACKAGE, property + "Service"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_SERVICE_BASE_PACKAGE, "BaseServiceImpl"));
        
        if(FtlConst.MYBATIS_VERSION == Version.V1){
        	imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE, property + "Example"));
        	freeMarker.setData("mybatis/v1/rServiceImpl.ftl", property + "ServiceImpl.java");
        }else if(FtlConst.MYBATIS_VERSION == Version.V2){
        	freeMarker.setData("mybatis/v2/rServiceImpl.ftl", property + "ServiceImpl.java");
        }
        
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
    		FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
            freeMarker.setFileDir(PathConst.FTL_ENTITY_EXAMPLE_PATH);
            freeMarker.setMap(JsonUtil.beanToMap(table, true));
            String property = table.getProperty();
            Map<String, Object> map = new HashMap<>();
            map.put(FtlConst.FTL_PACKAGR_NAME, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE);
            List<String> imports = new ArrayList<>();
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_IBATIS_COMMON_PACKAGE, "AttrType"));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_IBATIS_COMMON_PACKAGE, "Attribute"));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_IBATIS_COMMON_PACKAGE, "BaseBuilder"));
            imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_IBATIS_COMMON_PACKAGE, "Example"));
            map.put(FtlConst.FTL_IMPORT, imports);
            freeMarker.addMap(map);
            freeMarker.setData("mybatis/v1/rExample.ftl", property + "Example.java");
            FtlUtil.genCode(freeMarker);
    	}
    }

    /**
     * 生成测试类
     * @param table
     */
    public static void genTest(Table table){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_TEST_PATH);
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        Map<String, Object> map = new HashMap<>();
        map.put(FtlConst.FTL_PACKAGR_NAME, PackageConst.FTL_TEST_PACKAGE);
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_DAO_PACKAGE, property + "Mapper"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_PAGE_PACKAGE, "Page"));
        
        if(FtlConst.MYBATIS_VERSION == Version.V1){
        	imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_EXAMPLE_PACKAGE, property + "Example"));
        	freeMarker.setData("mybatis/v1/testMapper.ftl", property + "MapperTest.java");
        }else if(FtlConst.MYBATIS_VERSION == Version.V2){
        	freeMarker.setData("mybatis/v2/testMapper.ftl", property + "MapperTest.java");
        }
        
        map.put(FtlConst.FTL_IMPORT, imports);
        freeMarker.addMap(map);
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成controller
     * @param table
     */
    public static void genController(Table table, boolean swaggerFlag){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(PathConst.FTL_CONTROLLER_PATH);
        String property = table.getProperty();
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        Map<String, Object> map = new HashMap<>();
        map.put(FtlConst.FTL_PACKAGR_NAME, PackageConst.FTL_CONTROLLER_PACKAGE);
        List<String> imports = new ArrayList<>();
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_ENTITY_PACKAGE, property));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_SERVICE_PACKAGE, property + "Service"));
        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_PAGE_PACKAGE, "Page"));
        
        if(FtlConst.MYBATIS_VERSION == Version.V1){
        	freeMarker.setData("mybatis/v1/controller.ftl", property + "Controller.java");
        }else if(FtlConst.MYBATIS_VERSION == Version.V2){
        	imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PACKAGE, "JsonResult"));
        	imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_CONSTANT_PACKAGE, "HttpCode"));
        	freeMarker.setData("mybatis/v2/controller.ftl", property + "Controller.java");
        }
        map.put(FtlConst.FTL_IMPORT, imports);
        map.put(FtlConst.FTL_SWAGGER, swaggerFlag);
        freeMarker.addMap(map);
        FtlUtil.genCode(freeMarker);
    }
}
