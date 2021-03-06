package ${packageName!"com.wind.dao"};

<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
import java.util.List;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 添加
     * @param r
     * @return
     */
    int insert(${property} r);

    <#if primaryKeys?size == 1>
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(@Param("id") ${type!"String"} id);

    /**
     * 单条记录查询
     * @param id
     * @return
     */
    ${property} findById(@Param("id") ${type!"String"} id);

    /**
     * 修改
     * @param r
     * @return
     */
    int update(${property} r);
    <#else>
    </#if>

    /**
     * 列表查询
     * @param r
     * @return
     */
    List<${property}VO> list(${property}SearchQ r);

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