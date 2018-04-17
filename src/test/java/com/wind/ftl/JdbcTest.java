package com.wind.ftl;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.ftl.FtlUtil;
import com.wind.util.ftl.JdbcUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wind
 */
public class JdbcTest {

    private List<Table> tables = new ArrayList<>();

    @Before
    public void init(){
        //tables.addAll(DbUtil.getTables("test"));
        tables.add(DbUtil.getTable("test", "demo"));
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
