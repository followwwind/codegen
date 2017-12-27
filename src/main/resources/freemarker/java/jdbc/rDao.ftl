package com.wind.dao;

import com.wind.dao.base.BaseDao;
import com.wind.entity.${property};
<#assign key = getKey(columns, primaryKeys[0])>
<#assign type = key.type>
/**
 * 通用数据库操作方法封装
 * @author wind
 */
public interface ${property}Dao extends BaseDao<${property}, ${type}>{

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