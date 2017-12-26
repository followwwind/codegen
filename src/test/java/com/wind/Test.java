package com.wind;

import com.wind.util.DbUtils;
import com.zaxxer.hikari.HikariConfig;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void testList(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");

        System.out.println(list.toArray(new String[list.size()])[0]);
    }


    public static void testDatasource(){

        DbUtils.getAllDb();
        System.out.println("-------------------------------------------------");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://192.168.1.35:3307/business-v2");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        DbUtils.getAllDb();

    }


    public static void main(String[] args) {
        /*testDatasource();*/
        String aa = "1231\"";
        System.out.println(aa.replaceAll("\"", "'"));
    }
}
