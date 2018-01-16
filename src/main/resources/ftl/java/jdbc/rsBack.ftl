package com.wind.dao.callback;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 查询回调
 * @author wind
 */
<#if (JDK_VERSION >= 8)>
@FunctionalInterface
</#if>
public interface RsBack {

    /**
     * 回调方法， 遍历游标
     * @param rs
     * @throws SQLException
     */
    void call(ResultSet rs) throws SQLException;
}