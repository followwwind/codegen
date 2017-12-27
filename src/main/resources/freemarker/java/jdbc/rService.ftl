package com.wind.service;

import com.wind.entity.${property};
import com.wind.service.base.BaseService;
<#assign key = getKey(columns, primaryKeys[0])>
<#assign type = key.type>
/**
 * 数据库dao通用接口
 * @author wind
 * @param <${property}> 数据库表关联的实体类
 * @param <PK> 主键类型
 */
public interface ${property}Service extends BaseService<${property}, ${type}>{

}

<#function getKey columns primary>
 <#local b = {}>
 <#list columns as column>
  <#if primary.colName == column.columnName>
   <#local b = column>
  </#if>
 </#list>
 <#return b>
</#function>