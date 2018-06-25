package com.wind.ftl;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.ftl.FtlUtil;
import com.wind.util.ftl.JdbcUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wind
 */
public class JdbcTest {

    private List<Table> tables = new ArrayList<>();

    @Before
    public void init(){
        //tables.addAll(DbUtil.getTables("test"));
        tables.addAll(DbUtil.getTable("test", "demo"));
        genCommon();
    }

    private void genCommon(){
        JdbcUtil.genCommon();
        FtlUtil.genPage();
        JdbcUtil.genBaseDao();
        FtlUtil.genBaseService();
    }

    @Test
    public void genAllCode(){
        tables.forEach(table -> {
            FtlUtil.genEntity(table, true);
            JdbcUtil.genDao(table, true);
            JdbcUtil.genService(table, true);
            JdbcUtil.genTest(table);
        });
    }

}
