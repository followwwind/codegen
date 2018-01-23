package com.wind.ftl;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.ftl.FtlUtil;
import com.wind.util.ftl.JdbcUtil;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * @author wind
 */
public class JdbcTest {

    List<Table> tables = DbUtil.getTables("test");

    Table table = DbUtil.getTable("test", "user_info");

    @Before
    public void init(){

    }


    @Test
    public void genAllEntity(){
        tables.forEach(FtlUtil::genEntity);
    }


    @Test
    public void genEntity(){
        FtlUtil.genEntity(table);
    }


    @Test
    public void genBaseDao(){
        JdbcUtil.genBaseDao();
    }

    @Test
    public void genDao(){
        JdbcUtil.genDao(table, true);
    }

    @Test
    public void genAllDao(){
        tables.forEach(table -> JdbcUtil.genDao(table, true));
    }

    @Test
    public void genBaseService(){
        FtlUtil.genBaseService();
    }

    @Test
    public void genService(){
        JdbcUtil.genService(table, true);
    }

    @Test
    public void genAllService(){
        tables.forEach(table -> JdbcUtil.genService(table, true));
    }

    @Test
    public void genTest(){
        JdbcUtil.genTest(table);
    }

    @Test
    public void genAllTest(){
        tables.forEach(JdbcUtil::genTest);
    }

    @Test
    public void genPage(){
       FtlUtil.genPage();
    }

    @Test
    public void genAllCode(){
        JdbcUtil.genCommon();
        FtlUtil.genPage();
        JdbcUtil.genBaseDao();
        FtlUtil.genBaseService();
        tables.forEach(table -> {
            FtlUtil.genEntity(table);
            JdbcUtil.genDao(table, true);
            JdbcUtil.genService(table, true);
            JdbcUtil.genTest(table);
        });
    }

}
