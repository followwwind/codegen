package ${packageName!"com.wind.service.impl"};

import java.util.List;
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<#if primaryKeys?? && primaryKeys?size gt 0>
    <#assign key = getKey(columns, primaryKeys[0])>
    <#assign type = key.type>
<#else>
    <#assign type = "String">
</#if>

/**
 * @Title: ${property}ServiceImpl
 * @Package ${packageName!"com.wind.service.impl"}
 * @Description: ${remarks!""}业务处理
 * @author wind
 * @date ${.now?string("yyyy/MM/dd HH:mm:ss")}
 * @version V1.0
 */
@Service
public class ${property}ServiceImpl implements ${property}Service{

	private Logger logger = LoggerFactory.getLogger(${property}ServiceImpl.class);

    @Autowired
    private ${property}Mapper mapper;


    
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
