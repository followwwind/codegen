package com.wind.dao;

import java.util.List;
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * ${remarks!""} dao测试
 * @author wind
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class ${property}MapperTest{

    @Autowired
    ${property}Mapper mapper;

    @Test
    public void insert() {
        ${property} entity = new ${property}();
        for(int i = 0; i < 10; i++){
        	mapper.insert(entity);
        }
    }

    @Test
    public void delete() {
        ${property} entity = new ${property}();
        int i = mapper.delete(entity);
        System.out.println(i);
    }

    @Test
    public void findEntity() {
        
    }

    @Test
    public void findList() {
        ${property} entity = new ${property}();
        List<${property}> entities = mapper.findList(entity);
        System.out.println(entities.size());
    }

    @Test
    public void update() {
        ${property} entity = new ${property}();
        int i = mapper.update(entity);
        System.out.println(i);
    }

    @Test
    public void count(){
        ${property} entity = new ${property}();
        int count = mapper.count(entity);
        System.out.println(count);
    }
}

