package com.wind.util;

import com.wind.entity.db.Column;
import com.wind.entity.db.PrimaryKey;
import com.wind.entity.db.Table;
import org.junit.Test;

import java.util.List;

/**
 * @author wind
 */
public class DbTest {

    @Test
    public void printAllDb(){
        List<String> dbs = DbUtil.getAllDb();
        System.out.println(dbs);
    }

    @Test
    public void printTable2(){
        DbUtil.getTable("");
    }

    @Test
    public void printTables(){
        List<Table> tables  = DbUtil.getTables("test");
        System.out.println(JsonUtil.toJson(tables));
    }

    @Test
    public void printKeys(){
        List<PrimaryKey> keys = DbUtil.getPrimaryKeys("test", "user");
        System.out.println(keys.size());
        System.out.println(JsonUtil.toJson(keys));
    }

    @Test
    public void printColumns(){
        List<Column> columns = DbUtil.getColumns("test", "user");
        System.out.println(JsonUtil.toJson(columns));
    }

    @Test
    public void printTable(){
        List<Table> tables = DbUtil.getTable("test", "user");
        System.out.println(JsonUtil.toJson(tables));
    }

}
