package com.wind.ftl;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.ftl.FtlUtil;
import com.wind.util.ftl.MybatisUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wind
 */
public class MybatisTest {

    private List<Table> tables = new ArrayList<>();

    @Before
    public void init(){
        tables.addAll(DbUtil.getTables("test"));
        //tables.add(DbUtil.getTable("test", "demo"));
        
        common();
    }
    
    private void common(){
    	FtlUtil.genPage();
        MybatisUtil.genCommon();
        MybatisUtil.genBaseMapper();
        FtlUtil.genBaseService();
    }

    @Test
    public void genAllCode(){
        long start = System.currentTimeMillis();
        System.out.println(tables.size());
        tables.forEach(table -> {
            MybatisUtil.genController(table);
            FtlUtil.genEntity(table, true);
            MybatisUtil.genMapper(table, true);
            MybatisUtil.genExample(table);
            MybatisUtil.genService(table, true);
            MybatisUtil.genTest(table);
        });

        long end = System.currentTimeMillis();
        System.out.println(start + "-" + end + "..." + (end - start) + "ms");
    }
}
