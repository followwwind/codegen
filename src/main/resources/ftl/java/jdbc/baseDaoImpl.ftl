package com.wind.dao.base;

import com.wind.dao.callback.PsBack;
import com.wind.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用数据库操作方法封装
 * @author wind
 */
public abstract class BaseDaoImpl<R, PK> implements BaseDao<R, PK>{

    /**
     * 解析数据库表对应实体类
     * @param rs
     * @return
     * @throws SQLException
     */
    public abstract R parseTable(ResultSet rs) throws SQLException;

    /**
     * 拼接sql语句
     * @param r
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    public abstract String joinSql(R r, String prefix, String suffix);


    /**
     * 执行查询语句， ps不赋值回调
     * @param sql
     */
    public List<R> executeQuery(String sql){
        return executeQuery(sql, null);
    }

    /**
     * 执行查询语句， ps赋值回调
     * @param sql
     * @param psBack
     */
    public List<R> executeQuery(String sql, PsBack psBack){
        <#if (JDK_VERSION >= 8)>
        List<R> list = new ArrayList<>();
        <#else>
        List<R> list = new ArrayList<R>();
        </#if>
        Connection conn = DbUtil.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(psBack != null){
                psBack.call(ps);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(parseTable(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
        }
        return list;
    }

    /**
     * 执行查询记录条数语句， ps赋值回调
     * @param sql
     * @param psBack
     * @param
     */
    public int countQuery(String sql, PsBack psBack){
        Connection conn = DbUtil.getConnection();
        int i = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(psBack != null){
                psBack.call(ps);
            }
            ResultSet rs = ps.executeQuery();
            if(rs != null && rs.first()){
                i = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
        }
        return i;
    }


    /**
     * 执行添加， 删除， 更新等操作
     * @param sql
     * @return
     */
    public int executeUpdate(String sql) {
        Connection conn = DbUtil.getConnection();
        int i = -1;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
        }
        return i;
    }

    /**
     * 执行添加， 删除， 更新等操作
     * @param sql
     * @param psBack
     * @return
     */
    public int executeUpdate(String sql, PsBack psBack){
        Connection conn = DbUtil.getConnection();
        int i = -1;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            psBack.call(ps);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
        }
        return i;
    }
}
