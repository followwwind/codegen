package com.wind.util;

import com.wind.entity.db.Table;

import java.util.List;

/**
 * @author wind
 */
public class HikaricpTest {

    public static void printAllDb(){
        List<String> dbs = HikaricpUtil.getAllDb();
        System.out.println(dbs);
    }

    public static void printTable(){
        Table table = HikaricpUtil.getTable("user");
        System.out.println(table);
    }


    public static void main(String[] args) {
//        printAllDb();
        printTable();
    }
}
