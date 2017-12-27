package com.wind.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.wind.dao.CarDao;
import com.wind.dao.base.BaseDaoImpl;
import com.wind.dao.callback.PsBack;
import com.wind.entity.Car;
import com.wind.util.Const;

/**
 *
 * @author wind
 */
public class ${property}Impl extends BaseDaoImpl<${property}, String> implements ${property}Dao{

    @Override
    public int insert(${property} r) {
        String sql = "insert into ${tableName} values(?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                ps.setString(1, r.getCarId());
                ps.setString(2, r.getUserId());
                ps.setString(3, r.getCarNo());
                ps.setString(4, r.getMessage());
                ps.setString(5, r.getPicture());
                ps.setTimestamp(6, new Timestamp(r.getCreateTime().getTime()));
                ps.setInt(7, r.getStatus());
            }
        });
    }

    @Override
    public int deleteById(String id) {
        String sql = "delete from ${tableName} where car_id = ?";
        return executeUpdate(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        });
    }

    @Override
    public ${property} findById(String id) {
        String sql = "select * from ${tableName} where car_id = ?";
        List<${property}> entitys = executeQuery(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        });
        if(entitys.size() == 1){
            return entitys.get(0);
        }
        return null;
    }

    @Override
    public List<${property}> findByCondition(${property} r) {
        String sql = "select * from ${tableName} where 1 = 1 ";
        String whereClause = "";

        Integer status = r.getStatus();
        if(status != null && status != -1){
        whereClause += " and status = " + status;
        }

        String userId = r.getUserId();
        if(userId != null && userId.trim().equals("")){
        whereClause += " and userId = '" + userId;
        }

        sql += whereClause;
        return executeQuery(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {

            }
            });
    }

    @Override
    public int updateByCondition(${property} r) {
        String sql = "update ${tableName} ";
        String setSql = "set ";
        String id = r.getUserId();

        Integer status = r.getStatus();
        if(status != null){
        setSql += " status = " + status + " ,";
        }

        String message = r.getMessage();
        if(message != null){
        setSql += " message = '" + message + "' ,";
        }

        String pic = r.getPicture();
        if(pic != null){
        setSql += " picture = '" + pic + "' ,";
        }

        if(id != null && setSql.contains(Const.STR_COMMA)){
            sql += setSql.substring(0, setSql.length() - 2);
            sql += " where car_id = ?";
            return super.executeUpdate(sql, new PsBack() {
                @Override
                public void call(PreparedStatement ps) throws SQLException {
                    ps.setString(1, id);
                }
            });
        }

        return -1;
    }

    @Override
    public ${property} parseTable(ResultSet rs) throws SQLException {
        ${property} entity = null;
        if(rs != null) {
            entity = new ${property}();
        }
        return entity;
    }
}