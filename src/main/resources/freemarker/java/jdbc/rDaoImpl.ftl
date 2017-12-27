package com.wind.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.wind.dao.${property}Dao;
import com.wind.dao.base.BaseDaoImpl;
import com.wind.dao.callback.PsBack;
import com.wind.entity.${property};
import com.wind.util.Const;
<#assign key = getKey(columns, primaryKeys[0])>
<#assign pkName = key.columnName>
<#assign pkPro = key.property>
<#assign type = key.type>

/**
 *
 * @author wind
 */
public class ${property}Impl extends BaseDaoImpl<${property}, ${type}> implements ${property}Dao{

    @Override
    public int insert(${property} r) {
        String sql = "insert into ${tableName} values (${join(columns?size)})";
        return executeUpdate(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                <#list columns as column>
                <#if column.columnType == "TIMESTAMP">
                Date ${column.property} = r.get${column.property?cap_first}();
                ps.set${getType(column.columnType)}(${column_index + 1}, ${column.property} != null ? ${column.property}.getTime() : System.currentTimeMillis());
                <#else>
                ps.set${getType(column.columnType)}(${column_index + 1}, r.get${column.property?cap_first}());
                </#if>
                </#list>
            }
        });
    }

    @Override
    public int deleteById(String id) {
        String sql = "delete from ${tableName} where ${pkName} = ?";
        return executeUpdate(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                <#if type == "String">
                ps.setString(1, id);
                </#if>
            }
        });
    }

    @Override
    public ${property} findById(String id) {
        String sql = "select * from ${tableName} where ${pkName} = ?";
        List<${property}> entitys = executeQuery(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                <#if type == "String">
                ps.setString(1, id);
                </#if>
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

        <#list columns as column>
        ${column.type} ${column.property} = r.get${column.property?cap_first}();
        if(${column.property} != null){
        <#if column.columnType == "INT">
            whereClause += " and ${column.columnName} = " + ${column.property};
        <#elseif column.columnType == "VARCHAR">
            whereClause += " and ${column.columnName} = " + "'" + ${column.property} + "'";
        <#elseif column.columnType == "TIMESTAMP">

        </#if>
        }
        </#list>

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
        String id = r.get${pkPro?cap_first}();

        <#list columns as column>
        ${column.type} ${column.property} = r.get${column.property?cap_first}();
        if(${column.property} != null){
            <#if column.columnType == "INT">
            setSql += " ${column.columnName} = " + ${column.property} + ", ";
            <#elseif column.columnType == "VARCHAR">
            setSql += " ${column.columnName} = " + "'" + ${column.property} + "'" + ", ";
            </#if>
        }
        </#list>

        if(id != null && setSql.contains(Const.STR_COMMA)){
            sql += setSql.substring(0, setSql.length() - 2);
            sql += " where ${pkName} = ?";
            return super.executeUpdate(sql, new PsBack() {
                @Override
                public void call(PreparedStatement ps) throws SQLException {
                    <#if type == "String">
                    ps.setString(1, id);
                    </#if>
                }
            });
        }

        return -1;
    }

    @Override
    public int countByCondition(R r){
        String sql = "count (1) from ${tableName} where 1 = 1 ";
        String whereClause = "";

        <#list columns as column>
        ${column.type} ${column.property} = r.get${column.property?cap_first}();
        if(${column.property} != null){
            <#if column.columnType == "INT">
            whereClause += " and ${column.columnName} = " + ${column.property};
            <#elseif column.columnType == "VARCHAR">
            whereClause += " and ${column.columnName} = " + "'" + ${column.property} + "'";
            <#elseif column.columnType == "TIMESTAMP">

            </#if>
        }
        </#list>

        sql += whereClause;
        return executeQuery(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {

            }
        });
    }

    @Override
    public ${property} parseTable(ResultSet rs) throws SQLException {
        ${property} entity = null;
        if(rs != null) {
            entity = new ${property}();
            <#list columns as column>
            <#if column.columnType == "TIMESTAMP">
            long ${column.property} = rs.getTimestamp("${column.columnName}").getTime();
            entity.set${column.property?cap_first}(new Date(${column.property}));
            <#else>
            entity.set${column.property?cap_first}(rs.get${getType(column.columnType)}("${column.columnName}"));
            </#if>
            </#list>
        }
        return entity;
    }
}

<#function join size>
    <#local b = "">
    <#list 1..size as num>
        <#if num == size>
            <#local b += "?">
        <#else>
            <#local b += "?, ">
        </#if>
    </#list>
    <#return b>
</#function>

<#function getType columnType>
    <#local b = "">
    <#if columnType == "VARCHAR">
        <#local b += "String"/>
    <#elseif columnType == "INT">
        <#local b += "Int"/>
    <#elseif columnType == "TIMESTAMP">
        <#local b += "Timestamp"/>
    </#if>
    <#return b>
</#function>

<#function getKey columns primary>
    <#local b = {}>
    <#list columns as column>
        <#if primary.colName == column.columnName>
            <#local b = column>
        </#if>
    </#list>
    <#return b>
</#function>

