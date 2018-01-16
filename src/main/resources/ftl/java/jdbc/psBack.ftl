package com.wind.dao.callback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 更新回调
 * @author wind
 */
@FunctionalInterface
public interface PsBack {
    /**
     * 回调方法， 设置参数
     * @param ps
     * @throws SQLException
     */
    void call(PreparedStatement ps) throws SQLException;
}