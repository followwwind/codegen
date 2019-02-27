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
    	FtlUtil.clear();
    	/*String[] arr = {"pmt"};
    	tables.addAll(DbUtil.getTables("child").stream().filter(table -> {
    		String tableName = table.getTableName();
    		return Arrays.asList(arr).stream().anyMatch(str -> tableName.contains(str));
    	}).collect(Collectors.toList()));*/
        
//        tables.addAll(DbUtil.getTable("test", "pmt"));

        tables.addAll(DbUtil.getTables("test"));
    	//common();
//		tables.add(DbUtil.getTable("test", "demo"));
    }
    
    @Test
    public void genCommon() {
    	common();
    }
    
    private void common(){
        MybatisUtil.genCommon();
        MybatisUtil.genBaseMapper();
        FtlUtil.genBaseService();
    }

    @Test
    public void genAllCode(){
        long start = System.currentTimeMillis();
        System.out.println(tables.size());
        tables.forEach(table -> {
        	String property = table.getProperty();
        	table.setProperty(property.replaceAll("Pmt", ""));
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
