package com.wind.dao;

import java.util.List;
import com.wind.dao.${property}Mapper;
import com.wind.entity.${property};
import com.wind.entity.base.Page;
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
        int i = mapper.insert(entity);
        System.out.println(i);
    }

    @Test
    public void deleteByCondition() {
        ${property} entity = new ${property}();
        int i = mapper.deleteByCondition(entity);
        System.out.println(i);
    }

    @Test
    public void findEntity() {
        ${property} entity = new ${property}();
        List<${property}> entitys = mapper.findByCondition(entity);
        System.out.println(entitys.size());
    }

    @Test
    public void findByCondition() {
        ${property} entity = new ${property}();
        List<${property}> entitys = mapper.findByCondition(entity);
        System.out.println(entitys.size());
    }

    @Test
    public void updateByCondition() {
        ${property} entity = new ${property}();
        int i = mapper.updateByCondition(entity);
        System.out.println(i);
    }

    @Test
    public void countByCondition(){
        ${property} entity = new ${property}();
        int count = mapper.countByCondition(entity);
        System.out.println(count);
    }
}

