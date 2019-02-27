package ${packageName!"com.wind.dao"};

<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
<#if primaryKeys?? && primaryKeys?size gt 0>
 <#assign key = getKey(columns, primaryKeys[0])>
 <#assign type = key.type>
</#if>

/**
 * @Title: ${property}Mapper
 * @Package ${packageName!"com.wind.dao"}
 * @Description: ${remarks!""}mapper
 * @author wind
 * @date ${.now?string("yyyy/MM/dd HH:mm:ss")}
 * @version V1.0
 */
@SqlMapper
public interface ${property}Mapper{


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