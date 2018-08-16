package com.wind.ftl;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.FtlUtil;
import com.wind.util.MybatisUtil;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wind
 */
public class MybatisTest {

    private List<Table> tables = new ArrayList<>();

    @Before
    public void init(){
//        tables.addAll(DbUtil.getTables("child"));
    	FtlUtil.clear();
    	/*String[] arr = {"pmt"};
    	tables.addAll(DbUtil.getTables("child").stream().filter(table -> {
    		String tableName = table.getTableName();
    		return Arrays.asList(arr).stream().anyMatch(str -> tableName.contains(str));
    	}).collect(Collectors.toList()));*/
        
        tables.addAll(DbUtil.getTable("child", "pmt"));
        
    	//common();
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
        	String tableName = table.getTableName();
        	table.setTableName(tableName.replaceAll("pmt_", ""));
            MybatisUtil.genController(table, false);
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
