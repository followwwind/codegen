package com.wind;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.EnvUtil;
import com.wind.util.PropUtil;
import com.wind.util.ftl.AngularJsUtil;
import com.wind.util.ftl.FtlUtil;
import com.wind.util.ftl.MybatisUtil;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wind
 */
public class CodeTest {

    private List<Table> tables = new ArrayList<>();

    @Before
    public void init(){
    	FtlUtil.clear();

        Properties config = PropUtil.getProp(DbUtil.class.getResourceAsStream("/config.properties"));
        if(config != null){
            config.forEach((key, value) -> EnvUtil.set(String.valueOf(key), String.valueOf(value)));
        }
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
    }

    @Test
    public void genAllCode(){
        long start = System.currentTimeMillis();
        System.out.println(tables.size());
        tables.forEach(table -> {
        	String property = table.getProperty();
        	table.setProperty(property.replaceAll("Pmt", ""));
            MybatisUtil.genController(table, false);
            FtlUtil.genEntity(table);
            MybatisUtil.genMapper(table, true);
//            MybatisUtil.genExample(table);
            MybatisUtil.genService(table, true);
            MybatisUtil.genTest(table);
            AngularJsUtil.genTable(table);
            AngularJsUtil.genJs(table);
        });

        long end = System.currentTimeMillis();
        System.out.println(start + "-" + end + "..." + (end - start) + "ms");
    }
}
