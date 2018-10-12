package ${packageName!"com.wind.service.impl"};

import java.util.List;
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<#if primaryKeys?? && primaryKeys?size gt 0>
    <#assign key = getKey(columns, primaryKeys[0])>
    <#assign type = key.type>
<#else>
    <#assign type = "String">
</#if>

/**
 * ${remarks!""} service接口实现
 * @author wind
 */
@Service
//@Transactional
public class ${property}ServiceImpl extends BaseServiceImpl<${property}, ${type}> implements ${property}Service{

    @Autowired
    private ${property}Mapper mapper;


    @Override
    public int insert(${property} r) {
        int i = mapper.insert(r);
        return i;
    }

    @Override
    public int delete(${property} r) {
        return mapper.delete(r);
    }
    
    @Override
    public ${property} findEntity(${property} r) {
    	List<${property}> entities = mapper.findList(r);
        return entities.size() == 1 ? entities.get(0) : null;
    }

    @Override
    public List<${property}> findList(${property} r) {
        ${property}Example example = new ${property}Example();
        return mapper.findByCondition(example);
    }

    @Override
    public void findPageList(${property} r, Page page){ 
        ${property}Example example = new ${property}Example();
        int totalCount = mapper.countByCondition(example);
        example.setLimit(page.getStartRow() + "," + page.getLineNumber());
        List<${property}> entitys = mapper.findByCondition(example);
        page.setTotalCount(totalCount);
        page.setResult(entitys);
    }

    @Override
    public int update(${property} r) {
        return mapper.updateByCondition(r);
    }

    @Override
    public int count(${property} r){
    	${property}Example example = new ${property}Example();
        return mapper.countByCondition(example);
    }
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
