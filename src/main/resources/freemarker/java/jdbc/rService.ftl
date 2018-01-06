package com.wind.service;

import com.wind.entity.${property};
import com.wind.service.base.BaseService;
<#if primaryKeys?? && primaryKeys?size gt 0>
  <#assign key = getKey(columns, primaryKeys[0])>
  <#assign type = key.type>
</#if>
/**
 * ${remarks!""} service接口
 * @author wind
 */
public interface ${property}Service extends BaseService<${property}, ${type!"String"}>{

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