package com.wind.util;

import com.wind.entity.db.MyBatisTable;
import com.wind.entity.db.Table;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author wind
 */
public class HikaricpTest {

    @Test
    public void printAllDb(){
        List<String> dbs = HikaricpUtils.getAllDb();
        System.out.println(dbs);
    }

    @Test
    public void printTable(){
        Table table = new MyBatisTable();
        HikaricpUtils.setTable("book","books", table);
        System.out.println(Arrays.asList(table.getColumns()));
    }

}
