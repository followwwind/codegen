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
    <#list columns as column>
        <#if contains(primaryKeys, column.columnName) == true>
        <#assign pkName = column.columnName>
        <#assign pkType = column.columnType>
        <#assign pkPro = column.property>
        <id column="${column.columnName}" property="${column.property}" jdbcType="${replace(column.columnType)}" />
        <#else>
        <result column="${column.columnName}" property="${column.property}" jdbcType="${replace(column.columnType)}" />
        </#if>
    </#list>
    </resultMap>
    
    <sql id="Example_Where_Clause" >
    	<where>
  			<foreach collection="conditions" item="condition" separator="or" >
	        	<if test="condition.valid" >
	          		<trim prefix="(" suffix=")" prefixOverrides="and" >
		            	<foreach collection="condition.attrs" item="attr" >
		              		<choose >
		                		<when test="attr.type == 'no'" >
		                  			and ${dlBracket}attr.key}
		                		</when>
		                		<when test="attr.type == 'single'" >
		                  			and ${dlBracket}attr.key} #${lBracket}attr.value}
		                		</when>
		                		<when test="attr.type == 'between'" >
		                  			and ${dlBracket}attr.key}
		                  			<foreach collection="attr.value" item="listItem" open="" close="" separator="and" >
			                    		#${lBracket}listItem}
			                  		</foreach>
		                		</when>
		                		<when test="attr.type == 'in'" >
		                  			and ${dlBracket}attr.key}
			                  		<foreach collection="attr.value" item="listItem" open="(" close=")" separator="," >
			                    		#${lBracket}listItem}
			                  		</foreach>
		                		</when>
		              		</choose>
		            	</foreach>
		          	</trim>
		        </if>
	      	</foreach>
	   </where>
	</sql>

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
        ${join(2, "and", 0)}
    </sql>

    <sql id="Column_Assign_List" >
        ${join(3, ",", 0)}
    </sql>

    <insert id="insert" parameterType="${type}" >
        insert into ${tableName} (
        <include refid="Column_List" />
        ) values (
        <include refid="Column_Assign_List" />
        )
    </insert>

    <delete id="deleteById" parameterType="java.lang.String" >
        delete from ${tableName}
        where ${pkName!""} = #${lBracket}${pkPro},jdbcType=${pkType}}
    </delete>

    <delete id="deleteByCondition" parameterType="${type}" >
        delete from ${tableName} where 1 = 1
        <include refid="Column_Selective_And_List" />
    </delete>

    <select id="findEntitys" resultMap="BaseResultMap" parameterType="${type}" >
        select
        <include refid="Column_List" />
        from ${tableName}
        where 1 = 1
        <include refid="Column_Selective_And_List" />
    </select>

    <select id="findByCondition" resultMap="BaseResultMap" parameterType="${example}" >
        select
        <include refid="Column_List" />
        from ${tableName}
        <if test="_parameter != null" >
	      <include refid="Example_Where_Clause" />
	    </if>
	    <if test="groupClause != null" >
	      group by ${dlBracket}groupClause}
	    </if>
	    <if test="orderClause != null" >
	      order by ${dlBracket}orderClause}
	    </if>
	    <if test="limit != null" >
	      limit ${dlBracket}limit}
	    </if>
    </select>

    <update id="updateByCondition" parameterType="${type}" >
        update ${tableName}
        <set>
            <include refid="Column_Selective_List" />
        </set>
        <if test="${pkPro} != null">
        	where ${pkName!""} = #${lBracket}${pkPro},jdbcType=${pkType}}
        </if>
    </update>

    <select id="countByCondition" resultType="java.lang.Integer" parameterType="${example}" >
        select count(1) from ${tableName}
        <if test="_parameter != null" >
	      <include refid="Example_Where_Clause" />
	    </if>
	    <if test="groupClause != null" >
	      group by ${dlBracket}groupClause}
	    </if>
	    <if test="orderClause != null" >
	      order by ${dlBracket}orderClause}
	    </if>
	    <if test="limit != null" >
	      limit ${dlBracket}limit}
	    </if>
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
    <#local b = columnType>
    <#if columnType == "INT">
        <#local b = "INTEGER">
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
        </#if>
        <#local str += s>
    </#list>
    <#if flag == 1>
        <#local str += "</trim>">
    </#if>
    <#return str>
</#function>