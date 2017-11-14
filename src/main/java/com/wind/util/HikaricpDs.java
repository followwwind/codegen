package com.wind.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Hikaricp连接池的单例HikariDataSource
 * @author wind
 */
public enum  HikaricpDs {
    /**
     * 单例HikariDataSource
     */
    DATASOURCE;

    private HikariDataSource dataSource;

    HikaricpDs() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3307/follow");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource =  new HikariDataSource(config);
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
