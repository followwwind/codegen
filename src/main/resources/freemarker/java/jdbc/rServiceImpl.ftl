package com.wind.service.impl;

import java.util.List;
import com.wind.dao.${property}Dao;
import com.wind.dao.impl.${property}DaoImpl;
import com.wind.entity.${property};
import com.wind.service.${property}Service;
import com.wind.service.base.BaseServiceImpl;
<#assign key = getKey(columns, primaryKeys[0])>
<#assign type = key.type>

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
    public int deleteById(${type} id) {
        return dao.deleteById(id);
    }

    @Override
    public ${property} findById(${type} id) {
        return dao.findById(id);
    }

    @Override
    public List<${property}> findByCondition(${property} r) {
        return dao.findByCondition(r);
    }

    @Override
    public void findPageList(R r, Page page){
        dao.findPageList(r, page);
    }

    @Override
    public int updateByCondition(${property} r) {
        return dao.updateByCondition(r);
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
