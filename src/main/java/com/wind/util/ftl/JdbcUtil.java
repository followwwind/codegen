package com.wind.util.ftl;

import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.util.Const;
import com.wind.util.JsonUtil;

/**
 * jdbc代码生成工具类
 * @author wind
 */
public class JdbcUtil {

    /**
     * 生成通用dao操作类
     */
    public static void genBaseDao(){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_DAO + Const.FTL_BASE);
        freeMarker.setData("jdbc/baseDao.ftl",  "BaseDao.java");
        FtlUtil.genCode(freeMarker);
        freeMarker.setData("jdbc/baseDaoImpl.ftl",  "BaseDaoImpl.java");
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成基础工具类
     */
    public static void genCommon(){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_DAO + Const.FTL_CALLBACK);
        freeMarker.setData("jdbc/psBack.ftl",  "PsBack.java");
        FtlUtil.genCode(freeMarker);

        freeMarker.setData("jdbc/dbUtil.ftl", "DbUtil.java");
        freeMarker.setFileDir(Const.FTL_DIR + Const.FTL_UTIL);
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成jdbc dao操作模板类
     * @param table
     * @param flag true表示重新生成rDao接口， false则否
     */
    public static void genDao(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_DAO);
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        if(flag){
            freeMarker.setData("jdbc/rDao.ftl", property + "Dao.java");
            FtlUtil.genCode(freeMarker);
        }

        freeMarker.setData("jdbc/rDaoImpl.ftl", property + "DaoImpl.java");
        freeMarker.setFileDir(Const.FTL_DIR + Const.FTL_DAO + Const.FTL_IMPL);
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
            freeMarker.setData("jdbc/rService.ftl", property + "Service.java");
            FtlUtil.genCode(freeMarker);
        }

        freeMarker.setData("jdbc/rServiceImpl.ftl", property + "ServiceImpl.java");
        freeMarker.setFileDir(Const.FTL_DIR + Const.FTL_SERVICE + Const.FTL_IMPL);
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成测试类
     */
    public static void genTest(Table table){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_TEST);
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        freeMarker.setData("jdbc/testDao.ftl", property + "DaoTest.java");
        FtlUtil.genCode(freeMarker);
    }
}
