package com.wind.ftl;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.ftl.FtlUtil;
import com.wind.util.ftl.MybatisUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wind
 */
public class MybatisTest {

    private List<Table> tables = new ArrayList<>();

    @Before
    public void init(){
        /*tables.addAll(DbUtil.getTables("child"));
    	String[] arr = {"hm_user", "hm_role", "hm_role_res", "hm_user_role"};
    	tables.addAll(DbUtil.getTables("child").stream().filter(table -> {
    		String tableName = table.getTableName();
    		return Arrays.asList(arr).indexOf(tableName) > -1;
    	}).collect(Collectors.toList()));*/
        
        tables.add(DbUtil.getTable("child", "fin_bill_item"));
        
    	common();
//		tables.add(DbUtil.getTable("test", "demo"));
        
		tables = tables.stream().filter(table -> table != null).collect(Collectors.toList());
    }
    
    @Test
    public void genCommon() {
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
        tables.stream().forEach(table -> {
            MybatisUtil.genController(table);
            FtlUtil.genEntity(table, false);
            MybatisUtil.genMapper(table, true);
            //MybatisUtil.genExample(table);
            MybatisUtil.genService(table, true);
            //MybatisUtil.genTest(table);
        });

        long end = System.currentTimeMillis();
        System.out.println(start + "-" + end + "..." + (end - start) + "ms");
    }
}
