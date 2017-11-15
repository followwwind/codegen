<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${namespace}" >
    <resultMap id="BaseResultMap" type="${type}" >
    <#assign lBracket="{"/>
    <#list columns as column>
        <#if pkName == column.columnName>
        <#assign pkType=column.columnType/>
    <id column="${column.columnName}" property="${column.property}" jdbcType="${column.columnType}" />
        <#else>
        <result column="${column.columnName}" property="${column.property}" jdbcType="${column.columnType}" />
        </#if>
    </#list>
    </resultMap>


    <sql id="Base_Column_List" >
        ${join(0, ",")}
    </sql>

    <sql id="Column_List" >
        ${join(1, ",")}
    </sql>

    <sql id="Column_Selective_List" >
        ${join(2, ",")}
    </sql>

    <sql id="Column_Selective_And_List" >
        ${join(2, "and")}
    </sql>

    <sql id="Column_Assign_List" >
        ${join(3, ",")}
    </sql>

    <insert id="insert" parameterType="${type}" >
        insert into ${tableName} (
        <include refid="Column_List" />
        ) values (
        <include refid="Column_Assign_List" />
        )
    </insert>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.String" >
        select
        <include refid="Base_Column_List" />
        from ${tableName} ${alias}
        where ${pkName} = #${lBracket}${pkName},jdbcType=${pkType}}
    </select>

    <select id="selectByCondition" resultMap="BaseResultMap" parameterType="${type}" >
        select
        <include refid="Base_Column_List" />
        from ${tableName} ${alias}
        where 1 = 1
        <include refid="Column_Selective_And_List" />
    </select>

    <delete id="deleteByPrimaryKey" parameterType="java.lang.String" >
        delete from ${tableName}
        where ${pkName} = #${lBracket}${pkName},jdbcType=${pkType}}
    </delete>

    <update id="updateByPrimaryKeySelective" parameterType="${type}" >
        update ${tableName}
        <set>
            <include refid="Column_Selective_List" />
        </set>
        where ${pkName} = #${lBracket}${pkName},jdbcType=${pkType}}
    </update>

    <select id="countByCondition" resultMap="java.lang.Integer" parameterType="${type}" >
        select count(1)
        from ${tableName}
        where 1 = 1
        <include refid="Column_Selective_And_List" />
    </select>
</mapper>

<#function join type sign>
<#-- 声明局部变量 -->
    <#local str = "">
    <#local str += "<trim prefix=\"\" suffix=\"\" suffixOverrides=\"" + sign + "\">\n\t\t\t">
    <#list columns as column>
        <#local s = "">
        <#if type == 0>
            <#local s = alias + "." + column.columnName + sign + " ">
        <#elseif type == 1>
            <#local s = column.columnName + sign + " ">
        <#elseif type == 2>
            <#local s = "<if test=\"" + column.property + "!= null\" >\n\t\t\t\t">
            <#local s += sign + " " + column.columnName + " = #" + lBracket + column.property
            + ",jdbcType=" + column.columnType +"}\n\t\t\t">
            <#local s += "</if>\n\t\t\t">
        <#elseif type == 3>
            <#local s = sign + " " + column.columnName + " = #" + lBracket + column.property
            + ",jdbcType=" + column.columnType +"}\n\t\t\t">
        </#if>
        <#local str += s>
    </#list>
    <#local str += "\n\t\t</trim>">
    <#return str>
</#function>