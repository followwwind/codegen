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
    <#list columns as column>
        <#assign index=column_index/>
        ${tableName?substring(0,1)}.${column.columnName}<#if index%7 == 0 && index != (columns?size - 1)>,<#elseif index < (columns?size - 1)>,</#if>
    </#list>
    </sql>

    <sql id="Column_List" >
    <#list columns as column>
        ${column.columnName},
    </#list>
    </sql>

    <sql id="Column_Selective_List" >
    <#list columns as column>
        <if test="${column.property} != null" >
        , ${column.columnName} = #${lBracket}${column.property},jdbcType=${column.columnType}}
        </if>
    </#list>
    </sql>

    <sql id="Column_Selective_And_List" >
    <#list columns as column>
        <if test="${column.property} != null" >
            and ${column.columnName} = #${lBracket}${column.property},jdbcType=${column.columnType}}
        </if>
    </#list>
    </sql>

    <sql id="Column_Assign_List" >
    <#list columns as column>
        ${column.columnName} = #${lBracket}${column.property},jdbcType=${column.columnType}}
    </#list>
    </sql>

    <insert id="insert" parameterType="${type}" >
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <include refid="Column_List" />
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
            <include refid="Column_Assign_List" />
        </trim>
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
        <trim prefix="and (" suffix=")" suffixOverrides="and">
            <include refid="Column_Selective_And_List" />
        </trim>
    </select>

    <delete id="deleteByPrimaryKey" parameterType="java.lang.String" >
        delete from ${tableName}
        where ${pkName} = #${lBracket}${pkName},jdbcType=${pkType}}
    </delete>

    <update id="updateByPrimaryKeySelective" parameterType="${type}" >
        update ${tableName}
        <trim prefix="set" suffixOverrides=",">
            <include refid="Column_Selective_List" />
        </trim>
        where ${pkName} = #${lBracket}${pkName},jdbcType=${pkType}}
    </update>

    <select id="countByCondition" resultMap="java.lang.Integer" parameterType="${type}" >
        select count(1)
        from ${tableName}
        where 1 = 1
        <trim prefix="and (" suffix=")" suffixOverrides="and">
            <include refid="Column_Selective_And_List" />
        </trim>
    </select>
</mapper>