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
    public void printTables(){
        List<Table> tables  = DbUtil.getTables("test");
        System.out.println(JsonUtil.toJson(tables));
    }

    @Test
    public void printKeys(){
        List<PrimaryKey> keys = DbUtil.getPrimaryKeys("test", "user_info");
        System.out.println(keys.size());
    }

    @Test
    public void printColumns(){
        List<Column> columns = DbUtil.getColumns("test", "user");
        System.out.println(JsonUtil.toJson(columns));
    }

    @Test
    public void printTable(){
        Table table = DbUtil.getTable("test", "user_info");
        System.out.println(JsonUtil.toJson(table));
    }

}
