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
        List<String> dbs = DbUtils.getAllDb();
        System.out.println(dbs);
    }

    @Test
    public void printTables(){
        List<Table> tables  = DbUtils.getTables("car-system");
        System.out.println(JsonUtils.toJson(tables));
    }

    @Test
    public void printKeys(){
        List<PrimaryKey> keys = DbUtils.getPrimaryKeys("car-system", "user_info");
        System.out.println(keys.size());
    }

    @Test
    public void printColumns(){
        List<Column> columns = DbUtils.getColumns("car-system", "user_info");
        System.out.println(JsonUtils.toJson(columns));
    }

    @Test
    public void printTable(){
        Table table = DbUtils.getTable("car-system", "user_info");
        System.out.println(JsonUtils.toJson(table));
    }

}
