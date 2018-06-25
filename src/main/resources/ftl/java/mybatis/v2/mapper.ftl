<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${namespace}" >
    <resultMap id="BaseResultMap" type="${type}" >
    <#assign lBracket = "{"/>
    <#assign dlBracket = "${"/>
    <#assign columns = table.columns/>
    <#assign primaryKeys = table.primaryKeys/>
    <#assign tableName = table.tableName/>
    <#assign pkName = "">
    <#assign pkType = "">
    <#assign pkPro = "">
    <#assign pkJType = "">
    <#list columns as column>
        <#if contains(primaryKeys, column.columnName) == true>
        <#assign pkName = column.columnName>
        <#assign pkType = column.columnType>
        <#assign pkPro = column.property>
        <#assign pkJType = column.type>
        <id column="${column.columnName}" property="${column.property}" jdbcType="${replace(column.columnType)}" />
        <#else>
        <result column="${column.columnName}" property="${column.property}" jdbcType="${replace(column.columnType)}" />
        </#if>
    </#list>
    </resultMap>

    <#--<sql id="Base_Column_List" >
        ${join(0, ",", 0)}
    </sql>-->

    <sql id="Column_List" >
        ${join(1, ",", 0)}
    </sql>

    <sql id="Column_Selective_List" >
        ${join(2, ",", 1)}
    </sql>

    <sql id="Column_Selective_And_List" >
        ${join(5, "and", 0)}
    </sql>

    <sql id="Column_Assign_List" >
        ${join(3, ",", 0)}
    </sql>
    
    <sql id="InsertBatch_List" >
        ${join(4, ",", 0)}
    </sql>

    <insert id="insert" parameterType="${type}" >
        insert into ${tableName} (
        <include refid="Column_List" />
        ) values (
        <include refid="Column_Assign_List" />
        )
    </insert>
    
    <insert id="insertBatch" >
        insert into ${tableName} (
        <include refid="Column_List" />
        ) values 
        <foreach collection="list" item="item" separator=",">
			(
			<include refid="InsertBatch_List" />
			)
		</foreach>
    </insert>

    <delete id="delete" parameterType="${type}" >
        delete from ${tableName} where 1 = 1
        <include refid="Column_Selective_And_List" />
    </delete>
    
    <select id="findEntity" resultMap="BaseResultMap" parameterType="${pkJType}">
        select
        <include refid="Column_List" />
        from ${tableName}
        where 1 = 1
        <if test="_parameter != null">
        	and ${pkName!""} = #${lBracket}${pkPro},jdbcType=${replace(pkType)}}
        </if>
    </select>

    <select id="findList" resultMap="BaseResultMap" parameterType="${type}">
        select
        <include refid="Column_List" />
        from ${tableName}
        where 1 = 1
        <include refid="Column_Selective_And_List" />
    </select>

    <update id="update" parameterType="${type}" >
        update ${tableName}
        <set>
            <include refid="Column_Selective_List" />
        </set>
        <if test="${pkPro} != null">
        	where ${pkName!""} = #${lBracket}${pkPro},jdbcType=${replace(pkType)}}
        </if>
    </update>

    <select id="count" resultType="java.lang.Integer" parameterType="${type}" >
        select count(1) from ${tableName}
        where 1 = 1
        <include refid="Column_Selective_And_List" />
    </select>
</mapper>

<#function contains primaryKeys colName>
    <#local b = false>
    <#list primaryKeys![] as primaryKey>
        <#if primaryKey.colName = colName>
            <#local b = true>
            <#break>
        </#if>
    </#list>
    <#return b>
</#function>

<#function replace columnType>
    <#local b = columnType?replace(" UNSIGNED", "")>
    <#if columnType == "INT">
        <#local b = "INTEGER">
    <#elseif columnType == "TEXT" || columnType == "MEDIUMTEXT" || columnType == "LONGTEXT">
        <#local b = "LONGVARCHAR">
    <#elseif columnType == "DATETIME">
        <#local b = "TIMESTAMP">
    <#elseif columnType == "TINYBLOB">
        <#local b = "BINARY">
    <#elseif columnType == "BLOB" || columnType == "MEDIUMBLOB" || columnType == "LONGBLOB">
        <#local b = "LONGVARBINARY">
    </#if>
    <#return b>
</#function>

<#function join type sign flag>
<#-- 声明局部变量 -->
    <#local str = "">
    <#if flag == 1>
        <#local str += "<trim prefix=\"\" suffix=\"\" suffixOverrides=\"" + sign + "\" prefixOverrides=\"" + sign + "\">\n\t\t\t">
    </#if>
    <#list columns as column>
        <#local s = "">
        <#if type == 0>
            <#local s = column.columnName>
            <#if column_index < (columns?size - 1)>
                <#local s += sign>
            </#if>
            <#if column_index != 0 && column_index % 7 == 0>
                <#local s += "\n\t\t\t">
            </#if>
        <#elseif type == 1>
            <#local s = column.columnName>
            <#if column_index < (columns?size - 1)>
                <#local s += sign>
            </#if>
            <#if column_index != 0 && column_index % 7 == 0>
                <#local s += "\n\t\t\t">
            </#if>
        <#elseif type == 2>
            <#local s = "<if test=\"" + column.property + "!= null\" >\n\t\t\t">
            <#if flag == 1><#local s += "\t"></#if>
            <#local s += sign + " " + column.columnName + " = #" + lBracket + column.property
            + ",jdbcType=" + replace(column.columnType) +"}\n\t\t">
            <#if flag == 1><#local s += "\t"></#if>
            <#local s += "</if>\n\t\t">
            <#if flag == 1 && column_index < (columns?size - 1)><#local s += "\t"></#if>
        <#elseif type == 3>
            <#local s += "#" + lBracket + column.property
            + ",jdbcType=" + replace(column.columnType) +"}">
            <#if column_index < (columns?size - 1)>
                <#local s += sign>
            </#if>
            <#if column_index != 0 && column_index % 4 == 0>
                <#local s += "\n\t\t">
            </#if>
        <#elseif type == 4>
            <#local s += "#" + lBracket + "item." + column.property
            + ",jdbcType=" + replace(column.columnType) +"}">
            <#if column_index < (columns?size - 1)>
                <#local s += sign>
            </#if>
            <#if column_index != 0 && column_index % 4 == 0>
                <#local s += "\n\t\t">
            </#if>
        <#elseif type == 5>
        	<#if column.type == "String">
        	<#local s = "<if test=\"" + column.property + "!= null and " + column.property + "!=''\" >\n\t\t\t">
            <#else>
            <#local s = "<if test=\"" + column.property + "!= null\" >\n\t\t\t">
        	</#if>
        	<#if flag == 1><#local s += "\t"></#if>
            <#local s += sign + " " + column.columnName + " = #" + lBracket + column.property
            + ",jdbcType=" + replace(column.columnType) +"}\n\t\t">
            <#if flag == 1><#local s += "\t"></#if>
            <#local s += "</if>\n\t\t">
            <#if flag == 1 && column_index < (columns?size - 1)><#local s += "\t"></#if>
        </#if>
        <#local str += s>
    </#list>
    <#if flag == 1>
        <#local str += "</trim>">
    </#if>
    <#return str>
</#function>