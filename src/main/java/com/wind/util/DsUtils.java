package com.wind.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Hikaricp连接池的单例HikariDataSource
 * @author wind
 */
public enum DsUtils {
    /**
     * 单例HikariDataSource
     */
    DATASOURCE;

    private DataSource dataSource;

    DsUtils() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3307/follow");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDatasource(HikariConfig config){
        dataSource = new HikariDataSource(config);
    }
}
