package com.wind.util;

import java.util.List;

/**
 * @author wind
 */
public class HikaricpTest {

    public static void printAllDb(){
        List<String> dbs = HikaricpUtil.getAllDb();
        System.out.println(dbs);
    }


    public static void main(String[] args) {
        printAllDb();
    }
}
