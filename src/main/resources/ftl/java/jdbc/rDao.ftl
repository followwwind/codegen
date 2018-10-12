package com.wind.dao;

import com.wind.dao.base.BaseDao;
import com.wind.entity.${property};
<#if primaryKeys?? && primaryKeys?size gt 0>
<#assign key = getKey(columns, primaryKeys[0])>
<#assign type = key.type>
</#if>
/**
 * ${remarks!""} dao接口
 * @author wind
 */
public interface ${property}Dao extends BaseDao<${property}, ${type!"String"}>{

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