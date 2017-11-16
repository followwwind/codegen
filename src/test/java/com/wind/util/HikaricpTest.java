package com.wind.util;

import com.wind.entity.db.MyBatisTable;
import com.wind.entity.db.Table;

import java.util.Arrays;
import java.util.List;

/**
 * @author wind
 */
public class HikaricpTest {

    public static void printAllDb(){
        List<String> dbs = HikaricpUtils.getAllDb();
        System.out.println(dbs);
    }

    public static void printTable(){
        Table table = new MyBatisTable();
        HikaricpUtils.setTable("user", table);
        System.out.println(Arrays.asList(table.getColumns()));
    }


    public static void main(String[] args) {
//        printAllDb();
        printTable();
    }
}
