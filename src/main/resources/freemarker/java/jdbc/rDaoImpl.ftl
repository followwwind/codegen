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
import com.wind.entity.base.Page;
<#assign key = getKey(columns, primaryKeys[0])>
<#assign pkName = key.columnName>
<#assign pkPro = key.property>
<#assign type = key.type>

/**
 * ${remarks!""} dao接口实现
 * @author wind
 */
public class ${property}DaoImpl extends BaseDaoImpl<${property}, ${type}> implements ${property}Dao{

    @Override
    public int insert(${property} r) {
        String sql = "insert into ${tableName} values (${join(columns?size)})";
        if(r == null){
            return -1;
        }
        <#if (JDK_VERSION >= 8)>
        return executeUpdate(sql, ps -> {
        <#list columns as column>
            <#assign columnType = getType(column.columnType)>
            <#if columnType == "Timestamp">
            Date ${column.property} = r.get${column.property?cap_first}();
            ps.set${columnType}(${column_index + 1}, new Timestamp(${column.property} != null ? ${column.property}.getTime() : System.currentTimeMillis()));
            <#elseif columnType == "String">
            ps.set${columnType}(${column_index + 1}, r.get${column.property?cap_first}());
            <#elseif columnType == "Double">
            ps.set${columnType}(${column_index + 1}, r.get${column.property?cap_first}() != null ? r.get${column.property?cap_first}() : 0.0);
            <#elseif columnType == "Int">
            ps.set${columnType}(${column_index + 1}, r.get${column.property?cap_first}() != null ? r.get${column.property?cap_first}() : 0);
            </#if>
        </#list>
        });
        <#else>
        return executeUpdate(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                <#list columns as column>
                    <#assign columnType = getType(column.columnType)>
                    <#if columnType == "Timestamp">
                    Date ${column.property} = r.get${column.property?cap_first}();
                    ps.set${columnType}(${column_index + 1}, new Timestamp(${column.property} != null ? ${column.property}.getTime() : System.currentTimeMillis()));
                    <#elseif columnType == "String">
                    ps.set${columnType}(${column_index + 1}, r.get${column.property?cap_first}());
                    <#elseif columnType == "Double">
                    ps.set${columnType}(${column_index + 1}, r.get${column.property?cap_first}() != null ? r.get${column.property?cap_first}() : 0.0);
                    <#elseif columnType == "Int">
                    ps.set${columnType}(${column_index + 1}, r.get${column.property?cap_first}() != null ? r.get${column.property?cap_first}() : 0);
                    </#if>
                </#list>
            }
        });
        </#if>
    }

    @Override
    public int deleteById(String id) {
        String sql = "delete from ${tableName} where ${pkName} = ?";
        <#if (JDK_VERSION >= 8)>
        return executeUpdate(sql, ps -> ps.setString(1, id));
        <#else>
        return executeUpdate(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                <#if type == "String">
                ps.setString(1, id);
                </#if>
            }
        });
        </#if>
    }

    @Override
    public ${property} findById(String id) {
        String sql = "select * from ${tableName} where ${pkName} = ?";
        <#if (JDK_VERSION >= 8)>
        List<${property}> entitys = executeQuery(sql, ps -> ps.setString(1, id));
        <#else>
        List<${property}> entitys = executeQuery(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {
                <#if type == "String">
                ps.setString(1, id);
                </#if>
            }
        });
        </#if>
        if(entitys.size() == 1){
            return entitys.get(0);
        }
        return null;
    }

    @Override
    public List<${property}> findByCondition(${property} r) {
        String sql = "select * from ${tableName} where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        <#if (JDK_VERSION >= 8)>
        return executeQuery(sql, ps -> {});
        <#else>
        return executeQuery(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {

            }
        });
        </#if>
    }

    @Override
    public void findPageList(${property} r, Page page){
        String sql = "select count(1) from ${tableName} where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        <#if (JDK_VERSION >= 8)>
        int totalCount =  countQuery(sql, ps -> {});
        <#else>
        int totalCount = countQuery(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {

            }
        });
        page.setTotalCount(totalCount);
        </#if>
        sql += " limit " + page.getStartRow() + ", " + page.getLineNumber();
        <#if (JDK_VERSION >= 8)>
        List<${property}> entitys =  executeQuery(sql.replace("count(1)", "*"), ps -> {});
        <#else>
        List<${property}> entitys =  executeQuery(sql.replace("count(1)", "*"), new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {

            }
        });
        </#if>
        page.setResult(entitys);
    }

    @Override
    public int updateByCondition(${property} r) {
        String sql = "update ${tableName} ";
        if(r != null){
            return -1;
        }
        String setSql = "set " + joinSql(r, "", ", ");
        String id = r.get${pkPro?cap_first}();
        if(id != null && setSql.contains(", ")){
            sql += setSql.substring(0, setSql.length() - 2);
            sql += " where ${pkName} = ?";
            <#if (JDK_VERSION >= 8)>
            return executeUpdate(sql, ps -> ps.setString(1, id));
            <#else>
            return executeUpdate(sql, new PsBack() {
                @Override
                public void call(PreparedStatement ps) throws SQLException {
                    <#if type == "String">
                    ps.setString(1, id);
                    </#if>
                }
            });
            </#if>
        }
        return -1;
    }

    @Override
    public int countByCondition(${property} r){
        String sql = "select count(1) from ${tableName} where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        <#if (JDK_VERSION >= 8)>
        return countQuery(sql, ps -> {});
        <#else>
        return countQuery(sql, new PsBack() {
            @Override
            public void call(PreparedStatement ps) throws SQLException {

            }
        });
        </#if>
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

    @Override
    public String joinSql(${property} r, String prefix, String suffix){
        String joinSql = "";
        <#list columns as column>
        <#if column.columnName != pkName>
        ${column.type} ${column.property} = r.get${column.property?cap_first}();
        if(${column.property} != null){
            <#if column.columnType == "INT">
            joinSql += prefix + "${column.columnName} = " + ${column.property} + suffix;
            <#elseif column.columnType == "VARCHAR">
            joinSql += prefix + "${column.columnName} = " + "'" + ${column.property} + "'" + suffix;
            <#elseif column.columnType == "TIMESTAMP">

            </#if>
        }
        </#if>
        </#list>
        return joinSql;
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
    <#elseif columnType == "TEXT">
        <#local b += "String"/>
    <#elseif columnType == "DOUBLE">
        <#local b += "Double"/>
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

