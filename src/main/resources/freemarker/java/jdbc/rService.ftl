package com.wind.service;

import com.wind.entity.${property};
import com.wind.service.base.BaseService;
<#assign key = getKey(columns, primaryKeys[0])>
<#assign type = key.type>
/**
 * ${remarks!""} service接口
 * @author wind
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