package com.wind.util;

import com.wind.entity.DataPool;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Hikaricp连接池的单例HikariDataSource
 * @author wind
 */
public enum DsUtils {
    /**
     * 默认单例HikariDataSource
     */
    DATASOURCE(DataPool.HikariCP);

    private DataSource dataSource;

    private DataPool dataPool;

    DsUtils(DataPool dataPool) {
        this.dataPool = dataPool;
        init();
    }

    /**
     * 切换连接池或者修改数据库连接
     * @param dataPool
     */
    public void setDataPool(DataPool dataPool){
        this.dataPool = dataPool;
        init();
    }


    /**
     * 获取数据库连接池
     * @return
     */
    public DataSource getDataSource() {
        return  dataSource;
    }

    /**
     * 初始化
     */
    private void init(){
        switch (dataPool){
            case HikariCP:
                HikariConfig config = new HikariConfig("src/main/resources/jdbc.properties");
                dataSource = new HikariDataSource(config);
                break;
            default:break;
        }
    }


}
