package com.wind.freemarker;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.ftl.FtlUtil;
import com.wind.util.ftl.MybatisUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author wind
 */
public class MybatisTest {

    List<Table> tables = DbUtil.getTables("book-system");

    Table table = DbUtil.getTable("car-system", "user_info");

    @Test
    public void genMapper(){
        MybatisUtil.genMapper(table, true);
    }

    @Test
    public void genMappers(){
        tables.forEach(t -> MybatisUtil.genMapper(t, true));
    }

    @Test
    public void genBaseMapper(){
        MybatisUtil.genBaseMapper();
    }

    @Test
    public void genBaseService(){
        FtlUtil.genBaseService();
    }

    @Test
    public void genService(){
        MybatisUtil.genService(table, true);
    }

    @Test
    public void genAllService(){
        tables.forEach(table -> MybatisUtil.genService(table, true));
    }

    @Test
    public void genTest(){
        MybatisUtil.genTest(table);
    }

    @Test
    public void genAllTest(){
        tables.forEach(MybatisUtil::genTest);
    }

    @Test
    public void genController(){
        MybatisUtil.genController(table);
    }

    @Test
    public void genAllController(){
        tables.forEach(MybatisUtil::genController);
    }

    @Test
    public void genPage(){
        FtlUtil.genPage();
    }

    @Test
    public void genAllCode(){
        FtlUtil.genPage();
        MybatisUtil.genBaseMapper();
        FtlUtil.genBaseService();
        tables.forEach(table -> {
            MybatisUtil.genController(table);
            FtlUtil.genEntity(table);
            MybatisUtil.genMapper(table, true);
            MybatisUtil.genService(table, true);
            MybatisUtil.genTest(table);
        });
    }
}
