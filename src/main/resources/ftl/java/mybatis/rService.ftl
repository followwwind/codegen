package ${packageName!"com.wind.service"};

<#if primaryKeys?? && primaryKeys?size gt 0>
 <#assign key = getKey(columns, primaryKeys[0])>
 <#assign type = key.type?replace("java.lang.", "")>
<#else>
 <#assign type = "String">
</#if>

/**
 * @Title: ${property}Service
 * @Package ${packageName!"com.wind.service"}
 * @Description: ${remarks!""}业务接口
 * @author wind
 * @date ${.now?string("yyyy/MM/dd HH:mm:ss")}
 * @version V1.0
 */
public interface ${property}Service{

    /**
     * 添加
     * @param r
     * @return
     */
    JsonResult save(Object r);

    /**
     * 删除
     * @param id
     * @return
     */
    JsonResult delete(${type!"String"} id);

    /**
     * 单条记录查询
     * @param id
     * @return
     */
    JsonResult get(${type!"String"} id);

    /**
     * 批量查询记录
     * @param r
     * @return
     */
    JsonResult list(Object r);

    /**
     * 分页查询
     * @param r
     * @return
     */
    JsonResult pageList(Object r);

    /**
     * 修改
     * @param r
     * @return
     */
    JsonResult update(Object r);
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