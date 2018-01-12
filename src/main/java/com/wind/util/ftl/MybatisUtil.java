package com.wind.util.ftl;

import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.entity.ftl.MyBatis;
import com.wind.util.Const;
import com.wind.util.JsonUtil;

/**
 * mybatis代码生成工具类
 * @author wind
 */
public class MybatisUtil {

    /**
     * 生成通用dao操作类
     */
    public static void genBaseMapper(){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_DAO + Const.FTL_BASE);
        freeMarker.setData("mybatis/baseMapper.ftl",  "BaseMapper.java");
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成jdbc dao操作模板类
     * @param table
     * @param flag true表示重新生成rMapper接口， false则否
     */
    public static void genMapper(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_DAO);
        MyBatis myBatis = new MyBatis();
        String property = table.getProperty();
        myBatis.setNamespace("com.wind.dao." + property + "Mapper");
        myBatis.setType("com.wind.entity." + property);
        myBatis.setTable(table);


        if(flag){
            freeMarker.setMap(JsonUtil.beanToMap(table, true));
            freeMarker.setData("mybatis/rMapper.ftl", property + "Mapper.java");
            FtlUtil.genCode(freeMarker);
        }

        freeMarker.setMap(JsonUtil.beanToMap(myBatis, true));
        freeMarker.setData("mybatis/mapper.ftl", property + "Mapper.xml");
        freeMarker.setFileDir(Const.FTL_DIR + Const.FTL_MAPPER);
        FtlUtil.genCode(freeMarker);
    }

    /**
     *
     * @param table
     * @param flag
     */
    public static void genService(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_SERVICE);
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        if(flag){
            freeMarker.setData("rService.ftl", property + "Service.java");
            FtlUtil.genCode(freeMarker);
        }

        freeMarker.setData("mybatis/rServiceImpl.ftl", property + "ServiceImpl.java");
        freeMarker.setFileDir(Const.FTL_DIR + Const.FTL_SERVICE + Const.FTL_IMPL);
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成测试类
     * @param table
     */
    public static void genTest(Table table){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_TEST);
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        freeMarker.setData("mybatis/testMapper.ftl", property + "MapperTest.java");
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成controller
     * @param table
     */
    public static void genController(Table table){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_CONTROLLER);
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        freeMarker.setData("mybatis/controller.ftl", property + "Controller.java");
        FtlUtil.genCode(freeMarker);
    }
}
