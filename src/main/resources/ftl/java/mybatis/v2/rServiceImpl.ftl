package ${packageName!"com.wind.service.impl"};

import java.util.List;
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        return mapper.findList(r);
    }

    @Override
    public void findPageList(${property} r, Page page){ 
        PageHelper.startPage(page.getPageNumber(), page.getLineNumber());
        List<${property}> entitys = mapper.findList(r);
        PageInfo<${property}> info = new PageInfo<>(entitys);
        page.setResult(entitys);
        page.setTotalCount(info.getTotal());
    }

    @Override
    public int update(${property} r) {
        return mapper.update(r);
    }

    @Override
    public int count(${property} r){
        return mapper.count(r);
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
