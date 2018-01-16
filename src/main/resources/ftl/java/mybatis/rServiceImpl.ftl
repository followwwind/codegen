package com.wind.service.impl;

import java.util.List;
import com.wind.dao.${property}Mapper;
import com.wind.entity.${property};
import com.wind.service.${property}Service;
import com.wind.service.base.BaseServiceImpl;
import com.wind.entity.base.Page;
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
    public int deleteByCondition(${property} r) {
        return mapper.deleteByCondition(r);
    }

    @Override
    public ${property} findEntity(${property} r) {
        List<${property}> entitys = mapper.findByCondition(r);
        return entitys.size() == 1 ? entitys.get(0) : null;
    }

    @Override
    public List<${property}> findByCondition(${property} r) {
        return mapper.findByCondition(r);
    }

    @Override
    public void findPageList(${property} r, Page page){

    }

    @Override
    public int updateByCondition(${property} r) {
        return mapper.updateByCondition(r);
    }

    @Override
    public int countByCondition(${property} r){
        return mapper.countByCondition(r);
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
