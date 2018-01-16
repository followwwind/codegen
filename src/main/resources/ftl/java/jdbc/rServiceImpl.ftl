package com.wind.service.impl;

import java.util.List;
import com.wind.dao.${property}Dao;
import com.wind.dao.impl.${property}DaoImpl;
import com.wind.entity.${property};
import com.wind.service.${property}Service;
import com.wind.service.base.BaseServiceImpl;
import com.wind.entity.base.Page;
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
public class ${property}ServiceImpl extends BaseServiceImpl<${property}, ${type}> implements ${property}Service{

    private ${property}Dao dao = new ${property}DaoImpl();


    @Override
    public int insert(${property} r) {
        int i = dao.insert(r);
        return i;
    }

    @Override
    public int deleteByCondition(${property} r) {
        return dao.deleteByCondition(r);
    }

    @Override
    public ${property} findEntity(${property} r) {
        List<${property}> entitys = dao.findByCondition(r);
        return entitys.size() == 1 ? entitys.get(0) : null;
    }

    @Override
    public List<${property}> findByCondition(${property} r) {
        return dao.findByCondition(r);
    }

    @Override
    public void findPageList(${property} r, Page page){
        dao.findPageList(r, page);
    }

    @Override
    public int updateByCondition(${property} r) {
        return dao.updateByCondition(r);
    }

    @Override
    public int countByCondition(${property} r){
        return dao.countByCondition(r);
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
